package com.ingenia.banca.dao;

import com.ingenia.banca.model.Usuario;

import java.util.Optional;

public interface UsuarioDao {

    Optional<Usuario> findUsuarioByNif(String nif);

     void deleteUsuario(String nif);


}
