package com.ingenia.banca.dao;

import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Usuario;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CuentaDao {
    Optional<Cuenta> findCuentaByNumerocuenta(Long numerocuenta);

    List<Cuenta> findAll(Integer paginacion, Integer limite);

    List<Cuenta> findAccountsByUsuario(Long idUsuario);

    Double getSaldo(Long numerocuenta);
    Double getSaldoFecha(Long numerocuenta, LocalDate fecha);

}
