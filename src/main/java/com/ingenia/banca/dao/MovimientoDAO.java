package com.ingenia.banca.dao;

import com.ingenia.banca.model.Movimiento;


import java.util.List;

public interface MovimientoDAO {
    public Movimiento createMovimiento(Movimiento movimiento);

    public Movimiento updateMovimiento(Movimiento movimiento);

    public void deleteMovimiento(Long id);

    public List<Movimiento> findMovimientos();

    public Movimiento findOneMovimiento(Long id);

}
