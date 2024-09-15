package com.sap.superchargersrl.controller;

import com.sap.superchargersrl.model.Customer;
import com.sap.superchargersrl.service.ServicioClientes;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;

public class ClientesViewController {

    @FXML private TableView<Customer> customerTable;
    @FXML private TextField nameField;
    @FXML private TextField lastNameField;
    @FXML private TextField documentField;

    private ServicioClientes servicioClientes;

    public ClientesViewController() {
        this.servicioClientes = new ServicioClientes();
    }

    @FXML
    public void initialize() {
        loadCustomers();
    }

    @FXML
    public void handleAddCustomer() {
        Customer newCustomer = new Customer();
        newCustomer.setNombre(nameField.getText());
        newCustomer.setApellido(lastNameField.getText());
        newCustomer.setNumeroDocumento(documentField.getText());

        try {
            servicioClientes.registerCustomer(newCustomer);
            loadCustomers(); // Refresh the table
            clearFields();
        } catch (IllegalArgumentException e) {
            // Show error message to user
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void loadCustomers() {
        customerTable.setItems(FXCollections.observableArrayList(servicioClientes.getAllCustomers()));
    }

    private void clearFields() {
        nameField.clear();
        lastNameField.clear();
        documentField.clear();
    }
}
