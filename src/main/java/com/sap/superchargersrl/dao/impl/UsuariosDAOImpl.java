package com.sap.superchargersrl.dao.impl;

import com.sap.superchargersrl.dao.UsuariosDAO;
import com.sap.superchargersrl.model.Usuarios;
import com.sap.superchargersrl.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class UsuariosDAOImpl implements UsuariosDAO {

    @Override
    public void save(Usuarios usuarios) {
        String sql = "INSERT INTO mechanics (nombre, apellido, especialidad) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, usuarios.getNombre());
            pstmt.setString(2, usuarios.getApellido());
            pstmt.setString(3, usuarios.getEspecialidad());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating mechanic failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuarios.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating mechanic failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In a real application, use a logging framework
        }
    }

    @Override
    public Usuarios findById(int id) {
        String sql = "SELECT * FROM mechanics WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractMechanicFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Usuarios> findAll() {
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM mechanics";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuarios.add(extractMechanicFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuarios findByName(String nombre, String apellido) {
        String sql = "SELECT * FROM mechanics WHERE nombre = ? AND apellido = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractMechanicFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Usuarios usuarios) {
        String sql = "UPDATE mechanics SET nombre = ?, apellido = ?, especialidad = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuarios.getNombre());
            pstmt.setString(2, usuarios.getApellido());
            pstmt.setString(3, usuarios.getEspecialidad());
            pstmt.setInt(4, usuarios.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM mechanics WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuarios> findBySpecialty(String especialidad) {
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM mechanics WHERE especialidad = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, especialidad);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(extractMechanicFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public List<Usuarios> findAvailableMechanics(Date date, LocalTime time) {
        List<Usuarios> availableUsuarios = new ArrayList<>();
        String sql = "SELECT m.* FROM mechanics m " +
                "WHERE m.id NOT IN " +
                "(SELECT DISTINCT mechanicId FROM appointments " +
                "WHERE fecha = ? AND hora = ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, new java.sql.Date(date.getTime()));
            pstmt.setTime(2, Time.valueOf(time));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    availableUsuarios.add(extractMechanicFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableUsuarios;
    }

    @Override
    public void addActivity(int mechanicId, int appointmentId, String activity) {
        String sql = "INSERT INTO mechanic_activities (mechanicId, appointmentId, activity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, mechanicId);
            pstmt.setInt(2, appointmentId);
            pstmt.setString(3, activity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getActivities(int mechanicId, int appointmentId) {
        List<String> activities = new ArrayList<>();
        String sql = "SELECT activity FROM mechanic_activities WHERE mechanicId = ? AND appointmentId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, mechanicId);
            pstmt.setInt(2, appointmentId);
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

    private Usuarios extractMechanicFromResultSet(ResultSet rs) throws SQLException {
        Usuarios usuarios = new Usuarios();
        usuarios.setNombre(rs.getString("nombre"));
        usuarios.setApellido(rs.getString("apellido"));
        return usuarios;
    }
}