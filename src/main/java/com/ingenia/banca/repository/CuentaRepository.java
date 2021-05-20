package com.ingenia.banca.repository;

import com.ingenia.banca.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {



    @Query("SELECT importeinicial from Cuenta  where numerocuenta=?1")
    Double getSaldo(Long numerocuenta);



}
