package com.ingenia.banca.controller;

import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.services.MovimientoService;
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
public class MovimientoController {

    private MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService){
        this.movimientoService=movimientoService;
    }
    private final Logger log = LoggerFactory.getLogger(Movimiento.class);

    @PostMapping("/movimientos")
    @ApiOperation("Creación de un movimiento")
    public ResponseEntity<Movimiento> createMovimiento(@RequestBody Movimiento movimiento) throws URISyntaxException {
        log.debug("Create Movimiento");
        if(movimiento.getId()!=null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            Movimiento movimientoCreado = movimientoService.createMovimiento(movimiento);
            return ResponseEntity.created(new URI("/api/movimientos" + movimientoCreado.getId())).body(movimientoCreado);
        }
    }


    @PutMapping("/movimientos")
    @ApiOperation("Modificación de movimientos")
    public ResponseEntity<Movimiento>updateMovimiento(@RequestBody Movimiento movimiento) {
        log.debug("Modify Movimiento");
        if(movimiento.getId()==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            Movimiento movimientoCreado = movimientoService.updateMovimiento(movimiento);
            return ResponseEntity.ok().body(movimientoCreado);
        }
    }

    @DeleteMapping("movimientos/{id}")
    @ApiOperation("Delete de un movimiento")
    public void deleteMovimiento(@PathVariable Long id) {movimientoService.deleteMovimiento(id);}


    @GetMapping("/movimientos")
    @ApiOperation("Encuentra todos los movimientos sin paginación")
    public List<Movimiento> findMovimientos(){
        log.debug("Rest request all Movimientos");
        return movimientoService.findMovimientos();
    }


    @GetMapping("/movimientos/{id}")
    public ResponseEntity<Movimiento> findOneMovimiento(@ApiParam("Clave primaria del Movimiento")@PathVariable Long id) {
        log.debug("Rest request a Movimiento with id: "+ id);
        Optional<Movimiento> movimientoOpt = Optional.ofNullable(movimientoService.findOneMovimiento(id));
        if (movimientoOpt.isPresent())
            return ResponseEntity.ok().body(movimientoOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





}