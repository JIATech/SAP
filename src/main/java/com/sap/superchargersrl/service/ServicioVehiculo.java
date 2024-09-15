package com.sap.superchargersrl.service;

import com.sap.superchargersrl.dao.VehiculosDAO;
import com.sap.superchargersrl.dao.impl.VehiculosDAOImpl;
import com.sap.superchargersrl.model.Vehiculos;

import java.util.List;

public class ServicioVehiculo {

    private VehiculosDAO vehiculosDAO;

    public ServicioVehiculo() {
        this.vehiculosDAO = new VehiculosDAOImpl();
    }

    public void registerVehicle(Vehiculos vehiculos) {
        if (isValidVehicle(vehiculos)) {
            vehiculosDAO.save(vehiculos);
        } else {
            throw new IllegalArgumentException("Invalid vehicle data");
        }
    }

    public Vehiculos getVehicleById(int id) {
        return vehiculosDAO.findById(id);
    }

    public List<Vehiculos> getAllVehicles() {
        return vehiculosDAO.findAll();
    }

    public void updateVehicle(Vehiculos vehiculos) {
        if (isValidVehicle(vehiculos)) {
            vehiculosDAO.update(vehiculos);
        } else {
            throw new IllegalArgumentException("Invalid vehicle data");
        }
    }

    public void deleteVehicle(int id) {
        vehiculosDAO.delete(id);
    }

    public List<Vehiculos> getVehiclesByCustomerId(int customerId) {
        return vehiculosDAO.findByCustomerId(customerId);
    }

    public Vehiculos findByLicensePlate(String licensePlate) {
        return vehiculosDAO.findByLicensePlate(licensePlate);
    }

    private boolean isValidVehicle(Vehiculos vehiculos) {
        return vehiculos != null &&
                vehiculos.getMarca() != null && !vehiculos.getMarca().isEmpty() &&
                vehiculos.getModelo() != null && !vehiculos.getModelo().isEmpty() &&
                vehiculos.getNumeroPoliza() != null && !vehiculos.getNumeroPoliza().isEmpty();
    }
}