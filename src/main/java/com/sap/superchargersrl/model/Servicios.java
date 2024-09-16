package com.sap.superchargersrl.model;

public class Servicios {
    private int id_servicio;
    private String nombre_servicio;
    private String descripcion;


    // Getters and Setters
    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getServicio() {
        return nombre_servicio;
    }

    public void setServicio(String servicio) {
        this.nombre_servicio = servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
