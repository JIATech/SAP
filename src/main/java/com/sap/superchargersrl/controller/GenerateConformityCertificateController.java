package com.sap.superchargersrl.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GenerateConformityCertificateController {

    @FXML
    private ComboBox<String> customerComboBox;

    @FXML
    private TextField vehicleInfoField;

    @FXML
    private TextField servicePerformedField;

    @FXML
    private TextArea additionalNotesArea;

    @FXML
    private TextField certificationNumberField;

    @FXML
    public void initialize() {
        // Populate ComboBox with dummy customer data
        customerComboBox.setItems(FXCollections.observableArrayList("John Doe", "Jane Smith", "Bob Johnson"));

        // Add listener to customerComboBox to update vehicleInfoField
        customerComboBox.setOnAction(event -> updateVehicleInfo());
    }

    private void updateVehicleInfo() {
        String selectedCustomer = customerComboBox.getValue();
        // In a real application, you would fetch this information from a database
        vehicleInfoField.setText(selectedCustomer + "'s Vehicle - Model XYZ");
    }

    @FXML
    private void handleGenerateCertificate() {
        String customer = customerComboBox.getValue();
        String vehicleInfo = vehicleInfoField.getText();
        String servicePerformed = servicePerformedField.getText();
        String additionalNotes = additionalNotesArea.getText();
        String certificationNumber = certificationNumberField.getText();

        if (customer == null || vehicleInfo.isEmpty() || servicePerformed.isEmpty() || certificationNumber.isEmpty()) {
            showAlert("Error", "Customer, Vehicle Info, Service Performed, and Certification Number are required fields.");
            return;
        }

        // Here you would typically generate the certificate and save it to a database
        // For now, we'll just show a success message
        showAlert("Success", "Conformity Certificate generated successfully for " + customer);

        // Clear the fields after successful generation
        clearFields();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        customerComboBox.setValue(null);
        vehicleInfoField.clear();
        servicePerformedField.clear();
        additionalNotesArea.clear();
        certificationNumberField.clear();
    }
}