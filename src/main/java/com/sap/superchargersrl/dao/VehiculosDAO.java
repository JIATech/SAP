package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.Vehiculos;
import java.util.List;

public interface VehiculosDAO {
    // Create
    void save(Vehiculos vehiculos);

    // Read
    Vehiculos findById(int id);
    List<Vehiculos> findAll();
    Vehiculos findByLicensePlate(String numeroPoliza); // Assuming 'numeroPoliza' is the license plate

    // Update
    void update(Vehiculos vehiculos);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<Vehiculos> findByCustomerId(int customerId);
    List<Vehiculos> findByMakeAndModel(String marca, String modelo);
    void updateStatus(int id, String estado);
}