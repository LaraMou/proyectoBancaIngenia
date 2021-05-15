package com.ingenia.banca.services.impl;

import com.ingenia.banca.dao.CategoriaDAO;
import com.ingenia.banca.model.Categoria;

import com.ingenia.banca.repository.CategoriaRepository;
import com.ingenia.banca.services.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    private final CategoriaRepository categoriaRepository;
    private final CategoriaDAO categoriaDAO;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaDAO categoriaDAO) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaDAO = categoriaDAO;
    }


    @Override
    public List<Categoria> findAllCategorias(Integer paginacion, Integer limite) {
        return categoriaDAO.findAll(paginacion, limite);

    }

    @Override
    public List<Categoria> findAllByNombre(String nombre, Integer paginacion, Integer limite) {
        return categoriaDAO.findAllByNombre(nombre,paginacion,limite);
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        log.info("findOneById");
        if(id!=null)
            return categoriaRepository.findById(id);
        return Optional.empty();
    }

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        log.info("createTag");
        if(ObjectUtils.isEmpty(categoria))
            return null;

        return categoriaRepository.save(categoria);
    }


    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }
}
