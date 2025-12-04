package com.routine;
import com.routine.model.DailyLog;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:test.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("SQLite connection successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DailyLog log = new DailyLog("2025-12-04", 4.5, 7.0, 4, "Phone", "Decent day");
        System.out.println(log);

    }
}
