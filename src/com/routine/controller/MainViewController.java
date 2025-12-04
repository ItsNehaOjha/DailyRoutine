package com.routine.controller;

import com.routine.dao.DailyLogDAO;
import com.routine.model.DailyLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.List;
import java.util.OptionalDouble;

public class MainViewController {

    // Add Log controls
    @FXML private TextField dateField;
    @FXML private TextField studyField;
    @FXML private TextField sleepField;
    @FXML private ComboBox<Integer> moodBox;
    @FXML private TextField distractionField;
    @FXML private TextArea notesArea;
    @FXML private Button saveButton;

    // View logs
    @FXML private TableView<DailyLog> logTable;
    @FXML private TableColumn<DailyLog, String> dateColumn;
    @FXML private TableColumn<DailyLog, Number> studyColumn;
    @FXML private TableColumn<DailyLog, Number> sleepColumn;
    @FXML private TableColumn<DailyLog, Number> moodColumn;
    @FXML private TableColumn<DailyLog, String> distractionColumn;
    @FXML private TableColumn<DailyLog, String> notesColumn;

    // Insights
    @FXML private Label avgStudyLabel;
    @FXML private Label avgSleepLabel;
    @FXML private Label topDistractionLabel;

    private final DailyLogDAO dao = new DailyLogDAO();
    private final ObservableList<DailyLog> logs = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Mood 1..5
        moodBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        moodBox.getSelectionModel().select(2); // default 3

        // Table column bindings: use property names or PropertyValueFactory names
        dateColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("date"));
        studyColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("studyHours"));
        sleepColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("sleepHours"));
        moodColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("mood"));
        distractionColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("distraction"));
        notesColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("notes"));

        logTable.setItems(logs);

        // initial load
        refreshLogs();
    }

    @FXML
    private void handleSaveLog() {
        String date = dateField.getText().trim();
        double study = parseDoubleOrZero(studyField.getText());
        double sleep = parseDoubleOrZero(sleepField.getText());
        int mood = OptionalIntOrDefault(moodBox.getValue(), 3);
        String disc = distractionField.getText().trim();
        String notes = notesArea.getText().trim();

        if (date.isEmpty()) {
            showAlert("Date is required", Alert.AlertType.WARNING);
            return;
        }

        DailyLog log = new DailyLog(date, study, sleep, mood, disc, notes);
        try {
            dao.insert(log);
            refreshLogs();
            clearForm();
            showAlert("Insert successful.", Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            showAlert("Failed to save log: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void clearForm() {
        dateField.clear();
        studyField.clear();
        sleepField.clear();
        moodBox.getSelectionModel().select(2);
        distractionField.clear();
        notesArea.clear();
    }

    @FXML
    private void handleRefreshLogs() {
        refreshLogs();
    }

    private void refreshLogs() {
        try {
            List<DailyLog> all = dao.findAll();
            logs.setAll(all);
            recalcInsights();
        } catch (SQLException e) {
            showAlert("Failed to load logs: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleRecalculateInsights() {
        recalcInsights();
    }

    private void recalcInsights() {
        if (logs.isEmpty()) {
            avgStudyLabel.setText("-");
            avgSleepLabel.setText("-");
            topDistractionLabel.setText("-");
            return;
        }

        OptionalDouble avgStudy = logs.stream().mapToDouble(DailyLog::getStudyHours).average();
        OptionalDouble avgSleep = logs.stream().mapToDouble(DailyLog::getSleepHours).average();

        avgStudyLabel.setText(avgStudy.isPresent() ? String.format("%.2f", avgStudy.getAsDouble()) : "-");
        avgSleepLabel.setText(avgSleep.isPresent() ? String.format("%.2f", avgSleep.getAsDouble()) : "-");

        // Most frequent distraction (simple frequency)
        String top = logs.stream()
                .map(DailyLog::getDistraction)
                .filter(s -> s != null && !s.isBlank())
                .collect(java.util.stream.Collectors.groupingBy(s -> s, java.util.stream.Collectors.counting()))
                .entrySet()
                .stream()
                .max(java.util.Map.Entry.comparingByValue())
                .map(java.util.Map.Entry::getKey).orElse("-");
        topDistractionLabel.setText(top);
    }

    // helpers
    private double parseDoubleOrZero(String s) {
        try {
            return (s == null || s.isBlank()) ? 0.0 : Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private int OptionalIntOrDefault(Integer v, int def) {
        return v == null ? def : v;
    }

    private void showAlert(String text, Alert.AlertType type) {
        Alert a = new Alert(type, text, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
    }
}
