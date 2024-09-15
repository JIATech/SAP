package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.Usuarios;
import java.util.Date;
import java.util.List;
import java.time.LocalTime;

public interface UsuariosDAO {
    // Create
    void save(Usuarios usuarios);

    // Read
    Usuarios findById(int id);
    List<Usuarios> findAll();
    Usuarios findByName(String nombre, String apellido);

    // Update
    void update(Usuarios usuarios);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<Usuarios> findBySpecialty(String especialidad);
    List<Usuarios> findAvailableMechanics(Date date, LocalTime time);
    void addActivity(int mechanicId, int appointmentId, String activity);
    List<String> getActivities(int mechanicId, int appointmentId);
}