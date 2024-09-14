package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.ConformityCertificate;
import java.util.List;
import java.util.Date;

public interface ConformityCertificateDAO {
    // Create
    void save(ConformityCertificate certificate);

    // Read
    ConformityCertificate findById(int id);
    List<ConformityCertificate> findAll();
    ConformityCertificate findByAppointmentId(int appointmentId);

    // Update
    void update(ConformityCertificate certificate);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<ConformityCertificate> findByCustomerId(int customerId);
    List<ConformityCertificate> findByVehicleId(int vehicleId);
    List<ConformityCertificate> findByDateRange(Date startDate, Date endDate);
    List<ConformityCertificate> findByMechanicId(int mechanicId);
    void signCertificate(int certificateId, int mechanicId);
    boolean isSignedByCustomer(int certificateId);
    void signByCustomer(int certificateId);
    List<String> getCompletedServices(int certificateId);
    void addCompletedService(int certificateId, String serviceName);
}