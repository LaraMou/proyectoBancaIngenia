package com.ingenia.banca.dao.impl;

import com.ingenia.banca.dao.CuentaDao;
import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.repository.CuentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
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
    Double saldo;

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

        saldoActual = cuentaRepository.getSaldoTotalCuenta(numerocuenta);
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
    public Double getSaldoFecha(Long numerocuenta, LocalDate fechaInicio,LocalDate fechaFin) {
        Double saldoFinal = cuentaRepository.getSaldoFecha(numerocuenta,fechaInicio,fechaFin);
            if(saldoFinal==null)
                saldoFinal = 0d;
            Double saldoInicial = cuentaRepository.getSaldo(numerocuenta);
             saldo= saldoInicial + saldoFinal;
       return saldo;
    }

    @Override
    public Double getAverageSaldo(Long numerocuenta, LocalDate fechainicio, LocalDate fechafin) {
        Double saldoFinal = cuentaRepository.getAverageSaldo(numerocuenta,fechainicio,fechafin);
        if(saldoFinal==null)
            saldoFinal = 0d;

        Double saldoInicial = cuentaRepository.getSaldo(numerocuenta);



        saldo= saldoInicial + saldoFinal;


        return saldo;
    }

    @Override
    @Modifying(clearAutomatically = true)
    @Transactional
    public void saveSaldo(Long numerocuenta, LocalDate fechainicio, LocalDate fechafin) {
        saldo = getSaldoFecha(numerocuenta, fechainicio, fechafin);
        System.out.println("MLO-TEST>>>>" + saldo);

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaUpdate<Cuenta> update = builder.createCriteriaUpdate(Cuenta.class);
        Root<Cuenta> root = update.from(Cuenta.class);
        update.set(root.get("importeactual"), saldo);
        update.where(builder.equal(root.get("numerocuenta"), numerocuenta));

        int i = manager.createQuery(update).executeUpdate();
        System.out.println("Entities updated: " + i);



    }


}
