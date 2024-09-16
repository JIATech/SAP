package com.sap.superchargersrl.dao.impl;

import com.sap.superchargersrl.dao.ClientesDAO;
import com.sap.superchargersrl.model.Clientes;
import com.sap.superchargersrl.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAOImpl implements ClientesDAO {

    @Override
    public Clientes getById(int id) {
        return null;
    }

    @Override
    public List<Clientes> getAll() {
        return List.of();
    }

    @Override
    public void insert(Clientes clientes) {

    }


    @Override
    public void save(Clientes clientes) {
        String sql = "INSERT INTO Clientes (nombre, apellido, tipo_documento, numero_documento, telefono, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, clientes.getNombre());
            pstmt.setString(2, clientes.getApellido());
            pstmt.setString(3, clientes.getTipoDocumento());
            pstmt.setString(4, clientes.getNumeroDocumento());
            pstmt.setString(5, clientes.getTelefono());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    clientes.setIdCliente(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In a real application, use a logging framework
        }
    }

    @Override
    public Clientes findById(int id) {
        String sql = "SELECT * FROM Clientes WHERE id_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractClientesFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Clientes> findAll() {
        List<Clientes> customers = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Clientes.add(extractClientesFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Clientes findByDocumentNumber(String documentNumber) {
        String sql = "SELECT * FROM Clientes WHERE numero_documento = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, documentNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractClientesFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Clientes clientes) {
        String sql = "UPDATE Clientes SET nombre = ?, apellido = ?, tipo_documento = ?, numero_documento = ?, telefono = ? WHERE id_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, clientes.getNombre());
            pstmt.setString(2, clientes.getApellido());
            pstmt.setString(3, clientes.getTipoDocumento());
            pstmt.setString(4, clientes.getNumeroDocumento());
            pstmt.setString(5, clientes.getTelefono());
            pstmt.setInt(6, clientes.getIdCliente());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Clientes WHERE id_cliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Clientes> findByName(String nombre) {
        List<Clientes> customers = new ArrayList<>();
        String sql = "SELECT * FROM Clientes WHERE nombre LIKE ? OR apellido LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + nombre + "%");
            pstmt.setString(2, "%" + nombre + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Clientes.add(extractClientesFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private Clientes extractClientesFromResultSet(ResultSet rs) throws SQLException {
        return new Clientes(
                rs.getInt("id_cliente"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("tipo_documento"),
                rs.getString("numero_documento"),
                rs.getString("telefono"),
                rs.getString("email")
        );
    }
}