package com.sap.superchargersrl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Clientes {
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String tipo_documento;
    private String numero_documento;
    private String telefono;
    private String email;

    // Constructor
    public Clientes(int id_cliente, String nombre, String apellido, String tipo_documento, String numero_documento, String telefono, String email) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
        this.telefono = telefono;
        this.email = email;
    }

    public Clientes() {

    }

    public static void add(Clientes clientes) {
        String sql = "INSERT INTO Clientes (id_cliente, nombre, apellido, tipo_documento, numero_documento, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://mysql-sapsuperchargersrldb.alwaysdata.net:3306/sapsuperchargersrldb_final?useSSL=true&serverTimezone=UTC");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientes.getIdCliente());
            pstmt.setString(2, clientes.getNombre());
            pstmt.setString(3, clientes.getApellido());
            pstmt.setString(4, clientes.getTipoDocumento());
            pstmt.setString(5, clientes.getNumeroDocumento());
            pstmt.setString(6, clientes.getTelefono());
            pstmt.setString(7, clientes.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Getters and setters
    public int getIdCliente() {
        return id_cliente;
    }

    public void setIdCliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipo_documento;
    }

    public void setTipoDocumento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNumeroDocumento() {
        return numero_documento;
    }

    public void setNumeroDocumento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}