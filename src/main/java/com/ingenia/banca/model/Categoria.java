package com.ingenia.banca.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave primaria tipo Long")
    private Long id;

    @Column(name = "nombre" , nullable = false, unique = true)
    @ApiModelProperty("Formato texto no nulo")
    private String nombre;


    @ApiModelProperty("Listado cagetorias relacionadas movimiento")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos = new ArrayList<>();

    public Categoria() {
    }


    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
//
//    public List<Movimiento> getMovimientos() {
//        return movimientos;
//    }
//
//    public void setMovimientos(List<Movimiento> movimientos) {
//        this.movimientos = movimientos;
//    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
