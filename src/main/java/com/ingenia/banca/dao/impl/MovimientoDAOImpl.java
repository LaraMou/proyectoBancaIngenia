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
import java.time.LocalDate;
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
    public List<Movimiento> findMovimientosEntre(Long id, LocalDate fechaInicio, LocalDate fechaFin) {

        Query query = manager.createQuery("SELECT M FROM Movimiento M JOIN Cuenta C ON M.cuenta.numerocuenta = C.numerocuenta WHERE C.numerocuenta = "+id+" AND M.fecha BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"'");
        return query.getResultList();

    }

    @Override
    public List<Movimiento> getSumaCategoriaMes(Long numerocuenta, LocalDate fechainicio,LocalDate fechafin, Long idCat) {
        Query query = manager.createQuery("SELECT sum(importe) FROM Movimiento WHERE numerocuenta ="+numerocuenta+"AND fecha BETWEEN '"+fechainicio+"' AND '"+fechafin+"' AND categoria.id="+idCat+"");
        return query.getResultList();

    }


}
