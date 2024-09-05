package com.sap.superchargersrl.model;

import java.util.UUID;

public class Vehicle {
    private UUID id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private UUID ownerId;

    public Vehicle(String make, String model, int year, String licensePlate, UUID ownerId) {
        this.id = UUID.randomUUID();
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.ownerId = ownerId;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return year + " " + make + " " + model + " (Plate: " + licensePlate + ")";
    }
}