package com.sap.superchargersrl.controller;

import com.sap.superchargersrl.model.Servicios;
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
        LocalDate selectedDate = datePicker.getValue();
        LocalTime selectedTime = timeComboBox.getValue();
        Usuarios selectedMechanic = mechanicComboBox.getValue();

        if (selectedDate != null && selectedTime != null && selectedMechanic != null) {
            Turnos newAppointment = initializeTurnos(selectedDate, selectedTime, selectedMechanic);

            try {
                servicioTurnos.scheduleAppointment(newAppointment);
                System.out.println("Appointment successfully created!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Please select a date, time, and mechanic.");
        }
    }

    private Turnos initializeTurnos(LocalDate date, LocalTime time, Usuarios mechanic) {
        Turnos turnos = new Turnos(null, null, null, date.atTime(time), java.sql.Time.valueOf(time), "Pendiente", null);
        Turnos.setFecha(java.sql.Date.valueOf(date));
        Turnos.setHora(java.sql.Time.valueOf(time));
        Turnos.setAssignedMechanicId(mechanic.getId());
        return turnos;
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
