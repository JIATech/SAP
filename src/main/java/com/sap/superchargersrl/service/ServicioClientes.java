package com.sap.superchargersrl.service;

import com.sap.superchargersrl.dao.ClientesDAO;
import com.sap.superchargersrl.dao.impl.ClientesDAOImpl;
import com.sap.superchargersrl.model.Clientes;

import java.util.List;

public class ServicioClientes {

    private final ClientesDAO ClientesDAO;

    public ServicioClientes() {
        this.ClientesDAO = new ClientesDAOImpl();
    }

    public void registrarCliente(Clientes clientes) {
        // Add any business logic for customer registration
        if (isValidCustomer(clientes)) {
            ClientesDAO.save(clientes);
        } else {
            throw new IllegalArgumentException("Invalid customer data");
        }
    }

    public Clientes getClientesById(int id) {
        return ClientesDAO.findById(id);
    }

    public List<Clientes> getAllClientes() {
        return ClientesDAO.findAll();
    }

    public void updateCliente(Clientes clientes) {
        if (isValidCustomer(clientes)) {
            ClientesDAO.update(clientes);
        } else {
            throw new IllegalArgumentException("Invalid customer data");
        }
    }

    public void deleteCliente(int id) {
        ClientesDAO.delete(id);
    }

    public List<Clientes> searchClientesByName(String name) {
        return ClientesDAO.findByName(name);
    }

    private boolean isValidCustomer(Clientes customer) {
        // Implement validation logic
        return customer != null &&
                customer.getNombre() != null && !customer.getNombre().isEmpty() &&
                customer.getApellido() != null && !customer.getApellido().isEmpty() &&
                customer.getNumeroDocumento() != null && !customer.getNumeroDocumento().isEmpty();
    }


    // Add more business-specific methods as needed
}