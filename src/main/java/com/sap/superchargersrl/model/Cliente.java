package com.sap.superchargersrl.model;

public class Cliente {
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String tipo_documento;
    private String numero_documento;
    private String telefono;
    private String email;

    // Constructor
    public Cliente(int id_cliente, String nombre, String apellido, String tipo_documento, String numero_documento, String telefono, String email) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
        this.telefono = telefono;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumeroDocumento() {
        return numero_documento;
    }

    public void setNumeroDocumento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getTipoDocumento() {
        return tipo_documento;
    }

    public void setTipoDocumento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}