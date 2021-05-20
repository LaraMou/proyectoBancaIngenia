package com.ingenia.banca.controller;

import com.ingenia.banca.model.Cuenta;

import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.services.CuentaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> updateAccount(@RequestBody Cuenta cuenta , BindingResult result) {
        log.debug("Modify Cuenta");
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (cuenta.getNumerocuenta() == null) {
            log.error("Error en Modify Cuenta");
            response.put("mensaje", "Error: no se pudo editar, la cuenta numero: "
                    .concat(cuenta.getNumerocuenta().toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            Cuenta resultado = cuentaService.saveCuenta(cuenta);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la cuenta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La cuenta se ha atualizado con éxito!");
        response.put("cuenta", cuenta);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
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
    @ApiOperation("Borrado de cuenta por id")
    public ResponseEntity<?> deleteCuenta(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {

            cuentaService.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la cuenta de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente eliminado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

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

    @GetMapping("/accounts/saldo/{numerocuenta}")
    @ApiOperation("Obtiene Saldo actual ")
    public Double getSaldo(@ApiParam("Busqueda de movimientos entre dos fechas")@PathVariable Long numerocuenta) {
        log.debug("Rest request getSaldo " + numerocuenta);
        if (numerocuenta != null)
            cuentaService.getSaldo(numerocuenta);
        System.out.println(">>>>>>>saldo" + cuentaService.getSaldo(numerocuenta));
        return cuentaService.getSaldo(numerocuenta);
    }

    @GetMapping("/accounts/instant/{numerocuenta}")
    @ApiOperation("Obtiene saldo a fecha entrada")
    public Double getSaldoFecha(@ApiParam("Busqueda de movimientos entre dos fechas")@PathVariable Long numerocuenta,@RequestParam String fechaInicio) {
        log.debug("Rest request getSaldo " + numerocuenta);
        System.out.println("<<<<<<<<<<<<<<fecha"+fechaInicio);
        LocalDate localdate1 = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (numerocuenta !=null)
            cuentaService.getSaldoFecha(numerocuenta,localdate1);
        System.out.println(">>>>>>>saldo" + cuentaService.getSaldoFecha(numerocuenta,localdate1));
        return cuentaService.getSaldoFecha(numerocuenta,localdate1);
    }

}
