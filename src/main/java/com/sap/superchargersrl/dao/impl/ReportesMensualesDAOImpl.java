package com.sap.superchargersrl.dao.impl;

import com.sap.superchargersrl.dao.ReportesMensualesDAO;
import com.sap.superchargersrl.model.ReportesMensuales;
import com.sap.superchargersrl.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ReportesMensualesDAOImpl implements ReportesMensualesDAO {

    @Override
    public void save(ReportesMensuales report) {
        String sql = "INSERT INTO monthly_reports (month, year, totalRevenue, totalServices) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, report.getMonth());
            pstmt.setInt(2, report.getYear());
            pstmt.setDouble(3, report.getTotalRevenue());
            pstmt.setInt(4, report.getTotalServices());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating monthly report failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    report.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating monthly report failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In a real application, use a logging framework
        }
    }

    @Override
    public ReportesMensuales findById(int id) {
        String sql = "SELECT * FROM monthly_reports WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractMonthlyReportFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ReportesMensuales> findAll() {
        List<ReportesMensuales> reports = new ArrayList<>();
        String sql = "SELECT * FROM monthly_reports";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                reports.add(extractMonthlyReportFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public ReportesMensuales findByMonth(int month, int year) {
        String sql = "SELECT * FROM monthly_reports WHERE month = ? AND year = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, month);
            pstmt.setInt(2, year);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractMonthlyReportFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(ReportesMensuales report) {
        String sql = "UPDATE monthly_reports SET month = ?, year = ?, totalRevenue = ?, totalServices = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, report.getMonth());
            pstmt.setInt(2, report.getYear());
            pstmt.setDouble(3, report.getTotalRevenue());
            pstmt.setInt(4, report.getTotalServices());
            pstmt.setInt(5, report.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM monthly_reports WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ReportesMensuales> findByDateRange(Date startDate, Date endDate) {
        List<ReportesMensuales> reports = new ArrayList<>();
        String sql = "SELECT * FROM monthly_reports WHERE (year > ? OR (year = ? AND month >= ?)) AND (year < ? OR (year = ? AND month <= ?))";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(startDate);
            int startYear = cal.get(java.util.Calendar.YEAR);
            int startMonth = cal.get(java.util.Calendar.MONTH) + 1;
            cal.setTime(endDate);
            int endYear = cal.get(java.util.Calendar.YEAR);
            int endMonth = cal.get(java.util.Calendar.MONTH) + 1;

            pstmt.setInt(1, startYear);
            pstmt.setInt(2, startYear);
            pstmt.setInt(3, startMonth);
            pstmt.setInt(4, endYear);
            pstmt.setInt(5, endYear);
            pstmt.setInt(6, endMonth);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    reports.add(extractMonthlyReportFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public double calculateTotalRevenue(int reportId) {
        String sql = "SELECT totalRevenue FROM monthly_reports WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reportId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("totalRevenue");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public int countServicesPerformed(int reportId) {
        String sql = "SELECT totalServices FROM monthly_reports WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reportId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("totalServices");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ReportesMensuales> findTopRevenueReports(int limit) {
        List<ReportesMensuales> reports = new ArrayList<>();
        String sql = "SELECT * FROM monthly_reports ORDER BY totalRevenue DESC LIMIT ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, limit);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    reports.add(extractMonthlyReportFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public void generateReport(int month, int year) {
        // This method would typically involve complex logic to generate a report
        // For this example, we'll create a simple report with placeholder data
        ReportesMensuales report = new ReportesMensuales();
        report.setMonth(month);
        report.setYear(year);
        report.setTotalRevenue(10000.00); // Placeholder value
        report.setTotalServices(50); // Placeholder value

        save(report);
    }

    private ReportesMensuales extractMonthlyReportFromResultSet(ResultSet rs) throws SQLException {
        ReportesMensuales report = new ReportesMensuales();
        report.setId(rs.getInt("id"));
        report.setMonth(rs.getInt("month"));
        report.setYear(rs.getInt("year"));
        report.setTotalRevenue(rs.getDouble("totalRevenue"));
        report.setTotalServices(rs.getInt("totalServices"));
        return report;
    }
}