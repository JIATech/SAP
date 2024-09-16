package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.Clientes;
import java.util.List;

public interface ClientesDAO {
    Clientes getById(int id);
    List<Clientes> getAll();
    void insert(Clientes clientes);

    Clientes findByDocumentNumber(String documentNumber);

    void update(Clientes clientes);

    void save(Clientes clientes);

    Clientes findById(int id);

    List<Clientes> findAll();

    void delete(int id);

    List<Clientes> findByName(String nombre);
}