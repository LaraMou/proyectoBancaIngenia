package com.ingenia.banca.model;

import com.ingenia.banca.dao.UsuarioDao;
import com.ingenia.banca.repository.UsuarioRepository;
import com.ingenia.banca.services.UsuarioService;
import org.hibernate.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UsuarioTest {
    @PersistenceContext
    private EntityManager manager;
    @Mock
    UsuarioService usuarioService;
    @Mock
    UsuarioDao usuarioDao;
    @Mock
    Usuario usuario;




    @Test
    @DisplayName("Create and Find One User by Nif")
    public void Create() {
        Usuario usuario2 = new Usuario();
        usuario2.setNif("12345678B");
        usuario2.setFechanacimiento(LocalDate.of(2000,02,02));
        usuario2.setNombre("Juan");
        usuario2.setPrimerapellido("Valera");
        usuario2.setSegundoapellido("Ortiz");
        usuario2.setCiudad("Malaga");
        usuario2.setEmail("jv@imagina.com");
        usuario2.setInterviniente(Interviniente.AUTORIZADO);
        usuario2.setTelefono(666557788L);
        usuario2.setCreatedDate(Instant.now());

        usuarioService.saveUser(usuario2);
        usuario2.getNif();
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteria.from(Usuario.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("nif"),  usuario2.getNif()));
        Usuario item = manager.createQuery(criteria).getSingleResult();
        assertEquals("12345678b",item.getNif());
        manager.close();



    }


    @Test
    @DisplayName("update and Find One User by Nif")
    public void Upadte() {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteria.from(Usuario.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("nif"),  "12345678b"));
        Usuario item = manager.createQuery(criteria).getSingleResult();
        String ciudad = "Ciudad Real";
        String direccion = "C/ Santiago Bernabeu, 3";
        Long cp = 29000L;
        Long telefono = 654334455L;
        String ocupacion = "mis labores";

        if(item.getCiudad()!= ciudad)
            System.out.println("ciudad_antes"+ item.getCiudad());
            item.setCiudad(ciudad);

        if(item.getDireccion()!= direccion)
            System.out.println("direccion_antes"+ item.getDireccion());
            item.setDireccion(direccion);

        if(item.getCodigopostal()!= cp)
            System.out.println("cp_antes"+ item.getDireccion());
            item.setCodigopostal(cp);


        String email_nuevo = "nuevo@mail.to";
        if(item.getEmail()!= email_nuevo)
            System.out.println("email_antes"+ item.getEmail());
                item.setEmail("nuevo@mail.to");

        usuarioService.saveUser(usuario);
        manager.close();
        assertNotEquals("m@mailto.com",usuario.getEmail());
        usuarioDao.findUsuarioByNif(usuario.getNif());
        Optional<Usuario> usu = usuarioDao.findUsuarioByNif(usuario.getNif());
        System.out.println(usu);



    }




}
