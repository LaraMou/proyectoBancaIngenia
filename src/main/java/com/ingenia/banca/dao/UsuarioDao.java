package com.ingenia.banca.dao;

import com.ingenia.banca.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDao {

    Optional<Usuario> findUsuarioByNif(String nif);

    List<Usuario> findAll(Integer paginacion, Integer limite);
    Optional<Usuario> findUsuarioByInterviniente(String interviniente);
    List<Usuario> findAllByInterviniente(String interviniente, Integer paginacion,Integer limite);



}
