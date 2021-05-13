package com.ingenia.banca.dao.impl;

import com.ingenia.banca.dao.UsuarioDao;
import com.ingenia.banca.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public Optional<Usuario> findUsuarioByNif(String nif) {
        if (nif != null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("nif"), nif));
            Usuario item = manager.createQuery(criteria).getSingleResult();
            manager.close();
            return Optional.of(item);

        }
        return Optional.empty();
    }
}
