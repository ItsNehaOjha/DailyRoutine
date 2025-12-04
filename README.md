# Daily Routine Optimizer (JavaFX + SQLite)

A lightweight **Core Java desktop application** for tracking daily study hours, sleep, mood, distractions and notes.  
Built with **JavaFX** for UI and **SQLite** (via JDBC) for persistent storage. No Spring, no Maven.

---

## 1. Features

- **Add Daily Log**
  - Date (yyyy-MM-dd)
  - Study hours (double)
  - Sleep hours (double)
  - Mood rating (1–5)
  - Distraction (text)
  - Notes (multi-line)

- **View Logs**
  - JavaFX `TableView` showing all entries
  - Columns: Date, Study, Sleep, Mood, Distraction, Notes
  - Sorted by date (latest on top)
  - Refresh button to reload from DB

- **Insights**
  - Average study hours
  - Average sleep hours
  - Most frequent distraction
  - Simple overall mood trend

- **Persistence**
  - Data stored in a local SQLite file (`dailyroutine.db`) next to the application
  - Automatic table creation on first run

---

## 2. Tech Stack

| Layer           | Technology            |
|----------------|-----------------------|
| Language       | Core Java (JDK 17)    |
| UI             | JavaFX (controls + FXML) |
| Database       | SQLite                |
| DB Access      | JDBC (sqlite-jdbc driver) |
| IDE            | IntelliJ IDEA         |
| Build system   | IntelliJ project (no Maven / Gradle) |

---

## 3. Project Structure

```text
src/
 ├─ com.routine.db/
 │   └─ DBConnection.java         // SQLite connection + schema init
 ├─ com.routine.model/
 │   └─ DailyLog.java             // POJO with JavaFX properties
 ├─ com.routine.dao/
 │   └─ DailyLogDAO.java          // CRUD for daily_log table
 ├─ com.routine.controller/
 │   └─ MainViewController.java   // Handles all UI events
 ├─ com.routine.ui/
 │   └─ Main.java                 // JavaFX Application entry point
 └─ com.routine.ui/
     └─ MainView.fxml             // Tabbed UI: Add Log / View Logs / Insights
