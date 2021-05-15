package com.ingenia.banca.controller;

import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.services.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    private final Logger log = LoggerFactory.getLogger(Usuario.class);

    private final UsuarioService userService;

    public UsuarioController(UsuarioService userService) {
        this.userService = userService;
    }


    /**
     * Crea un usuario en la BD
     * @param user
     * @return ResponseEntity<Usuario>
     * @throws URISyntaxException
     */
    @PostMapping("/users")
    @ApiOperation("Creación  de usuarios")
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario user) throws URISyntaxException {
        Usuario resultado=null;
        if (user.getId()!=null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultado= userService.saveUser(user);
        return  ResponseEntity.created(new URI("/api/users/"+resultado.getId())).body(resultado);
    }

    /**
     * metodo para modificar un Usuario de la BD
     * @param user
     * @return ResponseEntity<Usuario>
     */
    @PutMapping(value = "/users")
    @ApiOperation("Modificación  de usuarios")
    public ResponseEntity<Usuario> modifyUser(@RequestBody Usuario user) {
        log.debug("Modify Usuario");
        if (user.getNif()==null) {
            log.error("Error en Modify Usuario");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Usuario resultado = userService.saveUser(user);
        return ResponseEntity.ok().body(resultado);
    }

    /**
     * metodo que devuelve una lista con todos los usuarios de la BD
     * @return List<Usuario>
     */
    @GetMapping("/users")
    @ApiOperation("Encuentra todos los empleados sin paginación")
    public List<Usuario> findUsers(){
        log.debug("Rest request all Users");
        return userService.findAll();
    }

    /**
     * Metodo para Fitrar un usuario filtrando por ID
     * @param nif String
     * @return ResponseEntity<Usuario>
     */
    @GetMapping("/users/user/{nif}")
    public ResponseEntity<Usuario> findOneUser(@ApiParam("Clave primaria del usuario en formato nif: [[A-H][J-N][P-S]UVW][0-9]{7}[0-9A-J]")@PathVariable String nif) {
        log.debug("Rest request a Usuario with id: "+nif);
        Optional<Usuario> userOpt = userService.findUsuarioByNif(nif);
        if (userOpt.isPresent())
            return ResponseEntity.ok().body(userOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/users/{id}")
    @ApiOperation("Borrado de usuario por nif")
    public void deleteUser(@PathVariable Long id) {userService.delete(id);}


}
