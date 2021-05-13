package com.ingenia.banca.services;

import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario finById(Long id) {
        return null;
    }

    @Override
    public Usuario saveUser(Usuario user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
