package com.sap.superchargersrl.model;

import java.time.LocalDate;
import java.util.UUID;

public class MaintenanceRecord {
    private UUID id;
    private UUID vehicleId;
    private UUID mechanicId;
    private LocalDate maintenanceDate;
    private String serviceType;
    private String description;
    private int mileage;
    private String partsReplaced;

    public MaintenanceRecord(UUID vehicleId, UUID mechanicId, LocalDate maintenanceDate,
                             String serviceType, String description, int mileage, String partsReplaced) {
        this.id = UUID.randomUUID();
        this.vehicleId = vehicleId;
        this.mechanicId = mechanicId;
        this.maintenanceDate = maintenanceDate;
        this.serviceType = serviceType;
        this.description = description;
        this.mileage = mileage;
        this.partsReplaced = partsReplaced;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public UUID getMechanicId() {
        return mechanicId;
    }

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getPartsReplaced() {
        return partsReplaced;
    }

    public void setPartsReplaced(String partsReplaced) {
        this.partsReplaced = partsReplaced;
    }

    @Override
    public String toString() {
        return serviceType + " on " + maintenanceDate + " for Vehicle " + vehicleId;
    }
}