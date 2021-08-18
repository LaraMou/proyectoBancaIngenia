package com.ingenia.banca.controller;

import com.ingenia.banca.model.Categoria;


import com.ingenia.banca.services.CategoriaService;
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
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final Logger log = LoggerFactory.getLogger(CategoriaController.class);

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    /**
     * Crea una categoria en la BD
     * @param categoria Object categoria
     * @return ResponseEntity<Categoria>
     * @throws URISyntaxException exception related URL
     */
    @PostMapping("/categorias")
    @ApiOperation("Create new categoria")
    public ResponseEntity<Categoria> createUser(@RequestBody Categoria categoria) throws URISyntaxException {
        log.debug("Create Categoria");
        Categoria resultado;
        if (categoria.getId()!=null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultado= categoriaService.saveCategoria(categoria);
        return  ResponseEntity.created(new URI("/api/users/"+resultado.getId())).body(resultado);
    }

    /**
     * metodo para modificar un Categoria de la BD
     * @param categoria  Categoria
     * @return ResponseEntity<Categoria>
     */
    @PutMapping(value = "/categorias")
    @ApiOperation("Modificación  de categorias")
    public ResponseEntity<Categoria> modifyUser(@RequestBody Categoria categoria) {
        log.debug("Modify Categoria");
        if (categoria.getId()==null) {
            log.error("Error en Modify Categoria");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Categoria resultado = categoriaService.saveCategoria(categoria);
        return ResponseEntity.ok().body(resultado);
    }

    /**
     * Obtener categorias por filtros
     * @param nombre String
     * @param limit int
     * @param page int
     * @return List categoria
     */
    @GetMapping("/categorias")
    public List<Categoria> index(@RequestParam(name = "nombre", required = false) String nombre,
                                @RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit,
                                @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        if(nombre!=null){
            return categoriaService.findAllByNombre(nombre,page,limit);
        }else{
            return categoriaService.findAllCategorias(page,limit);
        }
    }


    @GetMapping("/categorias/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Categoria> findOneCategory(@ApiParam("Clave primaria de la categoria Long ")@PathVariable Long id) {
        log.debug("Rest request a Categoria with id: "+id);
        Optional<Categoria> categoOpt = categoriaService.findById(id);
        if (categoOpt.isPresent())
            return ResponseEntity.ok().body(categoOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/categorias/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Categoria> categoria = categoriaService.findById(id);
          categoria.ifPresent(value -> categoriaService.deleteById(id));

//            if(categoria.isPresent())
//                categoriaService.deleteById(id);
        } catch (DataAccessException e) {
             response.put("mensaje", "Error al eliminar la etiqueta de la base de datos");
             /*
             para evitar este bug se elimina el mensaje que debería de mostrar
              */
//            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Categoria eliminada con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


}
