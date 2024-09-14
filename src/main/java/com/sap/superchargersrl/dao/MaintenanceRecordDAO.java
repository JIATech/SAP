package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.MaintenanceRecord;
import java.util.List;
import java.util.Date;

public interface MaintenanceRecordDAO {
    // Create
    void save(MaintenanceRecord record);

    // Read
    MaintenanceRecord findById(int id);
    List<MaintenanceRecord> findAll();

    // Update
    void update(MaintenanceRecord record);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<MaintenanceRecord> findByVehicleId(int vehicleId);
    List<MaintenanceRecord> findByMechanicId(int mechanicId);
    List<MaintenanceRecord> findByDateRange(Date startDate, Date endDate);
    List<MaintenanceRecord> findByServiceType(String serviceType);
    void addActivity(int recordId, String activity);
    List<String> getActivities(int recordId);
    double calculateTotalCost(int recordId);
}