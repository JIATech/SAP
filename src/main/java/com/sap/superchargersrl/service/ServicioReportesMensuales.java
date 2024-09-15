package com.sap.superchargersrl.service;

import com.sap.superchargersrl.dao.ReportesMensualesDAO;
import com.sap.superchargersrl.dao.impl.ReportesMensualesDAOImpl;
import com.sap.superchargersrl.model.ReportesMensuales;

import java.util.List;
import java.util.Date;

public class ServicioReportesMensuales {

    private ReportesMensualesDAO reportesMensualesDAO;

    public ServicioReportesMensuales() {
        this.reportesMensualesDAO = new ReportesMensualesDAOImpl();
    }

    public void generateMonthlyReport(int month, int year) {
        reportesMensualesDAO.generateReport(month, year);
    }

    public ReportesMensuales getReportById(int id) {
        return reportesMensualesDAO.findById(id);
    }

    public List<ReportesMensuales> getAllReports() {
        return reportesMensualesDAO.findAll();
    }

    public ReportesMensuales getReportByMonth(int month, int year) {
        return reportesMensualesDAO.findByMonth(month, year);
    }

    public void updateReport(ReportesMensuales report) {
        if (isValidReport(report)) {
            reportesMensualesDAO.update(report);
        } else {
            throw new IllegalArgumentException("Invalid report data");
        }
    }

    public void deleteReport(int id) {
        reportesMensualesDAO.delete(id);
    }

    public List<ReportesMensuales> getReportsByDateRange(Date startDate, Date endDate) {
        return reportesMensualesDAO.findByDateRange(startDate, endDate);
    }

    public double calculateTotalRevenue(int reportId) {
        return reportesMensualesDAO.calculateTotalRevenue(reportId);
    }

    public int countServicesPerformed(int reportId) {
        return reportesMensualesDAO.countServicesPerformed(reportId);
    }

    public List<ReportesMensuales> getTopRevenueReports(int limit) {
        return reportesMensualesDAO.findTopRevenueReports(limit);
    }

    private boolean isValidReport(ReportesMensuales report) {
        return report != null &&
                report.getMonth() >= 1 && report.getMonth() <= 12 &&
                report.getYear() > 0 &&
                report.getTotalRevenue() >= 0 &&
                report.getTotalServices() >= 0;
    }
}