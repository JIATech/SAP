package com.sap.superchargersrl.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class GenerarReportesMensualesController {

    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private ComboBox<Month> monthComboBox;

    @FXML
    private CheckBox appointmentsCheckBox;

    @FXML
    private CheckBox servicesCheckBox;

    @FXML
    private CheckBox revenueCheckBox;

    @FXML
    private CheckBox customerSatisfactionCheckBox;

    @FXML
    private DatePicker generateDatePicker;

    @FXML
    public void initialize() {
        // Populate year ComboBox (let's say from 2020 to current year)
        int currentYear = LocalDate.now().getYear();
        yearComboBox.setItems(FXCollections.observableArrayList(
                IntStream.rangeClosed(2020, currentYear).boxed().toList()
        ));

        // Populate month ComboBox
        monthComboBox.setItems(FXCollections.observableArrayList(Month.values()));

        // Set current date as default for report generation
        generateDatePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handleGenerateReport() {
        if (validateInputs()) {
            // Here you would typically generate the report based on the selected options
            // For now, we'll just show a success message
            showAlert("Success", "Monthly report generated successfully.");
        }
    }

    @FXML
    private void handlePreviewReport() {
        if (validateInputs()) {
            // Here you would typically generate a preview of the report
            // For now, we'll just show an information message
            showAlert("Preview", "Report preview functionality not implemented yet.");
        }
    }

    private boolean validateInputs() {
        Integer year = yearComboBox.getValue();
        Month month = monthComboBox.getValue();
        LocalDate generateDate = generateDatePicker.getValue();

        if (year == null || month == null || generateDate == null) {
            showAlert("Error", "Year, Month, and Generate Date are required fields.");
            return false;
        }

        if (!appointmentsCheckBox.isSelected() && !servicesCheckBox.isSelected() &&
                !revenueCheckBox.isSelected() && !customerSatisfactionCheckBox.isSelected()) {
            showAlert("Error", "Please select at least one report component.");
            return false;
        }

        return true;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}