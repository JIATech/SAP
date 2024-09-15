package com.sap.superchargersrl.model;

import java.time.LocalDate;
import java.util.UUID;

public class Conformidades {
    private UUID id;
    private UUID vehicleId;
    private UUID maintenanceRecordId;
    private String certificateNumber;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String issuedBy;
    private String remarks;

    public Conformidades(UUID vehicleId, UUID maintenanceRecordId, String certificateNumber,
                         LocalDate issueDate, LocalDate expirationDate, String issuedBy) {
        this.id = UUID.randomUUID();
        this.vehicleId = vehicleId;
        this.maintenanceRecordId = maintenanceRecordId;
        this.certificateNumber = certificateNumber;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.issuedBy = issuedBy;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public UUID getMaintenanceRecordId() {
        return maintenanceRecordId;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Certificate " + certificateNumber + " for Vehicle " + vehicleId + " (Issued: " + issueDate + ", Expires: " + expirationDate + ")";
    }
}