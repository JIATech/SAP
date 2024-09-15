package com.sap.superchargersrl.service;

import com.sap.superchargersrl.dao.TurnosDAO;
import com.sap.superchargersrl.dao.impl.TurnosDAOImpl;
import com.sap.superchargersrl.model.Turnos;

import java.util.List;
import java.util.Date;

public class ServicioTurnos {

    private TurnosDAO turnosDAO;
    private ServicioMecanico servicioMecanico;

    public ServicioTurnos() {
        this.turnosDAO = new TurnosDAOImpl();
        this.servicioMecanico = new ServicioMecanico();
    }

    public void scheduleAppointment(Turnos turnos) {
        if (isValidAppointment(turnos) && isMechanicAvailable(turnos)) {
            turnosDAO.save(turnos);
        } else {
            throw new IllegalArgumentException("Invalid appointment data or mechanic not available");
        }
    }

    public Turnos getAppointmentById(int id) {
        return turnosDAO.findById(id);
    }

    public List<Turnos> getAllAppointments() {
        return turnosDAO.findAll();
    }

    public List<Turnos> getAppointmentsByDate(Date date) {
        return turnosDAO.findByDate(date);
    }

    public void updateAppointment(Turnos turnos) {
        if (isValidAppointment(turnos) && isMechanicAvailable(turnos)) {
            turnosDAO.update(turnos);
        } else {
            throw new IllegalArgumentException("Invalid appointment data or mechanic not available");
        }
    }

    public void cancelAppointment(int id) {
        turnosDAO.delete(id);
    }

    public List<Turnos> getAppointmentsByCustomer(int customerId) {
        return turnosDAO.findByCustomerId(customerId);
    }

    public List<Turnos> getAppointmentsByVehicle(int vehicleId) {
        return turnosDAO.findByVehicleId(vehicleId);
    }

    public List<Turnos> getAppointmentsByMechanic(int mechanicId) {
        return turnosDAO.findByMechanicId(mechanicId);
    }

    public List<Turnos> getAppointmentsByStatus(String status) {
        return turnosDAO.findByStatus(status);
    }

    public void updateAppointmentStatus(int id, String status) {
        turnosDAO.updateStatus(id, status);
    }

    private boolean isValidAppointment(Turnos turnos) {
        return turnos != null &&
                turnos.getCustomerId() > 0 &&
                turnos.getVehicleId() > 0 &&
                turnos.getMechanicId() > 0 &&
                turnos.getFecha() != null &&
                turnos.getHora() != null &&
                turnos.getEstado() != null && !turnos.getEstado().isEmpty();
    }

    private boolean isMechanicAvailable(Turnos turnos) {
        List<Mechanic> availableMechanics = servicioMecanico.getAvailableMechanics(
                turnos.getFecha(), turnos.getHora().toLocalTime());
        return availableMechanics.stream()
                .anyMatch(mechanic -> mechanic.getId() == turnos.getMechanicId());
    }
}