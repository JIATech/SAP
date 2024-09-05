package com.sap.superchargersrl.controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button requestAppointmentButton;

    @FXML
    private Button assignAppointmentButton;

    @FXML
    private Button registerAttendanceButton;

    @FXML
    private Button generateConformityCertificateButton;

    @FXML
    private Button vehicleMaintenanceButton;

    @FXML
    private Button generateMonthlyReportButton;

    @FXML
    private void handleRequestAppointment() {
        loadView("RequestAppointmentView.fxml", "Request Appointment");
    }

    @FXML
    private void handleAssignAppointment() {
        loadView("AssignAppointmentView.fxml", "Assign Appointment");
    }

    @FXML
    private void handleRegisterAttendance() {
        loadView("RegisterAttendanceView.fxml", "Register Attendance");
    }

    @FXML
    private void handleGenerateConformityCertificate() {
        loadView("GenerateConformityCertificateView.fxml", "Generate Conformity Certificate");
    }

    @FXML
    private void handleVehicleMaintenance() {
        loadView("VehicleMaintenanceView.fxml", "Vehicle Maintenance");
    }

    @FXML
    private void handleGenerateMonthlyReport() {
        loadView("GenerateMonthlyReportView.fxml", "Generate Monthly Report");
    }

    private void loadView(String fxmlFile, String title) {
        try {
            File fxmlLocation = new File("C:\\Users\\j.arnaboldi.spb\\IdeaProjects\\SuperchargerSRL\\src\\main\\java\\com\\sap\\superchargersrl\\view\\" + fxmlFile);
            Scene scene = new Scene(FXMLLoader.load(fxmlLocation.toURI().toURL()));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // In a real application, you'd want to handle this exception more gracefully
        }
    }
}
