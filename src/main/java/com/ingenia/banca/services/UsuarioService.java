package com.ingenia.banca.services;

import com.ingenia.banca.model.Usuario;

import java.util.List;

public interface UsuarioService {
     List<Usuario> findAll();

     Usuario finById(Long id);

     Usuario saveUser(Usuario user);

     void deleteById(Long id);
}
