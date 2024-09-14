package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.Mechanic;
import java.util.Date;
import java.util.List;
import java.time.LocalTime;

public interface MechanicDAO {
    // Create
    void save(Mechanic mechanic);

    // Read
    Mechanic findById(int id);
    List<Mechanic> findAll();
    Mechanic findByName(String nombre, String apellido);

    // Update
    void update(Mechanic mechanic);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<Mechanic> findBySpecialty(String especialidad);
    List<Mechanic> findAvailableMechanics(Date date, LocalTime time);
    void addActivity(int mechanicId, int appointmentId, String activity);
    List<String> getActivities(int mechanicId, int appointmentId);
}