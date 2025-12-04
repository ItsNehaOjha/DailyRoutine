package com.routine.ui;

import com.routine.dao.DailyLogDAO;
import com.routine.db.DBConnection;
import com.routine.model.DailyLog;

import java.sql.SQLException;
import java.util.List;

/**
 * Console test for DailyLogDAO.
 */
public class TestDailyLogDAO {
    public static void main(String[] args) {
        // Ensure table exists
        DBConnection.initDB();

        DailyLogDAO dao = new DailyLogDAO();

        // 1. Insert a sample record
        DailyLog log = new DailyLog("2025-12-04", 5.0, 7.5, 4, "Social media", "Pretty productive day");

        try {
            dao.insert(log);
            System.out.println("Insert successful.");

            // 2. Read all records
            List<DailyLog> logs = dao.findAll();
            System.out.println("All logs:");
            for (DailyLog l : logs) {
                System.out.println(l);
            }

            // 3. Update the same date
            log.setStudyHours(6.0);
            log.setNotes("Updated: even better day");
            dao.updateByDate(log);
            System.out.println("Update successful.");

            // 4. Read again
            logs = dao.findAll();
            System.out.println("Logs after update:");
            for (DailyLog l : logs) {
                System.out.println(l);
            }

            // 5. Optional: delete
            // dao.deleteByDate("2025-12-04");
            // System.out.println("Delete successful.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
