package com.ingenia.banca.services;

import com.ingenia.banca.model.Movimiento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface  MovimientoService {

    Movimiento createMovimiento(Movimiento movimiento);

    Movimiento updateMovimiento(Movimiento movimiento);

    void deleteMovimiento(Long id);

    List<Movimiento> findMovimientos();

    Optional<Movimiento> findOneMovimiento(Long id);

    List <Movimiento> findMovimientosEntre(Long id, LocalDate fechaInicio, LocalDate fechaFin);

    List<Movimiento> getSumaCategoriaMes(Long numerocuenta, LocalDate fechainicio,LocalDate fechafin, Long idCat);
}
