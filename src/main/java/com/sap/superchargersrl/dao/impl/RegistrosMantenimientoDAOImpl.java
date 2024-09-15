package com.sap.superchargersrl.dao.impl;

import com.sap.superchargersrl.dao.RegistrosMantenimientoDAO;
import com.sap.superchargersrl.model.RegistrosMantenimientos;
import com.sap.superchargersrl.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrosMantenimientoDAOImpl implements RegistrosMantenimientoDAO {

    @Override
    public void save(RegistrosMantenimientos record) {
        String sql = "INSERT INTO maintenance_records (appointmentId, description, cost) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, record.getAppointmentId());
            pstmt.setString(2, record.getDescription());
            pstmt.setDouble(3, record.getCost());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating maintenance record failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    record.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating maintenance record failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In a real application, use a logging framework
        }
    }

    @Override
    public RegistrosMantenimientos findById(int id) {
        String sql = "SELECT * FROM maintenance_records WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractMaintenanceRecordFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RegistrosMantenimientos> findAll() {
        List<RegistrosMantenimientos> records = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_records";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                records.add(extractMaintenanceRecordFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public void update(RegistrosMantenimientos record) {
        String sql = "UPDATE maintenance_records SET appointmentId = ?, description = ?, cost = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, record.getAppointmentId());
            pstmt.setString(2, record.getDescription());
            pstmt.setDouble(3, record.getCost());
            pstmt.setInt(4, record.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM maintenance_records WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RegistrosMantenimientos> findByVehicleId(int vehicleId) {
        List<RegistrosMantenimientos> records = new ArrayList<>();
        String sql = "SELECT mr.* FROM maintenance_records mr " +
                "JOIN appointments a ON mr.appointmentId = a.id " +
                "WHERE a.vehicleId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vehicleId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    records.add(extractMaintenanceRecordFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public List<RegistrosMantenimientos> findByMechanicId(int mechanicId) {
        List<RegistrosMantenimientos> records = new ArrayList<>();
        String sql = "SELECT mr.* FROM maintenance_records mr " +
                "JOIN appointments a ON mr.appointmentId = a.id " +
                "WHERE a.mechanicId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, mechanicId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    records.add(extractMaintenanceRecordFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public List<RegistrosMantenimientos> findByDateRange(Date startDate, Date endDate) {
        List<RegistrosMantenimientos> records = new ArrayList<>();
        String sql = "SELECT mr.* FROM maintenance_records mr " +
                "JOIN appointments a ON mr.appointmentId = a.id " +
                "WHERE a.fecha BETWEEN ? AND ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
            pstmt.setDate(2, new java.sql.Date(endDate.getTime()));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    records.add(extractMaintenanceRecordFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public void addActivity(int recordId, String activity) {
        String sql = "INSERT INTO maintenance_activities (maintenanceRecordId, activity) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, recordId);
            pstmt.setString(2, activity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getActivities(int recordId) {
        List<String> activities = new ArrayList<>();
        String sql = "SELECT activity FROM maintenance_activities WHERE maintenanceRecordId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, recordId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    activities.add(rs.getString("activity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    private RegistrosMantenimientos extractMaintenanceRecordFromResultSet(ResultSet rs) throws SQLException {
        RegistrosMantenimientos record = new RegistrosMantenimientos();
        record.setId(rs.getInt("id"));
        record.setAppointmentId(rs.getInt("appointmentId"));
        record.setDescription(rs.getString("description"));
        record.setCost(rs.getDouble("cost"));
        return record;
    }
}