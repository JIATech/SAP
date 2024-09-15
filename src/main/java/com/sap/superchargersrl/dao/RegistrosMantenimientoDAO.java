package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.RegistrosMantenimientos;
import java.util.List;
import java.util.Date;

public interface RegistrosMantenimientoDAO {
    // Create
    void save(RegistrosMantenimientos record);

    // Read
    RegistrosMantenimientos findById(int id);
    List<RegistrosMantenimientos> findAll();

    // Update
    void update(RegistrosMantenimientos record);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<RegistrosMantenimientos> findByVehicleId(int vehicleId);
    List<RegistrosMantenimientos> findByMechanicId(int mechanicId);
    List<RegistrosMantenimientos> findByDateRange(Date startDate, Date endDate);
    List<RegistrosMantenimientos> findByServiceType(String serviceType);
    void addActivity(int recordId, String activity);
    List<String> getActivities(int recordId);
    double calculateTotalCost(int recordId);
}