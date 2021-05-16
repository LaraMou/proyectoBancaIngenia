package com.ingenia.banca.dao.impl;

import com.ingenia.banca.dao.MovimientoDAO;
import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MovimientoDAOImpl implements MovimientoDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public Movimiento createMovimiento(Movimiento movimiento){
        if(movimiento.getImporte()>0){
            System.out.println(movimiento);
//            System.out.println("numero"+ numerocuenta);
            //Query query =  manager.createQuery("select distinct c from Cuenta c join fetch c.listaMovimientos where c.numerocuenta = 1", Cuenta.class);
//            CriteriaBuilder builder = manager.getCriteriaBuilder();
//            CriteriaQuery<Cuenta> criteria = builder.createQuery(Cuenta.class);
//            Root<Cuenta> root = criteria.from(Cuenta.class);
//
//            criteria.select(root);
//            criteria.where(builder.equal(root.get("numerocuenta"),numerocuenta));
        }

        return movimientoRepository.save(movimiento);
    }

    @Override
    public Movimiento updateMovimiento(Movimiento movimiento){
        return movimientoRepository.save(movimiento);
    }

    @Override
    public void deleteMovimiento(Long id ){
       movimientoRepository.delete(manager.find(Movimiento.class,id));
    }

    @Override
    public List<Movimiento> findMovimientos() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Movimiento> criteria = builder.createQuery(Movimiento.class);
        Root<Movimiento> root = criteria.from(Movimiento.class);
        criteria.select(root);

        Query query =  manager.createQuery(criteria);

        List<Movimiento> movimientos = query.getResultList();

        return movimientos;
    }

    @Override
    public Optional<Movimiento> findOneMovimiento(Long id) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Movimiento> criteria = builder.createQuery(Movimiento.class);
        Root<Movimiento> root =     criteria.from(Movimiento.class);

        criteria.select(root);
        criteria.where(builder.equal(root.get("id"), id));
        return Optional.of(manager.createQuery(criteria).getSingleResult());

    }

    @Override
    public List<Movimiento> findAllMovimientoByNumerocuenta(Long numerocuenta) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Movimiento> criteria = builder.createQuery(Movimiento.class);
        Root<Movimiento> root =     criteria.from(Movimiento.class);

        criteria.select(root);
        criteria.where(builder.equal(root.get("numerocuenta"), numerocuenta));
        Query query =  manager.createQuery(criteria);
        List<Movimiento> movimientos = query.getResultList();
        System.out.println("mllll"+movimientos);
        return movimientos;
    }
//    //TODO MULTIWHERE
//    @Override
//    public List<Movimiento> findAllBetween(LocalDateTime fechainicio, LocalDateTime fechafin) {
////        CriteriaBuilder builder = manager.getCriteriaBuilder();
////        CriteriaQuery<Movimiento> criteria = builder.createQuery(Movimiento.class);
////        Root<Movimiento> root = criteria.from(Movimiento.class);
////        criteria.select(root);
////
////        Predicate dategreater = builder.gt(root("fechaValor"),fechainicio) // greater than
////        Predicate ageless30 = builder.lt(root.get("age"), 30); // less than
////
////        criteria.where(builder.and(agegreater20, ageless30));
////
////        criteria.where(builder.between()root.get("fechaValor"),fechainicio,fechafin);
////    }


}
