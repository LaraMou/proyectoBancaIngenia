package com.ingenia.banca.dao;

import com.ingenia.banca.model.Cuenta;


import java.util.Optional;

public interface CuentaDao {
    Optional<Cuenta> findCuentaByNumerocuenta(Long numerocuenta);
}
