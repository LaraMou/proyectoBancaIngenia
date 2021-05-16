package com.ingenia.banca.dao;

import com.ingenia.banca.model.Movimiento;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovimientoDAO {
    public Movimiento createMovimiento(Movimiento movimiento);

    public Movimiento updateMovimiento(Movimiento movimiento);

    public void deleteMovimiento(Long id);

    public List<Movimiento> findMovimientos();

    public Optional<Movimiento> findOneMovimiento(Long id);

//    List<Movimiento> findAllBetween(LocalDateTime fechainicio, LocalDateTime fechafin);
    List<Movimiento> findAllMovimientoByNumerocuenta(Long numerocuenta);

}
