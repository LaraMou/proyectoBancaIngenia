package com.ingenia.banca.services;

import com.ingenia.banca.model.Tarjeta;

import java.util.List;

public interface TarjetaService {

    Tarjeta createTarjeta(Tarjeta tarjeta);

    Tarjeta updateTarjeta(Tarjeta tarjeta);

    void deleteTarjeta(Long numeroTarjeta);

    List<Tarjeta> findTarjetas();

    Tarjeta findOneTarjeta(Long numeroTarjeta);
}
