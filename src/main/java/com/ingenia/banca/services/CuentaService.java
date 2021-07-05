package com.ingenia.banca.services;

import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Usuario;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CuentaService {
    List<Cuenta> findAll();

    Optional<Cuenta> findCuentaByNumerocuenta(Long numerocuenta);

    Cuenta saveCuenta(Cuenta cuenta);

   void deleteById(Long id);

   List<Cuenta> findAccountsByUsuario(Long idUsuario);
   Double getSaldo(Long numerocuenta);
    Double getSaldoFecha(Long numerocuenta, LocalDate fechaentrada,LocalDate fechafin);
    Double getAverageSaldo(Long numerocuenta, LocalDate fechaentrada,LocalDate fechafin);
}
