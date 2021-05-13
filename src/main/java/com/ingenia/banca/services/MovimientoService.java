package com.ingenia.banca.services;

import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Tarjeta;

import java.util.List;

public interface  MovimientoService {

    Movimiento createMovimiento(Movimiento movimiento);

    Movimiento updateMovimiento(Movimiento movimiento);

    void deleteMovimiento(Long id);

    List<Movimiento> findMovimientos();

    Movimiento findOneMovimiento(Long id);
}
