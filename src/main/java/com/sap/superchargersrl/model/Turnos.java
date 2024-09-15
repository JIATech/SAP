package com.sap.superchargersrl.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

public class Turnos {
    private UUID id;
    private UUID customerId;
    private UUID vehicleId;
    private LocalDateTime appointmentDateTime;
    private String serviceType;
    private String status; // e.g., "Scheduled", "Completed", "Cancelled"
    private UUID assignedMechanicId;

    public Turnos(UUID customerId, UUID vehicleId, LocalDateTime appointmentDateTime, String serviceType) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.appointmentDateTime = appointmentDateTime;
        this.serviceType = serviceType;
        this.status = "Scheduled";
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getAssignedMechanicId() {
        return assignedMechanicId;
    }

    public void setAssignedMechanicId(UUID assignedMechanicId) {
        this.assignedMechanicId = assignedMechanicId;
    }

    @Override
    public String toString() {
        return "Appointment for Vehicle " + vehicleId + " on " + appointmentDateTime + " (" + status + ")";
    }

    public void setFecha(Date date) {
        this.appointmentDateTime = date.toLocalDate().atStartOfDay();
    }

    public void setHora(Time time) {
        this.appointmentDateTime = time.toLocalTime().atDate(LocalDateTime.now().toLocalDate());
    }

    public void setMechanicId(UUID id) {
        this.assignedMechanicId = id;
    }
}