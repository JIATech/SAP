package com.sap.superchargersrl.model;

public class Usuarios {
    private Integer id_usuario;
    private String nombre;
    private String apellido;
    private String id_mecanico;

    public Usuarios(String nombre, String apellido, String id_mecanico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id_mecanico = id_mecanico;
    }

    // Getters and setters
    public Integer getId() {
        return id_usuario;
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

    public String getId_mecanico() {
        return id_mecanico;
    }

    public void setId_mecanico(String id_mecanico) {
        this.id_mecanico = id_mecanico;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}