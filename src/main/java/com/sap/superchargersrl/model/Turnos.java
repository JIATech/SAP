package com.sap.superchargersrl.model;

import javafx.scene.text.Text;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

public class Turnos {
    private Integer id_turno;
    private Integer id_cliente;
    private Integer id_vehiculo;
    private LocalDateTime fecha;
    private Time hora;
    private String estado; // e.g., "Scheduled", "Completed", "Cancelled"
    private Integer id_mecanico;

    public Turnos(Integer id_turno, Integer id_cliente, Integer id_vehiculo, LocalDateTime fecha, Time hora, String estado, Text observaciones) {
        this.id_turno = id_turno;
        this.id_cliente = id_cliente;
        this.id_vehiculo = id_vehiculo;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = "Pendiente";
    }

    // Getters and setters
    public Integer getId() {
        return id_turno;
    }

    public Integer getCustomerId() {
        return id_cliente;
    }

    public Integer getVehicleId() {
        return id_vehiculo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getStatus() {
        return estado;
    }

    public void setStatus(String estado) {
        this.estado = estado;
    }

    public Integer getAssignedMechanicId() {
        return id_mecanico;
    }

    public static void setAssignedMechanicId(Integer id_mecanico) {
        this.id_mecanico = id_mecanico;
    }

    @Override
    public String toString() {
        return "Appointment for Vehicle " + id_vehiculo + " on " + fecha + " (" + estado + ")";
    }

    public static void setFecha(Date date) {
        this.fecha = date.toLocalDate().atStartOfDay();
    }

    public static void setHora(Time time) {
        this.fecha = time.toLocalTime().atDate(LocalDateTime.now().toLocalDate());
    }

    public void setMechanicId(Integer id_mecanico) {
        this.id_mecanico = id_mecanico;
    }

    public Time getHora() {
        return hora;
    }
}