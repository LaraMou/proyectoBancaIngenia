package com.ingenia.banca.dao.impl;

import com.ingenia.banca.dao.TarjetaDAO;
import com.ingenia.banca.model.Tarjeta;
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
public class TarjetaDAOImpl implements TarjetaDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private TarjetaRepository tarjetaRepository;

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
        Query queryNative = manager.createNativeQuery("delete from tarjetas where umero_tarjeta =" + numeroTarjeta);
        queryNative.executeUpdate();
        tarjetaRepository.delete(manager.find(Tarjeta.class,numeroTarjeta));
    }

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
    public Tarjeta findOneTarjeta(Long numeroTarjeta) {return manager.find(Tarjeta.class,numeroTarjeta);}

}
