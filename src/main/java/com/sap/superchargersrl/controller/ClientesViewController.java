package com.sap.superchargersrl.controller;

import com.sap.superchargersrl.model.Clientes;
import com.sap.superchargersrl.service.ServicioClientes;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;

public class ClientesViewController {

    @FXML private TableView<Clientes> customerTable;
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
    public void handleAddClientes() {
        Clientes newClientes = new Clientes();
        newClientes.setNombre(nameField.getText());
        newClientes.setApellido(lastNameField.getText());
        newClientes.setNumeroDocumento(documentField.getText());

        try {
            servicioClientes.registrarCliente(newClientes);
            loadCustomers(); // Refresh the table
            clearFields();
        } catch (IllegalArgumentException e) {
            // Show error message to user
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void loadCustomers() {
        customerTable.setItems(FXCollections.observableArrayList(servicioClientes.getAllClientes()));
    }

    private void clearFields() {
        nameField.clear();
        lastNameField.clear();
        documentField.clear();
    }
}
