package com.sap.superchargersrl.dao.impl;

import com.sap.superchargersrl.dao.ConformidadesDAO;
import com.sap.superchargersrl.model.Conformidades;
import com.sap.superchargersrl.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConformidadesDAOImpl implements ConformidadesDAO {

    @Override
    public void save(Conformidades certificate) {
        String sql = "INSERT INTO conformity_certificates (appointmentId, mechanicSignature, customerSignature) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, certificate.getAppointmentId());
            pstmt.setBoolean(2, certificate.isMechanicSignature());
            pstmt.setBoolean(3, certificate.isCustomerSignature());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating conformity certificate failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    certificate.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating conformity certificate failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In a real application, use a logging framework
        }
    }

    @Override
    public Conformidades findById(int id) {
        String sql = "SELECT * FROM conformity_certificates WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractConformityCertificateFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Conformidades> findAll() {
        List<Conformidades> certificates = new ArrayList<>();
        String sql = "SELECT * FROM conformity_certificates";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                certificates.add(extractConformityCertificateFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return certificates;
    }

    @Override
    public Conformidades findByAppointmentId(int appointmentId) {
        String sql = "SELECT * FROM conformity_certificates WHERE appointmentId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, appointmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractConformityCertificateFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Conformidades certificate) {
        String sql = "UPDATE conformity_certificates SET appointmentId = ?, mechanicSignature = ?, customerSignature = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, certificate.getAppointmentId());
            pstmt.setBoolean(2, certificate.isMechanicSignature());
            pstmt.setBoolean(3, certificate.isCustomerSignature());
            pstmt.setInt(4, certificate.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM conformity_certificates WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Conformidades> findByCustomerId(int customerId) {
        List<Conformidades> certificates = new ArrayList<>();
        String sql = "SELECT cc.* FROM conformity_certificates cc " +
                "JOIN appointments a ON cc.appointmentId = a.id " +
                "WHERE a.customerId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    certificates.add(extractConformityCertificateFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return certificates;
    }

    @Override
    public List<Conformidades> findByVehicleId(int vehicleId) {
        List<Conformidades> certificates = new ArrayList<>();
        String sql = "SELECT cc.* FROM conformity_certificates cc " +
                "JOIN appointments a ON cc.appointmentId = a.id " +
                "WHERE a.vehicleId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vehicleId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    certificates.add(extractConformityCertificateFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return certificates;
    }

    @Override
    public void signCertificate(int certificateId, int mechanicId) {
        String sql = "UPDATE conformity_certificates SET mechanicSignature = TRUE WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, certificateId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isSignedByCustomer(int certificateId) {
        String sql = "SELECT customerSignature FROM conformity_certificates WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, certificateId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("customerSignature");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void signByCustomer(int certificateId) {
        String sql = "UPDATE conformity_certificates SET customerSignature = TRUE WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, certificateId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Conformidades extractConformityCertificateFromResultSet(ResultSet rs) throws SQLException {
        Conformidades certificate = new Conformidades();
        certificate.setId(rs.getInt("id"));
        certificate.setAppointmentId(rs.getInt("appointmentId"));
        certificate.setMechanicSignature(rs.getBoolean("mechanicSignature"));
        certificate.setCustomerSignature(rs.getBoolean("customerSignature"));
        return certificate;
    }
}