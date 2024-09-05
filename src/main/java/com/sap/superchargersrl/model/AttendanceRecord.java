package com.sap.superchargersrl.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class AttendanceRecord {
    private UUID id;
    private UUID employeeId;
    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private String notes;

    public AttendanceRecord(UUID employeeId, LocalDate date, LocalTime timeIn) {
        this.id = UUID.randomUUID();
        this.employeeId = employeeId;
        this.date = date;
        this.timeIn = timeIn;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Attendance for Employee " + employeeId + " on " + date +
                " (In: " + timeIn + ", Out: " + (timeOut != null ? timeOut : "Not recorded") + ")";
    }
}