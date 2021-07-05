package com.ingenia.banca.dao;

import com.ingenia.banca.model.Tarjeta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TarjetaDAO {
    public Tarjeta createTarjeta(Tarjeta tarjeta);

    public Tarjeta updateTarjeta(Tarjeta tarjeta);

    public void deleteTarjeta(Long numeroTarjeta);

    public List<Tarjeta> findTarjetas();

    public Optional<Tarjeta> findOneTarjeta(Long numeroTarjeta);

    public List<Tarjeta> findTarjetasByCuenta(Long numeroCuenta);
    Double getSaldoFecha(Long numeroTarjeta, LocalDate fechainicio, LocalDate fechafin);
}
