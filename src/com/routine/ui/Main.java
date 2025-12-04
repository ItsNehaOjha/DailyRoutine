package com.routine.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.routine.db.DBConnection;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Ensure DB/table exists before UI
        DBConnection.initDB();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1000, 640);
        stage.setTitle("DailyRoutine App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
