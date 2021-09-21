package com.ingenia.banca.repository;

import com.ingenia.banca.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Long> {
    @Query("SELECT C.numerocuenta from  Cuenta C Join Tarjeta T on C.numerocuenta = T.cuenta.numerocuenta  where T.numeroTarjeta=?1")
    Long getNumeroCuenta(Long numeroTarjeta);
}
