package com.sap.superchargersrl.controller;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RequestAppointmentController {

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField vehicleModelField;

    @FXML
    private TextField vehiclePlateField;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private void handleSubmitRequest() {
        String customerName = customerNameField.getText();
        String vehicleModel = vehicleModelField.getText();
        String vehiclePlate = vehiclePlateField.getText();
        LocalDate appointmentDate = appointmentDatePicker.getValue();

        if (customerName.isEmpty() || vehicleModel.isEmpty() || vehiclePlate.isEmpty() || appointmentDate == null) {
            showAlert("Error", "All fields are required.");
            return;
        }

        // Here you would typically save the appointment request to a database
        // For now, we'll just show a success message
        showAlert("Success", "Appointment request submitted successfully for " + customerName);

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
        customerNameField.clear();
        vehicleModelField.clear();
        vehiclePlateField.clear();
        appointmentDatePicker.setValue(null);
    }
}