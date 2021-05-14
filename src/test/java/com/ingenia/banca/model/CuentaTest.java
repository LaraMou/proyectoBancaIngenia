package com.ingenia.banca.model;

import com.ingenia.banca.repository.CuentaRepository;

import com.ingenia.banca.repository.MovimientoRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CuentaTest {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    @Autowired
    private CuentaRepository cuentaRepository;
    Cuenta cuenta;
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Test
    @DisplayName("Creación Cuenta")
    void create() {
        Cuenta cuenta = new Cuenta();

        cuenta.setNumerocuenta(2234123401123456789L);
        cuenta.setImporteactual(1000.00);
        cuenta.setImporteactual(1000.00);

        cuenta.setEstado(Estado.ACTIVO);
        cuenta.setFechaactual(LocalDate.now());
        cuenta.setFechaapertura(LocalDate.of(2021, 01, 01));
        cuenta.setFechacontable(LocalDate.of(2021, 01, 03));



    }

}
