package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.MonthlyReport;
import java.util.List;
import java.util.Date;

public interface MonthlyReportDAO {
    // Create
    void save(MonthlyReport report);

    // Read
    MonthlyReport findById(int id);
    List<MonthlyReport> findAll();
    MonthlyReport findByMonth(int month, int year);

    // Update
    void update(MonthlyReport report);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<MonthlyReport> findByDateRange(Date startDate, Date endDate);
    double calculateTotalRevenue(int reportId);
    int countServicesPerformed(int reportId);
    List<String> getMostCommonServices(int reportId, int limit);
    void addServiceToReport(int reportId, int serviceId);
    List<MonthlyReport> findTopRevenueReports(int limit);
    void generateReport(int month, int year);
}
