package com.ingenia.banca.repository;

import com.ingenia.banca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>, CrudRepository<Usuario, Long> {

}
