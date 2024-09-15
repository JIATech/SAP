package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.Conformidades;
import java.util.List;
import java.util.Date;

public interface ConformidadesDAO {
    // Create
    void save(Conformidades certificate);

    // Read
    Conformidades findById(int id);
    List<Conformidades> findAll();
    Conformidades findByAppointmentId(int appointmentId);

    // Update
    void update(Conformidades certificate);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<Conformidades> findByCustomerId(int customerId);
    List<Conformidades> findByVehicleId(int vehicleId);
    List<Conformidades> findByDateRange(Date startDate, Date endDate);
    List<Conformidades> findByMechanicId(int mechanicId);
    void signCertificate(int certificateId, int mechanicId);
    boolean isSignedByCustomer(int certificateId);
    void signByCustomer(int certificateId);
    List<String> getCompletedServices(int certificateId);
    void addCompletedService(int certificateId, String serviceName);
}