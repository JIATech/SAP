package com.sap.superchargersrl.model;

import java.util.UUID;

public class Mechanic {
    private UUID id;
    private String firstName;
    private String lastName;
    private String specialization;
    private String employeeId;

    public Mechanic(String firstName, String lastName, String specialization, String employeeId) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.employeeId = employeeId;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + specialization + ")";
    }
}