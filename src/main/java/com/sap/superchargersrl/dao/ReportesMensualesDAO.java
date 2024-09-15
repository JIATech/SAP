package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.ReportesMensuales;
import java.util.List;
import java.util.Date;

public interface ReportesMensualesDAO {
    // Create
    void save(ReportesMensuales report);

    // Read
    ReportesMensuales findById(int id);
    List<ReportesMensuales> findAll();
    ReportesMensuales findByMonth(int month, int year);

    // Update
    void update(ReportesMensuales report);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<ReportesMensuales> findByDateRange(Date startDate, Date endDate);
    double calculateTotalRevenue(int reportId);
    int countServicesPerformed(int reportId);
    List<String> getMostCommonServices(int reportId, int limit);
    void addServiceToReport(int reportId, int serviceId);
    List<ReportesMensuales> findTopRevenueReports(int limit);
    void generateReport(int month, int year);
}
