package com.ingenia.banca.model;

import com.ingenia.banca.repository.CuentaRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CuentaTest {
    @Mock
    Estado estado;
    @Mock
    CuentaRepository cuentaRepository;
    @Test
    @DisplayName("Creación Cuenta")
    void create(){
        Cuenta cuenta = new Cuenta();

        cuenta.setNumerocuenta(1234123401123456789L);
        cuenta.setImporteactual(1000.00);
        cuenta.setImporteactual(1000.00);

        cuenta.setEstado(Estado.ACTIVO);
        cuenta.setFechaactual(LocalDateTime.now());
        cuenta.setFechaapertura(LocalDateTime.of(2021,01,01,00,00));
        cuenta.setFechacontable(LocalDateTime.of(2021,01,03,00,00));
        cuentaRepository.save(cuenta);


    }
}
