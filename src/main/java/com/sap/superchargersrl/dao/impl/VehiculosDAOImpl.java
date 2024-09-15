package com.sap.superchargersrl.dao.impl;

import com.sap.superchargersrl.dao.VehiculosDAO;
import com.sap.superchargersrl.model.Vehiculos;
import com.sap.superchargersrl.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculosDAOImpl implements VehiculosDAO {

    @Override
    public void save(Vehiculos vehiculos) {
        String sql = "INSERT INTO vehicles (marca, modelo, numeroPoliza, customerId) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, vehiculos.getMarca());
            pstmt.setString(2, vehiculos.getModelo());
            pstmt.setString(3, vehiculos.getNumeroPoliza());
            pstmt.setInt(4, vehiculos.getCustomerId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating vehicle failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    vehiculos.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating vehicle failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In a real application, use a logging framework
        }
    }

    @Override
    public Vehiculos findById(int id) {
        String sql = "SELECT * FROM vehicles WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractVehicleFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehiculos> findAll() {
        List<Vehiculos> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vehiculos.add(extractVehicleFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    @Override
    public Vehiculos findByLicensePlate(String numeroPoliza) {
        String sql = "SELECT * FROM vehicles WHERE numeroPoliza = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, numeroPoliza);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractVehicleFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Vehiculos vehiculos) {
        String sql = "UPDATE vehicles SET marca = ?, modelo = ?, numeroPoliza = ?, customerId = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vehiculos.getMarca());
            pstmt.setString(2, vehiculos.getModelo());
            pstmt.setString(3, vehiculos.getNumeroPoliza());
            pstmt.setInt(4, vehiculos.getCustomerId());
            pstmt.setInt(5, vehiculos.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM vehicles WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vehiculos> findByCustomerId(int customerId) {
        List<Vehiculos> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE customerId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    vehiculos.add(extractVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    @Override
    public List<Vehiculos> findByMakeAndModel(String marca, String modelo) {
        List<Vehiculos> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE marca = ? AND modelo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, marca);
            pstmt.setString(2, modelo);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    vehiculos.add(extractVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    private Vehiculos extractVehicleFromResultSet(ResultSet rs) throws SQLException {
        Vehiculos vehiculos = new Vehiculos();
        vehiculos.setId(rs.getInt("id"));
        vehiculos.setMarca(rs.getString("marca"));
        vehiculos.setModelo(rs.getString("modelo"));
        vehiculos.setNumeroPoliza(rs.getString("numeroPoliza"));
        vehiculos.setCustomerId(rs.getInt("customerId"));
        return vehiculos;
    }
}