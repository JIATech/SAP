package com.sap.superchargersrl.service;

import com.sap.superchargersrl.dao.ConformidadesDAO;
import com.sap.superchargersrl.dao.impl.ConformidadesDAOImpl;
import com.sap.superchargersrl.model.Conformidades;

import java.util.List;

public class ServicioConformidades {

    private ConformidadesDAO conformidadesDAO;

    public ServicioConformidades() {
        this.conformidadesDAO = new ConformidadesDAOImpl();
    }

    public void createCertificate(Conformidades certificate) {
        if (isValidCertificate(certificate)) {
            conformidadesDAO.save(certificate);
        } else {
            throw new IllegalArgumentException("Invalid certificate data");
        }
    }

    public Conformidades getCertificateById(int id) {
        return conformidadesDAO.findById(id);
    }

    public List<Conformidades> getAllCertificates() {
        return conformidadesDAO.findAll();
    }

    public Conformidades getCertificateByAppointment(int appointmentId) {
        return conformidadesDAO.findByAppointmentId(appointmentId);
    }

    public void updateCertificate(Conformidades certificate) {
        if (isValidCertificate(certificate)) {
            conformidadesDAO.update(certificate);
        } else {
            throw new IllegalArgumentException("Invalid certificate data");
        }
    }

    public void deleteCertificate(int id) {
        conformidadesDAO.delete(id);
    }

    public List<Conformidades> getCertificatesByCustomer(int customerId) {
        return conformidadesDAO.findByCustomerId(customerId);
    }

    public List<Conformidades> getCertificatesByVehicle(int vehicleId) {
        return conformidadesDAO.findByVehicleId(vehicleId);
    }

    public void signCertificateByMechanic(int certificateId, int mechanicId) {
        conformidadesDAO.signCertificate(certificateId, mechanicId);
    }

    public boolean isCertificateSignedByCustomer(int certificateId) {
        return conformidadesDAO.isSignedByCustomer(certificateId);
    }

    public void signCertificateByCustomer(int certificateId) {
        conformidadesDAO.signByCustomer(certificateId);
    }

    private boolean isValidCertificate(Conformidades certificate) {
        return certificate != null && certificate.getAppointmentId() > 0;
    }
}