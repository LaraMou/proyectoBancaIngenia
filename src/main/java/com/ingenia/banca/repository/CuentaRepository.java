package com.ingenia.banca.repository;

import com.ingenia.banca.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;


public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    @Query("SELECT SUM(importe) from Movimiento where cuenta.numerocuenta=?1")
    Double getSaldoTotalCuenta(Long numerocuenta);

    @Query("SELECT importeinicial from Cuenta  where numerocuenta=?1")
    Double getSaldo(Long numerocuenta);

    @Query("SELECT SUM(importe) from Movimiento where cuenta.numerocuenta=?1 And fechaValor>=?2 and fechaValor<=?3")
    Double getSaldoFecha(Long numerocuenta, LocalDate fechaInicio, LocalDate fechaFin);

    @Query("SELECT AVG(importe) from Movimiento where cuenta.numerocuenta=?1 And fechaValor>=?2 and fechaValor<=?3")
    Double getAverageSaldo(Long numerocuenta, LocalDate fechaInicio, LocalDate fechaFin);








}
