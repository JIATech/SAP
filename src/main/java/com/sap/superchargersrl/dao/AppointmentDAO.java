package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.Appointment;
import java.util.Date;
import java.util.List;

public interface AppointmentDAO {
    // Create
    void save(Appointment appointment);

    // Read
    Appointment findById(int id);
    List<Appointment> findAll();
    List<Appointment> findByDate(Date fecha);

    // Update
    void update(Appointment appointment);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<Appointment> findByCustomerId(int customerId);
    List<Appointment> findByVehicleId(int vehicleId);
    List<Appointment> findByMechanicId(int mechanicId);
    List<Appointment> findByStatus(String estado);
    void updateStatus(int id, String estado);
    List<Appointment> findByDateRange(Date startDate, Date endDate);
}