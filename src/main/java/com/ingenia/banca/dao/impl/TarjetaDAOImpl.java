package com.ingenia.banca.dao.impl;

import com.ingenia.banca.dao.TarjetaDAO;
import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.repository.CuentaRepository;
import com.ingenia.banca.repository.TarjetaRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class TarjetaDAOImpl implements TarjetaDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private TarjetaRepository tarjetaRepository;
    @Autowired
    CuentaRepository cuentaRepository;


    @Override
    public Tarjeta createTarjeta(Tarjeta tarjeta){
        return tarjetaRepository.save(tarjeta);
    }

    @Override
    public Tarjeta updateTarjeta(Tarjeta tarjeta){
        return tarjetaRepository.save(tarjeta);
    }

    @Override
    public void deleteTarjeta(Long numeroTarjeta ){
        tarjetaRepository.delete(manager.find(Tarjeta.class,numeroTarjeta));}


    @Override
    public List<Tarjeta> findTarjetas() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Tarjeta> criteria = builder.createQuery(Tarjeta.class);
        Root<Tarjeta> root = criteria.from(Tarjeta.class);
        criteria.select(root);

        Query query = manager.createQuery(criteria);

        List<Tarjeta> tarjetas = query.getResultList();

        return tarjetas;
    }

    @Override
    public Optional<Tarjeta> findOneTarjeta(Long numeroTarjeta) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Tarjeta> criteria = builder.createQuery(Tarjeta.class);
        Root<Tarjeta> root =     criteria.from(Tarjeta.class);

        criteria.select(root);
        criteria.where(builder.equal(root.get("numeroTarjeta"), numeroTarjeta));
        return Optional.of(manager.createQuery(criteria).getSingleResult());
    }


    @Override
    public List<Tarjeta> findTarjetasByCuenta(Long numeroCuenta) {
        Cuenta cuentabd = manager.find(Cuenta.class,numeroCuenta);
        if (cuentabd != null) {
            return cuentabd.getListaTarjetas();
        }
        else{
            return null;
        }

    }

    @Override
    public Double getSaldoFecha(Long numeroTarjeta, LocalDate fechainicio, LocalDate fechafin) {
        //obtener la cuenta asociada y el saldo de la cuenta.
        Long numerocuenta = tarjetaRepository.getNumeroCuenta(numeroTarjeta);

        Double saldoFinal = cuentaRepository.getSaldoFecha(numerocuenta,fechainicio,fechafin);
        if(saldoFinal==null)
            saldoFinal = 0d;
        Double saldoInicial = cuentaRepository.getSaldo(numerocuenta);
        Double saldo= saldoInicial + saldoFinal;

        return saldo;
    }

}
