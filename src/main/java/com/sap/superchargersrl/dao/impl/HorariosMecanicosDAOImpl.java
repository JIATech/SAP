package com.sap.superchargersrl.dao.impl;

import com.sap.superchargersrl.dao.HorariosMecanicosDAO;
import com.sap.superchargersrl.model.HorariosMecanicos;
import com.sap.superchargersrl.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HorariosMecanicosDAOImpl implements HorariosMecanicosDAO {

    @Override
    public void save(HorariosMecanicos record) {
        String sql = "INSERT INTO HorariosMecanicos (id_horario, id_mecanico, fecha, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, record.getId_mecanico());
            pstmt.setDate(2, java.sql.Date.valueOf(record.getFecha()));
            pstmt.setTime(3, record.getHora_inicio());
            pstmt.setTime(4, record.getHora_fin());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating attendance record failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    record.setId_horario(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating attendance record failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In a real application, use a logging framework
        }
    }


    @Override
    public HorariosMecanicos findById(int id) {
        String sql = "SELECT * FROM HorariosMecanicos WHERE id_horario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractHorariosMecanicosFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HorariosMecanicos> findAll() {
        List<HorariosMecanicos> records = new ArrayList<>();
        String sql = "SELECT * FROM HorariosMecanicos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                records.add(extractHorariosMecanicosFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public void update(HorariosMecanicos record) {
        String sql = "UPDATE HorariosMecanicos SET id_horario = ?, fecha = ?, hora_inicio = ?, hora_fin = ? WHERE id_mecanico = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, record.getId_horario());
            pstmt.setDate(2, java.sql.Date.valueOf(record.getFecha()));
            pstmt.setTime(3, record.getHora_inicio());
            pstmt.setTime(4, record.getHora_fin());
            pstmt.setInt(5, record.getId_mecanico());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM HorariosMecanicos WHERE id_mecanico = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<HorariosMecanicos> findByUsuariosId(int id_mecanico) {
        List<HorariosMecanicos> records = new ArrayList<>();
        String sql = "SELECT * FROM HorariosMecanicos WHERE id_mecanico = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_mecanico);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    records.add(extractHorariosMecanicosFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public List<HorariosMecanicos> findByDateRange(Date startDate, Date endDate) {
        List<HorariosMecanicos> records = new ArrayList<>();
        String sql = "SELECT * FROM HorariosMecanicos WHERE fecha BETWEEN ? AND ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
            pstmt.setDate(2, new java.sql.Date(endDate.getTime()));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    records.add(extractHorariosMecanicosFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    private HorariosMecanicos extractHorariosMecanicosFromResultSet(ResultSet rs) throws SQLException {
        HorariosMecanicos record = new HorariosMecanicos();
        record.setId_horario(rs.getInt("id_horario"));
        record.setId_mecanico(rs.getInt("id_mecanico"));
        record.setFecha(rs.getDate("fecha").toLocalDate());
        record.setHora_inicio(rs.getTime("hora_inicio").toLocalTime());
        record.setHora_fin(rs.getTime("hora_fin").toLocalTime());
        return record;
    }
}