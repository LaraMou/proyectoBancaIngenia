package com.ingenia.banca.services.impl;

import com.ingenia.banca.dao.UsuarioDao;
import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.repository.UsuarioRepository;
import com.ingenia.banca.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    private final UsuarioRepository usuarioRepository;
    private final UsuarioDao usuarioDao;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioDao usuarioDao) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioDao = usuarioDao;
    }


    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findUsuarioByNif(String nif) {
        log.info("findOneUserById");
        if(nif!=null)
            return usuarioDao.findUsuarioByNif(nif);
        return Optional.empty();
    }


    @Override
    public Usuario saveUser(Usuario user) {
        log.info("Save user Update or Create");
        if(ObjectUtils.isEmpty(user))
            return null;

        return usuarioRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);

    }

}
