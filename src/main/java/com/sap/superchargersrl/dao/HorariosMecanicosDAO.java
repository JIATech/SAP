package com.sap.superchargersrl.dao;

import com.sap.superchargersrl.model.HorariosMecanicos;

import java.sql.Date;
import java.util.List;

public interface HorariosMecanicosDAO {
    void save(HorariosMecanicos record);

    HorariosMecanicos findById(int id);

    List<HorariosMecanicos> findAll();

    void update(HorariosMecanicos record);

    void delete(int id);

    List<HorariosMecanicos> findByUsuariosId(int id_mecanico);

    List<HorariosMecanicos> findByDateRange(Date startDate, Date endDate);
}
