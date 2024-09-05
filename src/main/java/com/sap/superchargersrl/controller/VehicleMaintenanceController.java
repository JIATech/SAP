package com.sap.superchargersrl.controller;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class VehicleMaintenanceController {

    @FXML
    private ComboBox<String> customerComboBox;

    @FXML
    private TextField vehicleInfoField;

    @FXML
    private DatePicker maintenanceDatePicker;

    @FXML
    private TextField mileageField;

    @FXML
    private ComboBox<String> serviceTypeComboBox;

    @FXML
    private TextArea serviceDetailsArea;

    @FXML
    private TextField partReplacedField;

    @FXML
    private TextField technicianField;

    @FXML
    public void initialize() {
        // Populate ComboBoxes with dummy data
        customerComboBox.setItems(FXCollections.observableArrayList("John Doe", "Jane Smith", "Bob Johnson"));
        serviceTypeComboBox.setItems(FXCollections.observableArrayList("Oil Change", "Brake Service", "Tire Rotation", "Engine Tune-up"));

        // Add listener to customerComboBox to update vehicleInfoField
        customerComboBox.setOnAction(event -> updateVehicleInfo());
    }

    private void updateVehicleInfo() {
        String selectedCustomer = customerComboBox.getValue();
        // In a real application, you would fetch this information from a database
        vehicleInfoField.setText(selectedCustomer + "'s Vehicle - Model XYZ");
    }

    @FXML
    private void handleRecordMaintenance() {
        String customer = customerComboBox.getValue();
        String vehicleInfo = vehicleInfoField.getText();
        LocalDate maintenanceDate = maintenanceDatePicker.getValue();
        String mileage = mileageField.getText();
        String serviceType = serviceTypeComboBox.getValue();
        String serviceDetails = serviceDetailsArea.getText();
        String partsReplaced = partReplacedField.getText();
        String technician = technicianField.getText();

        if (customer == null || vehicleInfo.isEmpty() || maintenanceDate == null ||
                mileage.isEmpty() || serviceType == null || serviceDetails.isEmpty() || technician.isEmpty()) {
            showAlert("Error", "All fields except Parts Replaced are required.");
            return;
        }

        try {
            Integer.parseInt(mileage);
        } catch (NumberFormatException e) {
            showAlert("Error", "Mileage must be a valid number.");
            return;
        }

        // Here you would typically save the maintenance record to a database
        // For now, we'll just show a success message
        showAlert("Success", "Maintenance record saved successfully for " + customer);

        // Clear the fields after successful submission
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
        maintenanceDatePicker.setValue(null);
        mileageField.clear();
        serviceTypeComboBox.setValue(null);
        serviceDetailsArea.clear();
        partReplacedField.clear();
        technicianField.clear();
    }
}