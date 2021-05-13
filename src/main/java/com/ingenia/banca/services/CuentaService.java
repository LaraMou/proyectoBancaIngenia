package com.ingenia.banca.services;

import com.ingenia.banca.model.Cuenta;


import java.util.List;
import java.util.Optional;

public interface CuentaService {
    List<Cuenta> findAll();

    Optional<Cuenta> findCuentaByNumerocuenta(Long numerocuenta);

    Cuenta saveCuenta(Cuenta cuenta);


}
