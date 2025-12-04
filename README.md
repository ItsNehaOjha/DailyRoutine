# Daily Routine Optimizer 📝 (JavaFX + SQLite)

![Java](https://img.shields.io/badge/Java-17-blue)
![JavaFX](https://img.shields.io/badge/JavaFX-UI%20Framework-orange)
![SQLite](https://img.shields.io/badge/SQLite-Database-blue)
![Platform](https://img.shields.io/badge/Platform-Desktop-success)
![License](https://img.shields.io/badge/License-MIT-green)

A lightweight **Core Java desktop application** for tracking daily study hours, sleep, mood, distractions, and notes.  
Built using **JavaFX** for UI and **SQLite (via JDBC)** for storage.  
No Maven. No Spring Boot. **Pure Core Java.**

---

## 🚀 Features

### ✅ Add Daily Log
- Date (yyyy-MM-dd)  
- Study hours  
- Sleep hours  
- Mood rating (1–5)  
- Distraction  
- Notes (multi-line)

### 📄 View Logs
- TableView of all records  
- Sorted by date  
- Refresh button  

### 📊 Insights Dashboard
- Average study hours  
- Average sleep hours  
- Most frequent distraction  
- Basic mood trend  

### 💾 Local Database
- SQLite file: **`dailyroutine.db`**  
- Auto-table creation on first launch  
- Fully offline, portable  

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Core Java (JDK 17) |
| UI | JavaFX (Controls + FXML) |
| Database | SQLite |
| DB Access | JDBC (sqlite-jdbc driver) |
| IDE | IntelliJ IDEA |
| Build | Plain IntelliJ project (no Maven/Gradle) |

---

## 📁 Project Structure

src/
 ├─ com.routine.db/
 │   └─ DBConnection.java
 ├─ com.routine.model/
 │   └─ DailyLog.java
 ├─ com.routine.dao/
 │   └─ DailyLogDAO.java
 ├─ com.routine.controller/
 │   └─ MainViewController.java
 ├─ com.routine.ui/
 │   └─ Main.java
 └─ com.routine.ui/
     └─ MainView.fxml


## 🧩 How to Run This Project (After Cloning)

### **Step 1 — Install Requirements**
1. Install **JDK 17**
2. Download **JavaFX SDK 17**  
   👉 https://gluonhq.com/products/javafx/
3. Ensure `sqlite-jdbc-x.x.x.jar` is inside your project’s **lib/** folder.

---

### **Step 2 — Configure IntelliJ**
1. Open IntelliJ → **File → Open** → select project  
2. Go to:  
   **File → Project Structure → Libraries → Add → Java**  
   - Select folder: `JavaFX SDK → lib/`  
   - Add it  
3. Add SQLite JDBC JAR as another library  
4. Apply → OK

---

### **Step 3 — Configure Run Settings**
Go to:

**Run → Edit Configurations → + → Application**

Set values:

#### **Main class**
com.routine.ui.Main


#### **VM Options**


--module-path "PATH_TO_FX/lib" --add-modules javafx.controls,javafx.fxml


Example:


--module-path "E:\javafx-sdk-17.0.17\lib" --add-modules javafx.controls,javafx.fxml


#### Ensure classpath includes:
- `out/production/<your-project>`  
- `sqlite-jdbc.jar`

---

### **Step 4 — Run the Application**
Click **Run ▶**.

If configured correctly:
- The JavaFX UI opens  
- `dailyroutine.db` is created automatically  
- You can add/view logs  

---

## 📝 Notes
- The project does **not** use Maven or Spring Boot  
- JavaFX must **always** be added manually in VM options  
- The SQLite DB file remains local and portable  
- The project is intentionally simple and clean for academic demonstration  

---

## 📜 License
MIT License – free to use and modify.

---

## ⭐ If you like this project
Star ⭐ the repo and share it!
