package com.ingenia.banca.dao.impl;

import com.ingenia.banca.dao.UsuarioDao;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Usuario;

import com.ingenia.banca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public Optional<Usuario> findUsuarioByNif(String nif) {
        if (nif != null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("nif"), nif));
            Usuario item = manager.createQuery(criteria).getSingleResult();
            System.out.println(item.getNif());
            manager.close();
            return Optional.of(item);

        }
        return Optional.empty();
    }

    @Override
    public void deleteUsuario(String nif) {
        Query queryNative = manager.createNativeQuery("delete from usuario where nif =" +nif);
        queryNative.executeUpdate();
        usuarioRepository.delete(manager.find(Usuario.class,nif));
    }


}
