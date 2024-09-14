package com.sap.superchargersrl.dao.impl;

import com.sap.superchargersrl.dao.CustomerDAO;
import com.sap.superchargersrl.model.Customer;
import com.sap.superchargersrl.util.DatabaseConnection;
import java.sql.*;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private static final String INSERT_SQL = "INSERT INTO customers (nombre, apellido, tipoDocumento, numeroDocumento, telefono) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM customers WHERE id = ?";

    @Override
    public void save(Customer customer) {
        executeUpdate(INSERT_SQL, pstmt -> {
            pstmt.setString(1, customer.getNombre());
            pstmt.setString(2, customer.getApellido());
            pstmt.setString(3, customer.getTipoDocumento());
            pstmt.setString(4, customer.getNumeroDocumento());
            pstmt.setString(5, customer.getTelefono());
        });
    }

    @Override
    public Customer findById(int id) {
        return executeQuery(SELECT_BY_ID_SQL, pstmt -> pstmt.setInt(1, id), this::extractCustomerFromResultSet);
    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }

    @Override
    public Customer findByDocumentNumber(String documentNumber) {
        return null;
    }

    @Override
    public void update(Customer customer) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public List<Customer> findByName(String name) {
        return List.of();
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return List.of();
    }

    private Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setNombre(rs.getString("nombre"));
        customer.setApellido(rs.getString("apellido"));
        customer.setTipoDocumento(rs.getString("tipoDocumento"));
        customer.setNumeroDocumento(rs.getString("numeroDocumento"));
        customer.setTelefono(rs.getString("telefono"));
        return customer;
    }

    private void executeUpdate(String sql, ThrowingConsumer<PreparedStatement> parameterSetter) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            parameterSetter.accept(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private <T> T executeQuery(String sql, ThrowingConsumer<PreparedStatement> parameterSetter, ThrowingFunction<ResultSet, T> resultSetProcessor) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            parameterSetter.accept(pstmt);
            try (ResultSet rs = pstmt.executeQuery()) {
                return resultSetProcessor.apply(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FunctionalInterface
    private interface ThrowingConsumer<T> {
        void accept(T t) throws SQLException;
    }

    @FunctionalInterface
    private interface ThrowingFunction<T, R> {
        R apply(T t) throws SQLException;
    }
}