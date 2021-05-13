package com.ingenia.banca.model;

import com.ingenia.banca.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UsuarioTest {
    @Mock
    UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Create User")
    void create(){
        Usuario usuario = new Usuario();
        usuario.setNif("12345678B");
        usuario.setFechanacimiento(LocalDate.of(2000,02,02));
        usuario.setNombre("Juan");
        usuario.setPrimerapellido("Valera");
        usuario.setSegundoapellido("Ortiz");
        usuario.setCiudad("Malaga");
        usuario.setEmail("jv@imagina.com");
        usuario.setInterviniente(Interviniente.AUTORIZADO);
        usuario.setTelefono(666557788L);
        usuario.setCreatedDate(Instant.now());
        usuarioRepository.save(usuario);
        List<Usuario> usuarios = usuarioRepository.findAll();
        Optional<Usuario> usuarioDB = usuarioRepository.findById(1l);
        assertEquals(2,usuarios.size());
        assertTrue(usuarioDB.isPresent());

    }


}
