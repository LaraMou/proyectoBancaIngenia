package com.ingenia.banca.controller;

import com.ingenia.banca.model.Cuenta;

import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.services.CuentaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class CuentaController {
    private final CuentaService cuentaService;
    private final Logger log = LoggerFactory.getLogger(Cuenta.class);

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }


    /**
     * Crea una cuenta en la BD
     * @param cuenta Object cuenta
     * @return ResponseEntity<Cuenta>
     * @throws URISyntaxException exception related URL
     */
    @PostMapping("/accounts")
    @ApiOperation("Create new cuenta")
    public ResponseEntity<Cuenta> createUser(@RequestBody Cuenta cuenta) throws URISyntaxException {
        log.debug("Create Cuenta");
        Cuenta resultado;
        if (cuenta.getNumerocuenta()!=null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultado= cuentaService.saveCuenta(cuenta);
        return  ResponseEntity.created(new URI("/api/users/"+resultado.getNumerocuenta())).body(resultado);
    }

    /**
     * metodo para modificar un Cuenta de la BD
     * @param cuenta  Cuenta
     * @return ResponseEntity<Cuenta>
     */
    @PutMapping(value = "/accounts")
    @ApiOperation("Modificación  de cuentas")
    public ResponseEntity<Cuenta> modifyUser(@RequestBody Cuenta cuenta) {
        log.debug("Modify Cuenta");
        if (cuenta.getNumerocuenta()==null) {
            log.error("Error en Modify Cuenta");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Cuenta resultado = cuentaService.saveCuenta(cuenta);
        return ResponseEntity.ok().body(resultado);
    }

    /**
     * metodo que devuelve una lista con todos los usuarios de la BD
     * @return List<Cuenta>
     */
    @GetMapping("/accounts")
    @ApiOperation("Encuentra todas las cuentas sin paginación")
    public List<Cuenta> findAccounts(){
        log.debug("Rest request all Accoutns");
        return cuentaService.findAll();
    }

    /**
     * Metodo para Fitrar un usuario filtrando por ID
     * @param numerocuenta Long
     * @return ResponseEntity<Cuenta>
     */
    @GetMapping("/accounts/{numerocuenta}")
    public ResponseEntity<Cuenta> findOneAccount(@ApiParam("Clave primaria de la cuenta Long size 10")@PathVariable Long numerocuenta) {
        log.debug("Rest request a Cuenta with id: "+numerocuenta);
        Optional<Cuenta> userOpt = cuentaService.findCuentaByNumerocuenta(numerocuenta);
        if (userOpt.isPresent())
            return ResponseEntity.ok().body(userOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/accounts/{id}")
    @ApiOperation("Borrado de categoria por id")
    public void deleteCategoria(@PathVariable Long id) {cuentaService.deleteById(id);}


    @GetMapping("/accounts/user/{idUsuario}")
    @ApiOperation("Encuentra todas las cuentas de un usuario")
    public ResponseEntity<List<Cuenta>> findAccountsByUsuario(@PathVariable Long idUsuario){
        log.debug("Rest request All cuentas with user id: "+ idUsuario );
        List<Cuenta> cuentasList = cuentaService.findAccountsByUsuario(idUsuario);
        if(cuentasList!= null){
            return ResponseEntity.ok().body(cuentasList);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
