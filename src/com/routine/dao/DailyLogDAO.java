package com.routine.dao;

import com.routine.db.DBConnection;
import com.routine.model.DailyLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DailyLogDAO {

    public void insert(DailyLog log) throws SQLException {
        String sql = """
                INSERT INTO daily_log(date, study_hours, sleep_hours, mood, distraction, notes)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, log.getDate());
            ps.setDouble(2, log.getStudyHours());
            ps.setDouble(3, log.getSleepHours());
            ps.setInt(4, log.getMood());
            ps.setString(5, log.getDistraction());
            ps.setString(6, log.getNotes());
            ps.executeUpdate();
        }
    }

    // This is the one your TestDailyLogDAO calls
    public void updateByDate(DailyLog log) throws SQLException {
        String sql = """
                UPDATE daily_log
                SET study_hours = ?, sleep_hours = ?, mood = ?, distraction = ?, notes = ?
                WHERE date = ?
                """;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDouble(1, log.getStudyHours());
            ps.setDouble(2, log.getSleepHours());
            ps.setInt(3, log.getMood());
            ps.setString(4, log.getDistraction());
            ps.setString(5, log.getNotes());
            ps.setString(6, log.getDate());
            ps.executeUpdate();
        }
    }

    public List<DailyLog> findAll() throws SQLException {
        String sql = "SELECT * FROM daily_log ORDER BY date DESC";
        List<DailyLog> list = new ArrayList<>();

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DailyLog log = new DailyLog();
                log.setId(rs.getInt("id"));
                log.setDate(rs.getString("date"));
                log.setStudyHours(rs.getDouble("study_hours"));
                log.setSleepHours(rs.getDouble("sleep_hours"));
                log.setMood(rs.getInt("mood"));
                log.setDistraction(rs.getString("distraction"));
                log.setNotes(rs.getString("notes"));
                list.add(log);
            }
        }
        return list;
    }

    public void deleteByDate(String date) throws SQLException {
        String sql = "DELETE FROM daily_log WHERE date = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, date);
            ps.executeUpdate();
        }
    }
}
