package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.Turnos;
import java.util.Date;
import java.util.List;

public interface TurnosDAO {
    // Create
    void save(Turnos turnos);

    // Read
    Turnos findById(int id);
    List<Turnos> findAll();
    List<Turnos> findByDate(Date fecha);

    // Update
    void update(Turnos turnos);

    // Delete
    void delete(int id);

    // Additional methods specific to your business needs
    List<Turnos> findByCustomerId(int customerId);

    List<Turnos> findByClienteId(int customerId);

    List<Turnos> findByVehicleId(int vehicleId);
    List<Turnos> findByMechanicId(int mechanicId);
    List<Turnos> findByStatus(String estado);
    void updateStatus(int id, String estado);
    List<Turnos> findByDateRange(Date startDate, Date endDate);
}