package com.ingenia.banca.dao;

import com.ingenia.banca.model.Tarjeta;

import java.util.List;

public interface TarjetaDAO {
    public Tarjeta createTarjeta(Tarjeta tarjeta);

    public Tarjeta updateTarjeta(Tarjeta tarjeta);

    public void deleteTarjeta(Long numero_tarjeta);

    public List<Tarjeta> findTarjetas();

    public Tarjeta findOneTarjeta(Long numeroTarjeta);
}
