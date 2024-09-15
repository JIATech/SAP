package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.Cliente;
import java.util.List;

public interface ClienteDAO {
    Cliente getById(int id);
    List<Cliente> getAll();
    void insert(Cliente cliente);
    void update(Cliente cliente);
    void delete(int id);
}