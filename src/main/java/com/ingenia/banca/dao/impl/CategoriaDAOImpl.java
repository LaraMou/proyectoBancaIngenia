package com.ingenia.banca.dao.impl;

import com.ingenia.banca.dao.CategoriaDAO;
import com.ingenia.banca.model.Categoria;
import com.ingenia.banca.repository.CategoriaRepository;
import com.ingenia.banca.repository.MovimientoRepository;
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

@Repository
public class CategoriaDAOImpl implements CategoriaDAO {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MovimientoDAOImpl movimientoDAO;

    @Override
    public List<Categoria> findAllByNombre(String nombre, Integer paginacion, Integer limite) {
        if(nombre!=null) {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<Categoria> criteriaQuery = criteriaBuilder.createQuery(Categoria.class);
            Root<Categoria> root = criteriaQuery.from(Categoria.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.like(root.get("nombre"), nombre + '%'));
            Query query = manager.createQuery(criteriaQuery);
            query.setMaxResults(limite);
            query.setFirstResult(paginacion);
            manager.close();
            return query.getResultList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Categoria> findAll( Integer limite,Integer paginacion) {

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Categoria> criteriaQuery = criteriaBuilder.createQuery(Categoria.class);
        Root<Categoria> root = criteriaQuery.from(Categoria.class);
        criteriaQuery.select(root);
        Query query = manager.createQuery(criteriaQuery);
        query.setMaxResults(limite);
        query.setFirstResult(paginacion);
        manager.close();
        return query.getResultList();

    }
}
