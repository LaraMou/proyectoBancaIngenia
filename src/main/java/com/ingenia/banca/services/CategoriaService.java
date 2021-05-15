package com.ingenia.banca.services;


import com.ingenia.banca.model.Categoria;


import java.util.List;
import java.util.Optional;

public interface CategoriaService {

     List<Categoria> findAllCategorias(Integer paginacion,Integer limite);

     List<Categoria> findAllByNombre(String nombre, Integer paginacion,Integer limite);

     Optional<Categoria> findById(Long id);

     Categoria saveCategoria(Categoria categoria);
     void deleteById(Long id);
}
