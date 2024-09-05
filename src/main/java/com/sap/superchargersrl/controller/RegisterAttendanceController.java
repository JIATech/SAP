package com.sap.superchargersrl.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RegisterAttendanceController {

    @FXML
    private DatePicker attendanceDatePicker;

    @FXML
    private ComboBox<String> employeeComboBox;

    @FXML
    private TextField timeInField;

    @FXML
    private TextField timeOutField;

    @FXML
    private TextField notesField;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    public void initialize() {
        // Populate ComboBox with dummy employee data
        employeeComboBox.setItems(FXCollections.observableArrayList("John Doe", "Jane Smith", "Bob Johnson"));
    }

    @FXML
    private void handleRegisterAttendance() {
        String employee = employeeComboBox.getValue();
        LocalDate date = attendanceDatePicker.getValue();
        String timeIn = timeInField.getText();
        String timeOut = timeOutField.getText();
        String notes = notesField.getText();

        if (employee == null || date == null || timeIn.isEmpty() || timeOut.isEmpty()) {
            showAlert("Error", "Employee, Date, Time In, and Time Out are required fields.");
            return;
        }

        if (!isValidTime(timeIn) || !isValidTime(timeOut)) {
            showAlert("Error", "Invalid time format. Please use HH:MM format.");
            return;
        }

        // Here you would typically save the attendance record to a database
        // For now, we'll just show a success message
        showAlert("Success", "Attendance registered successfully for " + employee);

        // Clear the fields after successful registration
        clearFields();
    }

    private boolean isValidTime(String time) {
        try {
            LocalTime.parse(time, timeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        employeeComboBox.setValue(null);
        attendanceDatePicker.setValue(null);
        timeInField.clear();
        timeOutField.clear();
        notesField.clear();
    }
}