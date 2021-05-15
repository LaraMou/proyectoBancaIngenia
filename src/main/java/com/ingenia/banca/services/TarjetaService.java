package com.ingenia.banca.services;

import com.ingenia.banca.model.Tarjeta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TarjetaService {

    Tarjeta createTarjeta(Tarjeta tarjeta);

    Tarjeta updateTarjeta(Tarjeta tarjeta);

    void deleteTarjeta(Long numeroTarjeta);

    List<Tarjeta> findTarjetas();

    Optional<Tarjeta> findOneTarjeta(Long numeroTarjeta);
}
