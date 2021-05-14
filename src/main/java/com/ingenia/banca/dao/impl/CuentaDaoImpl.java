package com.ingenia.banca.dao.impl;

import com.ingenia.banca.dao.CuentaDao;
import com.ingenia.banca.model.Cuenta;
import com.sun.xml.bind.v2.TODO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;
@Repository
public class CuentaDaoImpl implements CuentaDao {
    @PersistenceContext
    private EntityManager manager;


    @Override
    public Optional<Cuenta> findCuentaByNumerocuenta(Long  numerocuenta) {
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
    // todo





}
