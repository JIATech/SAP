package com.sap.superchargersrl.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class HorariosMecanicos {
    private int id_horario;
    private int id_mecanico;
    private LocalDate fecha;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    private HorariosMecanicos extractHorariosMecanicosFromResultSet(ResultSet rs) throws SQLException {
        HorariosMecanicos record = new HorariosMecanicos();
        record.setId_horario(rs.getInt("id"));
        record.setId_mecanico(rs.getInt("employeeId"));
        record.setFecha(rs.getDate("date").toLocalDate());
        record.setHora_inicio(rs.getTime("timeIn").toLocalTime());
        record.setHora_fin(rs.getTime("timeOut").toLocalTime());
        return record;
    }

    // Constructor vacío
    public HorariosMecanicos() {
    }

    // Getters y setters
    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getId_mecanico() {
        return id_mecanico;
    }

    public void setId_mecanico(int id_mecanico) {
        this.id_mecanico = id_mecanico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public long setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    @Override
    public String toString() {
        return "Attendance for Mechanic " + id_mecanico + " on " + fecha +
                " (In: " + hora_inicio + ", Out: " + (hora_fin != null ? hora_fin : "Not recorded") + ")";
    }

}