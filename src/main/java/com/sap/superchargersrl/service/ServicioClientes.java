package com.sap.superchargersrl.service;

import com.sap.superchargersrl.dao.CustomerDAO;
import com.sap.superchargersrl.dao.impl.ClientesDAOImpl;
import com.sap.superchargersrl.model.Customer;

import java.util.List;

public class ServicioClientes {

    private CustomerDAO customerDAO;

    public ServicioClientes() {
        this.customerDAO = new ClientesDAOImpl();
    }

    public void registerCustomer(Customer customer) {
        // Add any business logic for customer registration
        if (isValidCustomer(customer)) {
            customerDAO.save(customer);
        } else {
            throw new IllegalArgumentException("Invalid customer data");
        }
    }

    public Customer getCustomerById(int id) {
        return customerDAO.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    public void updateCustomer(Customer customer) {
        if (isValidCustomer(customer)) {
            customerDAO.update(customer);
        } else {
            throw new IllegalArgumentException("Invalid customer data");
        }
    }

    public void deleteCustomer(int id) {
        customerDAO.delete(id);
    }

    public List<Customer> searchCustomersByName(String name) {
        return customerDAO.findByName(name);
    }

    private boolean isValidCustomer(Customer customer) {
        // Implement validation logic
        return customer != null &&
                customer.getNombre() != null && !customer.getNombre().isEmpty() &&
                customer.getApellido() != null && !customer.getApellido().isEmpty() &&
                customer.getNumeroDocumento() != null && !customer.getNumeroDocumento().isEmpty();
    }

    // Add more business-specific methods as needed
}