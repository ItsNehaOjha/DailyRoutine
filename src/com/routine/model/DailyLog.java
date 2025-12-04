package com.routine.model;

import javafx.beans.property.*;

public class DailyLog {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id", 0);
    private final StringProperty date = new SimpleStringProperty(this, "date", "");
    private final DoubleProperty studyHours = new SimpleDoubleProperty(this, "studyHours", 0.0);
    private final DoubleProperty sleepHours = new SimpleDoubleProperty(this, "sleepHours", 0.0);
    private final IntegerProperty mood = new SimpleIntegerProperty(this, "mood", 0);
    private final StringProperty distraction = new SimpleStringProperty(this, "distraction", "");
    private final StringProperty notes = new SimpleStringProperty(this, "notes", "");

    public DailyLog() {}

    public DailyLog(String date, double studyHours, double sleepHours, int mood, String distraction, String notes) {
        setDate(date);
        setStudyHours(studyHours);
        setSleepHours(sleepHours);
        setMood(mood);
        setDistraction(distraction);
        setNotes(notes);
    }

    // id
    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    // date
    public String getDate() { return date.get(); }
    public void setDate(String value) { date.set(value); }
    public StringProperty dateProperty() { return date; }

    // studyHours
    public double getStudyHours() { return studyHours.get(); }
    public void setStudyHours(double value) { studyHours.set(value); }
    public DoubleProperty studyHoursProperty() { return studyHours; }

    // sleepHours
    public double getSleepHours() { return sleepHours.get(); }
    public void setSleepHours(double value) { sleepHours.set(value); }
    public DoubleProperty sleepHoursProperty() { return sleepHours; }

    // mood
    public int getMood() { return mood.get(); }
    public void setMood(int value) { mood.set(value); }
    public IntegerProperty moodProperty() { return mood; }

    // distraction
    public String getDistraction() { return distraction.get(); }
    public void setDistraction(String value) { distraction.set(value); }
    public StringProperty distractionProperty() { return distraction; }

    // notes
    public String getNotes() { return notes.get(); }
    public void setNotes(String value) { notes.set(value); }
    public StringProperty notesProperty() { return notes; }

    @Override
    public String toString() {
        return "DailyLog{id=" + getId() + ", date=" + getDate() + "}";
    }
}
