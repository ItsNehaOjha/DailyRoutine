# Daily Routine Optimizer (JavaFX + SQLite)

A lightweight **Core Java desktop application** for tracking daily study hours, sleep, mood, distractions, and notes.  
Built using **JavaFX** for UI and **SQLite (JDBC)** for data storage.  
No Maven. No Spring Boot. Pure Core Java.

---

## 1. Features

### **Add Daily Log**
- Date (yyyy-MM-dd)  
- Study hours  
- Sleep hours  
- Mood rating (1–5)  
- Distraction  
- Notes  

### **View Logs**
- TableView listing all logs  
- Sorted by date  
- Refresh option  

### **Insights**
- Average study hours  
- Average sleep hours  
- Most frequent distraction  
- Simple mood trend  

### **Database**
- Uses `dailyroutine.db` (SQLite) stored locally next to the `.java` code  
- Table auto-created on first run  

---

## 2. Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Core Java (JDK 17) |
| UI | JavaFX (FXML + Controls) |
| Database | SQLite |
| DB Access | JDBC (sqlite-jdbc driver) |
| IDE | IntelliJ IDEA |
| Build | IntelliJ Project (no Maven/Gradle) |

---

4.How to Run This Project (after cloning)
Step 1 — Install Requirements

Install JDK 17

Download JavaFX SDK 17
https://gluonhq.com/products/javafx/

Make sure sqlite-jdbc-x.x.x.jar is inside the project's lib/ folder

Step 2 — Configure IntelliJ

Open IntelliJ → File → Open Project → select your cloned repo

Go to:
File → Project Structure → Libraries → Add → Select JavaFX SDK → lib folder

Also add the SQLite JDBC .jar as a library

Apply → OK

Step 3 — Update Run Configuration

Go to Run → Edit Configurations → Add New → Application

Set:

Main class: com.routine.ui.Main

VM Options:

--module-path "PATH_TO_FX/lib" --add-modules javafx.controls,javafx.fxml


Example:

--module-path "E:\javafx-sdk-17.0.17\lib" --add-modules javafx.controls,javafx.fxml


Ensure your classpath includes:

out/production/<project>

sqlite-jdbc.jar

Step 4 — Run the Application

Click Run ▶
If everything is configured correctly, the UI will launch and dailyroutine.db will be created automatically.

5. Notes

The project does not require Maven or Spring Boot

SQLite DB file stays local; portable across machines

JavaFX must always be added manually to VM options
