package com.ingenia.banca.controller;

import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.services.TarjetaService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TarjetaController {

    private TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService){
        this.tarjetaService=tarjetaService;
    }
    private final Logger log = LoggerFactory.getLogger(Tarjeta.class);
    /**
     * Crea una Tarjeta en la BD
     * @param tarjeta
     * @return ResponseEntity<Tarjeta>
     * @throws URISyntaxException
     */

    @PostMapping("/tarjetas")
    @ApiOperation("Creación de una tarjeta")
    public ResponseEntity<Tarjeta> createTarjeta(@RequestBody Tarjeta tarjeta) throws URISyntaxException {
        log.debug("Create Tarjeta");
        if(tarjeta.getNumeroTarjeta()!=null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            Tarjeta tarjetaCreada = tarjetaService.createTarjeta(tarjeta);
            return ResponseEntity.created(new URI("/api/tarjetas" + tarjetaCreada.getNumeroTarjeta())).body(tarjetaCreada);
        }
    }

    /**
     * metodo para modificar una tarjeta de la BD
     * @param tarjeta
     * @return ResponseEntity<Tarjeta>
     */

    @PutMapping("/tarjetas/{numerotarjeta}")
    @ApiOperation("Modificación de tarjetas")
    public ResponseEntity<Tarjeta>updateTarjeta(@RequestBody Tarjeta tarjeta) {
        log.debug("Modify Tarjeta");
        if(tarjeta.getNumeroTarjeta()==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return ResponseEntity.ok().body(tarjetaService.updateTarjeta(tarjeta));
        }
    }

    @DeleteMapping("tarjetas/{numeroTarjeta}")
    @ApiOperation("Delete de una tarjeta")
    public void deleteTarjeta(@PathVariable Long numeroTarjeta) {tarjetaService.deleteTarjeta(numeroTarjeta);}


    /**
     * metodo que devuelve una lista con todas las tarjetas de la BD
     * @return List<Tarjeta>
     */

    @GetMapping("/tarjetas")
    @ApiOperation("Encuentra todas las tarjetas sin paginación")
    public List<Tarjeta> findTarjetas(){
        log.debug("Rest request all Tarjetas");
        return tarjetaService.findTarjetas();
    }

    /**
     * Metodo para Fitrar un usuario filtrando por ID
     * @param numeroTarjeta Long
     * @return ResponseEntity<Usuario>
     */
    @GetMapping("/tarjetas/{numeroTarjeta}")
    public ResponseEntity<Tarjeta> findOneTarjeta(@ApiParam("Clave primaria de la tarjeta")@PathVariable Long numeroTarjeta) {
        log.debug("Rest request a Tarjeta with id: "+ numeroTarjeta);
        Optional<Tarjeta> tarjetaOpt = tarjetaService.findOneTarjeta(numeroTarjeta);
        if (tarjetaOpt.isPresent())
            return ResponseEntity.ok().body(tarjetaOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tarjetas/cuenta/{numeroTarjeta}")
    public ResponseEntity<List<Tarjeta>> findTarjetasByCuenta(@ApiParam("Clave primaria de la tarjeta")@RequestParam Long numeroCuenta) {
        log.debug("Rest request to find all cards in Cuenta: "+ numeroCuenta);
        List<Tarjeta> listTarjetas = tarjetaService.findTarjetasByCuenta(numeroCuenta);
        if (listTarjetas != null)
            return ResponseEntity.ok().body(listTarjetas);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tarjetas/instant/{numeroTarjeta}")
    @ApiOperation("Obtiene Saldo actual en cuenta de la tarjeta asociada")
    public ResponseEntity<?> getSaldo(@ApiParam("Busqueda de movimientos entre dos fechas") @PathVariable Long numeroTarjeta, @RequestParam String fechaInicio, @RequestParam String fechaFin) {
        Map<String, Double> response = new HashMap<>();
        log.debug("Rest request getSaldo tarjeta " + numeroTarjeta);

        LocalDate localdate1 = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate localdate2 = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try{

            Optional<Tarjeta> tarjeta = tarjetaService.findOneTarjeta(numeroTarjeta);
            if (tarjeta.isPresent()){
                Double saldo = tarjetaService.getSaldo(numeroTarjeta,localdate1,localdate2);
                response.put("Saldo actual", saldo);
                return new ResponseEntity<Map<String,Double>>( response,HttpStatus.OK);
            }
            else {
                response.put("El número de cuenta : ".concat(numeroTarjeta.toString().concat(" no existe en la base de datos!")),0d);
                return new ResponseEntity<Map<String,Double>>( response,HttpStatus.NOT_FOUND);
            }

        } catch(DataAccessException e) {
            response.put( "Error al realizar la consulta en la base de datos",0d);
            response.put( e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()),0d);
            return new ResponseEntity<Map<String,Double>>( response,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
