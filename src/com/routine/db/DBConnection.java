package com.routine.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    // SQLite file will be created next to the project working dir (change path if needed)
    private static final String URL = "jdbc:sqlite:daily_routine.db";

    static {
        // For modern sqlite-jdbc the driver auto-registers; Class.forName is optional.
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // If jar not on classpath you'll get this early; fail fast.
            throw new RuntimeException("SQLite JDBC driver not found. Add the jar to classpath.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Call at app startup to create table if missing
    public static void initDB() {
        String sql = """
            CREATE TABLE IF NOT EXISTS daily_log (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                date TEXT UNIQUE NOT NULL,
                study_hours REAL,
                sleep_hours REAL,
                mood INTEGER,
                distraction TEXT,
                notes TEXT
            );
            """;

        try (Connection c = getConnection(); Statement stmt = c.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize DB", e);
        }
    }
}
