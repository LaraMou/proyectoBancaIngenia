package com.ingenia.banca.services;

import com.ingenia.banca.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
     List<Usuario> findAll();

     Optional<Usuario> findUsuarioByNif(String nif);

     Usuario saveUser(Usuario user);

     void deleteById(Long id);
}
