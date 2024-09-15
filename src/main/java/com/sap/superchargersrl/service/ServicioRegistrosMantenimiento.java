package com.sap.superchargersrl.service;

import com.sap.superchargersrl.dao.RegistrosMantenimientoDAO;
import com.sap.superchargersrl.dao.impl.RegistrosMantenimientoDAOImpl;
import com.sap.superchargersrl.model.RegistrosMantenimientos;

import java.util.List;
import java.util.Date;

public class ServicioRegistrosMantenimiento {

    private RegistrosMantenimientoDAO registrosMantenimientoDAO;

    public ServicioRegistrosMantenimiento() {
        this.registrosMantenimientoDAO = new RegistrosMantenimientoDAOImpl();
    }

    public void createMaintenanceRecord(RegistrosMantenimientos record) {
        if (isValidMaintenanceRecord(record)) {
            registrosMantenimientoDAO.save(record);
        } else {
            throw new IllegalArgumentException("Invalid maintenance record data");
        }
    }

    public RegistrosMantenimientos getMaintenanceRecordById(int id) {
        return registrosMantenimientoDAO.findById(id);
    }

    public List<RegistrosMantenimientos> getAllMaintenanceRecords() {
        return registrosMantenimientoDAO.findAll();
    }

    public void updateMaintenanceRecord(RegistrosMantenimientos record) {
        if (isValidMaintenanceRecord(record)) {
            registrosMantenimientoDAO.update(record);
        } else {
            throw new IllegalArgumentException("Invalid maintenance record data");
        }
    }

    public void deleteMaintenanceRecord(int id) {
        registrosMantenimientoDAO.delete(id);
    }

    public List<RegistrosMantenimientos> getMaintenanceRecordsByVehicle(int vehicleId) {
        return registrosMantenimientoDAO.findByVehicleId(vehicleId);
    }

    public List<RegistrosMantenimientos> getMaintenanceRecordsByMechanic(int mechanicId) {
        return registrosMantenimientoDAO.findByMechanicId(mechanicId);
    }

    public List<RegistrosMantenimientos> getMaintenanceRecordsByDateRange(Date startDate, Date endDate) {
        return registrosMantenimientoDAO.findByDateRange(startDate, endDate);
    }

    public void addActivityToMaintenanceRecord(int recordId, String activity) {
        registrosMantenimientoDAO.addActivity(recordId, activity);
    }

    public List<String> getMaintenanceRecordActivities(int recordId) {
        return registrosMantenimientoDAO.getActivities(recordId);
    }

    private boolean isValidMaintenanceRecord(RegistrosMantenimientos record) {
        return record != null &&
                record.getAppointmentId() > 0 &&
                record.getDescription() != null && !record.getDescription().isEmpty() &&
                record.getCost() >= 0;
    }
}