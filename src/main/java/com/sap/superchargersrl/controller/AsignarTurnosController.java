package com.sap.superchargersrl.controller;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AsignarTurnosController {

    @FXML
    private ComboBox<String> customerComboBox;

    @FXML
    private TextField vehicleInfoField;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ComboBox<String> timeSlotComboBox;

    @FXML
    private ComboBox<String> mechanicComboBox;

    @FXML
    public void initialize() {
        // Populate ComboBoxes with dummy data
        customerComboBox.setItems(FXCollections.observableArrayList("John Doe", "Jane Smith", "Bob Johnson"));
        timeSlotComboBox.setItems(FXCollections.observableArrayList("09:00 AM", "10:00 AM", "11:00 AM", "02:00 PM", "03:00 PM"));
        mechanicComboBox.setItems(FXCollections.observableArrayList("Mike Mechanic", "Sarah Spanner", "Tom Toolbox"));

        // Add listener to customerComboBox to update vehicleInfoField
        customerComboBox.setOnAction(event -> updateVehicleInfo());
    }

    private void updateVehicleInfo() {
        String selectedCustomer = customerComboBox.getValue();
        // In a real application, you would fetch this information from a database
        vehicleInfoField.setText(selectedCustomer + "'s Vehicle - Model XYZ");
    }

    @FXML
    private void handleAssignAppointment() {
        String customer = customerComboBox.getValue();
        LocalDate date = appointmentDatePicker.getValue();
        String timeSlot = timeSlotComboBox.getValue();
        String mechanic = mechanicComboBox.getValue();

        if (customer == null || date == null || timeSlot == null || mechanic == null) {
            showAlert("Error", "All fields are required.");
            return;
        }

        // Here you would typically save the assigned appointment to a database
        // For now, we'll just show a success message
        showAlert("Success", "Appointment assigned successfully for " + customer);

        // Clear the fields after successful assignment
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
        appointmentDatePicker.setValue(null);
        timeSlotComboBox.setValue(null);
        mechanicComboBox.setValue(null);
    }
}