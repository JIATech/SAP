package com.sap.superchargersrl.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public class MonthlyReport {
    private UUID id;
    private YearMonth reportPeriod;
    private LocalDate generationDate;
    private int totalAppointments;
    private int completedServices;
    private double totalRevenue;
    private double averageCustomerSatisfaction;
    private String additionalNotes;

    public MonthlyReport(YearMonth reportPeriod, LocalDate generationDate) {
        this.id = UUID.randomUUID();
        this.reportPeriod = reportPeriod;
        this.generationDate = generationDate;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public YearMonth getReportPeriod() {
        return reportPeriod;
    }

    public LocalDate getGenerationDate() {
        return generationDate;
    }

    public int getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(int totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    public int getCompletedServices() {
        return completedServices;
    }

    public void setCompletedServices(int completedServices) {
        this.completedServices = completedServices;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getAverageCustomerSatisfaction() {
        return averageCustomerSatisfaction;
    }

    public void setAverageCustomerSatisfaction(double averageCustomerSatisfaction) {
        this.averageCustomerSatisfaction = averageCustomerSatisfaction;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    @Override
    public String toString() {
        return "Monthly Report for " + reportPeriod.getMonth() + " " + reportPeriod.getYear() +
                " (Generated on: " + generationDate + ")";
    }
}