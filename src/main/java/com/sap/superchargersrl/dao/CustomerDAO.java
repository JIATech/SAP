package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.Customer;
import java.util.List;

public interface CustomerDAO {
    // Create
    void save(Customer customer);

    // Read
    Customer findById(int id);
    List<Customer> findAll();
    Customer findByDocumentNumber(String documentNumber);

    // Update
    void update(Customer customer);

    // Delete
    void delete(int id);

    // Más metodos
    List<Customer> findByName(String nombre);
    List<Customer> findByLastName(String apellido);
}