package com.sap.superchargersrl.service;

import com.sap.superchargersrl.dao.UsuariosDAO;
import com.sap.superchargersrl.dao.impl.UsuariosDAOImpl;
import com.sap.superchargersrl.model.Usuarios;

import java.util.List;
import java.util.Date;
import java.time.LocalTime;

public class ServicioMecanico {

    private UsuariosDAO usuariosDAO;

    public ServicioMecanico() {
        this.usuariosDAO = new UsuariosDAOImpl();
    }

    public void hireMechanic(Usuarios usuarios) {
        if (isValidMechanic(usuarios)) {
            usuariosDAO.save(usuarios);
        } else {
            throw new IllegalArgumentException("Invalid mechanic data");
        }
    }

    public Usuarios getMechanicById(int id) {
        return usuariosDAO.findById(id);
    }

    public List<Usuarios> getAllMechanics() {
        return usuariosDAO.findAll();
    }

    public void updateMechanic(Usuarios usuarios) {
        if (isValidMechanic(usuarios)) {
            usuariosDAO.update(usuarios);
        } else {
            throw new IllegalArgumentException("Invalid mechanic data");
        }
    }

    public void deleteMechanic(int id) {
        usuariosDAO.delete(id);
    }

    public List<Usuarios> getMechanicsBySpecialty(String specialty) {
        return usuariosDAO.findBySpecialty(specialty);
    }

    public List<Usuarios> getAvailableMechanics(Date date, LocalTime time) {
        return usuariosDAO.findAvailableMechanics(date, time);
    }

    public void addActivityToMechanic(int mechanicId, int appointmentId, String activity) {
        usuariosDAO.addActivity(mechanicId, appointmentId, activity);
    }

    public List<String> getMechanicActivities(int mechanicId, int appointmentId) {
        return usuariosDAO.getActivities(mechanicId, appointmentId);
    }

    private boolean isValidMechanic(Usuarios usuarios) {
        return usuarios != null &&
                usuarios.getNombre() != null && !usuarios.getNombre().isEmpty() &&
                usuarios.getApellido() != null && !usuarios.getApellido().isEmpty() &&
    }
}