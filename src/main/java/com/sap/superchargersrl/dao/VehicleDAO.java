package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.Vehicle;
import java.util.List;

public interface VehicleDAO {
    // Create
    void save(Vehicle vehicle);

    // Read
    Vehicle findById(int id);
    List<Vehicle> findAll();
    Vehicle findByLicensePlate(String numeroPoliza); // Assuming 'numeroPoliza' is the license plate

    // Update
    void update(Vehicle vehicle);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<Vehicle> findByCustomerId(int customerId);
    List<Vehicle> findByMakeAndModel(String marca, String modelo);
    void updateStatus(int id, String estado);
}