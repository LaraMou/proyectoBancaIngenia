package com.ingenia.banca.dao.impl;

import com.ingenia.banca.dao.CuentaDao;
import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.repository.CuentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class CuentaDaoImpl implements CuentaDao {
    @Autowired
    CuentaRepository cuentaRepository;
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
        Usuario usuarioDb = manager.find(Usuario.class,idUsuario);

        if(usuarioDb != null){
            return usuarioDb.getCuentas();
        }
        else{
            return new ArrayList<>();
        }
    }

//    @Override
//    public Double calcularSaldo(Long numerocuenta) {
//
//
//        Query query = manager.createQuery("SELECT SUM(importe) FROM Movimiento  WHERE cuenta.numerocuenta = "+numerocuenta+"");
//        return query.getSingleResult(Double);
//
//    }
    //TODO POR LAS FECHAS WHERE , FECHAVALO..


}
