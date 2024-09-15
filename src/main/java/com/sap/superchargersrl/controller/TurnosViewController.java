package com.sap.superchargersrl.controller;

import com.sap.superchargersrl.model.Turnos;
import com.sap.superchargersrl.model.Usuarios;
import com.sap.superchargersrl.service.ServicioTurnos;
import com.sap.superchargersrl.service.ServicioMecanico;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.collections.FXCollections;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class TurnosViewController {

    @FXML private DatePicker datePicker;
    @FXML private ComboBox<LocalTime> timeComboBox;
    @FXML private ComboBox<Usuarios> mechanicComboBox;

    private final ServicioTurnos servicioTurnos;
    private final ServicioMecanico servicioMecanico;

    public TurnosViewController() {
        this.servicioTurnos = new ServicioTurnos();
        this.servicioMecanico = new ServicioMecanico();
    }

    @FXML
    public void initialize() {
        populateTimeComboBox();
        loadMechanics();
    }

    @FXML
    public void handleDateSelection() {
        LocalDate selectedDate = datePicker.getValue();
        LocalTime selectedTime = timeComboBox.getValue();
        if (selectedDate != null && selectedTime != null) {
            loadAvailableMechanics(selectedDate, selectedTime);
        }
    }

    @FXML
    public void handleCreateAppointment() {
        LocalDate date = datePicker.getValue();
        LocalTime time = timeComboBox.getValue();
        Usuarios usuarios = mechanicComboBox.getValue();

        if (date != null && time != null && usuarios != null) {
            Turnos turnos = new Turnos();
            turnos.setFecha(java.sql.Date.valueOf(date));
            turnos.setHora(java.sql.Time.valueOf(time));
            turnos.setMechanicId(usuarios.getId());
            // Set other necessary fields

            try {
                servicioTurnos.scheduleAppointment(turnos);
                // Show success message and clear fields
            } catch (IllegalArgumentException e) {
                // Show error message to user
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void populateTimeComboBox() {
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);
        while (start.isBefore(end)) {
            timeComboBox.getItems().add(start);
            start = start.plusMinutes(30);
        }
    }

    private void loadMechanics() {
        mechanicComboBox.setItems(FXCollections.observableArrayList(servicioMecanico.getAllMechanics()));
    }

    private void loadAvailableMechanics(LocalDate date, LocalTime time) {
        Date javaDate = java.sql.Date.valueOf(date);
        mechanicComboBox.setItems(FXCollections.observableArrayList(
                servicioMecanico.getAvailableMechanics(javaDate, time)
        ));
    }
}
