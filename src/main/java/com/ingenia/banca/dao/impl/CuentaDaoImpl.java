package com.ingenia.banca.dao.impl;

import com.ingenia.banca.dao.CuentaDao;
import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.repository.CuentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class CuentaDaoImpl implements CuentaDao {
    @Autowired
    CuentaRepository cuentaRepository;

    @PersistenceContext
    private EntityManager manager;

    //variables de la clase
    private Double saldoActual;
    private Long numerocuenta;
    private LocalDate fechaentrada;

    @Override
    public Optional<Cuenta> findCuentaByNumerocuenta(Long numerocuenta) {
        if (numerocuenta != null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Cuenta> criteria = builder.createQuery(Cuenta.class);
            Root<Cuenta> root = criteria.from(Cuenta.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("numerocuenta"), numerocuenta));
            Cuenta item = manager.createQuery(criteria).getSingleResult();
            manager.close();
            return Optional.of(item);

        }
        return Optional.empty();
    }

    @Override
    public List<Cuenta> findAll(Integer paginacion, Integer limite) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Cuenta> criteriaQuery = criteriaBuilder.createQuery(Cuenta.class);
        Root<Cuenta> root = criteriaQuery.from(Cuenta.class);
        criteriaQuery.select(root);
        Query query = manager.createQuery(criteriaQuery);
        query.setMaxResults(limite);
        query.setFirstResult(paginacion);
        manager.close();
        return query.getResultList();
    }

    @Override
    public List<Cuenta> findAccountsByUsuario(Long idUsuario) {
        Usuario usuarioDb = manager.find(Usuario.class, idUsuario);

        if (usuarioDb != null) {
            return usuarioDb.getCuentas();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Double getSaldo(Long numerocuenta) {

        saldoActual = cuentaRepository.getSaldo(numerocuenta);

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Double> criteria = builder.createQuery(Double.class);
        Root<Cuenta> root = criteria.from(Cuenta.class);
        criteria.select(root.get("importeinicial"));

        criteria.where(builder.equal(root.get("numerocuenta"), numerocuenta));

        Double saldo = manager.createQuery(criteria).getSingleResult();

        Double calculadoSaldo = saldo + saldoActual;


        return calculadoSaldo;
    }


    @Override
    public Double getSaldoFecha(Long numerocuenta, LocalDate fechainicio,LocalDate fechafin) {
        //recuperar si la fecha es menor a la fecha de creacion

        //suma de saldo de movimiento entre fechas

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Double> criteria = builder.createQuery(Double.class);
        Root<Movimiento> root = criteria.from(Movimiento.class);
        criteria.select(builder.sum(root.get("importe")));
        criteria.where(builder.between(root.get("fechaValor"), fechainicio,fechafin));
        Double saldo = manager.createQuery(criteria).getSingleResult();
        if(saldo== null)
            saldo = 0D;

        Double saldoInicial = cuentaRepository.getSaldo(numerocuenta);
        System.out.println("saldo"+saldoInicial);
        Double calculadoSaldo = saldoInicial + saldo;

        return calculadoSaldo;
    }






}
