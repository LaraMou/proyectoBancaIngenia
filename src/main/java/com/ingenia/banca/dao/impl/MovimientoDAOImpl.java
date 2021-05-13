package com.ingenia.banca.dao.impl;

import com.ingenia.banca.dao.MovimientoDAO;
import com.ingenia.banca.dao.TarjetaDAO;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.repository.MovimientoRepository;
import com.ingenia.banca.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
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
        Query queryNative = manager.createNativeQuery("delete from movimientos where id =" + id);
        queryNative.executeUpdate();
        movimientoRepository.delete(manager.find(Movimiento.class,id));
    }

    @Override
    public List<Movimiento> findMovimientos() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Movimiento> criteria = builder.createQuery(Movimiento.class);
        Root<Movimiento> root = criteria.from(Movimiento.class);
        criteria.select(root);

        Query query = manager.createQuery(criteria);

        List<Movimiento> movimientos = query.getResultList();

        return movimientos;
    }

    @Override
    public Movimiento findOneMovimiento(Long id) {return manager.find(Movimiento.class,id);}

}
