package com.sap.superchargersrl.dao.impl;

import com.sap.superchargersrl.dao.TurnosDAO;
import com.sap.superchargersrl.model.Turnos;
import com.sap.superchargersrl.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TurnosDAOImpl implements TurnosDAO {

    @Override
    public void save(Turnos turnos) {
        String sql = "INSERT INTO Turnos (fecha, hora, estado, id_cliente, id_vehiculo, id_mecanico) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Splitting LocalDateTime into Date and Time
            LocalDateTime dateTime = turnos.getFecha();
            Date date = Date.valueOf(dateTime.toLocalDate());
            Time time = Time.valueOf(dateTime.toLocalTime());

            pstmt.setDate(1, date);
            pstmt.setTime(2, time);
            pstmt.setString(3, turnos.getStatus());

            // Converting UUID to String to avoid type mismatch
            pstmt.setString(4, turnos.getCustomerId().toString());
            pstmt.setString(5, turnos.getVehicleId().toString());
            pstmt.setString(6, turnos.getAssignedMechanicId().toString());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating appointment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    turnos.setId(UUID.fromString(generatedKeys.getString(1)));
                } else {
                    throw new SQLException("Creating appointment failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In a real application, use a logging framework
        }
    }

    @Override
    public Turnos findById(int id) {
        String sql = "SELECT * FROM Turnos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractAppointmentFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Turnos> findAll() {
        List<Turnos> turnos = new ArrayList<>();
        String sql = "SELECT * FROM Turnos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                turnos.add(extractAppointmentFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turnos;
    }

    @Override
    public List<Turnos> findByDate(java.util.Date date) {
        List<Turnos> turnos = new ArrayList<>();
        String sql = "SELECT * FROM Turnos WHERE fecha = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, new java.sql.Date(date.getTime()));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    turnos.add(extractAppointmentFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turnos;
    }

    @Override
    public void update(Turnos turnos) {
        String sql = "UPDATE Turnos SET fecha = ?, hora = ?, estado = ?, id_cliente = ?, id_vehiculo = ?, id_mecnico = ? WHERE id_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, new java.sql.Date(turnos.getFecha().getTime()));
            pstmt.setTime(2, new java.sql.Time(turnos.getHora().getTime()));
            pstmt.setString(3, turnos.getEstado());
            pstmt.setInt(4, turnos.getCustomerId());
            pstmt.setInt(5, turnos.getVehicleId());
            pstmt.setInt(6, turnos.getMechanicId());
            pstmt.setInt(7, turnos.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Turnos WHERE id_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Turnos> findByClienteId(int customerId) {
        List<Turnos> turnos = new ArrayList<>();
        String sql = "SELECT * FROM Turnos WHERE id_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    turnos.add(extractAppointmentFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turnos;
    }

    @Override
    public List<Turnos> findByVehicleId(int vehicleId) {
        List<Turnos> turnos = new ArrayList<>();
        String sql = "SELECT * FROM Turnos WHERE id_vehiculo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vehicleId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    turnos.add(extractAppointmentFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turnos;
    }

    @Override
    public List<Turnos> findByMechanicId(int mechanicId) {
        List<Turnos> turnos = new ArrayList<>();
        String sql = "SELECT * FROM Turnos WHERE id_mecanico = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, mechanicId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    turnos.add(extractAppointmentFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turnos;
    }

    @Override
    public List<Turnos> findByStatus(String estado) {
        List<Turnos> turnos = new ArrayList<>();
        String sql = "SELECT * FROM Turnos WHERE estado = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estado);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    turnos.add(extractAppointmentFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turnos;
    }

    @Override
    public void updateStatus(int id, String estado) {
        String sql = "UPDATE Turnos SET estado = ? WHERE id_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estado);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Turnos extractTurnosFromResultSet(ResultSet rs) throws SQLException {
        Turnos turnos = new Turnos();
        turnos.setId(rs.getInt("id"));
        turnos.setFecha(rs.getDate("fecha"));
        turnos.setHora(rs.getTime("hora"));
        turnos.setEstado(rs.getString("estado"));
        turnos.setCustomerId(rs.getInt("customerId"));
        turnos.setVehicleId(rs.getInt("vehicleId"));
        turnos.setMechanicId(rs.getInt("mechanicId"));
        return turnos;
    }
}