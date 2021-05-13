package com.ingenia.banca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave primaria Long")
    private Long id;

    @ApiModelProperty("Double importe movimiento")
    @Column(name="importe" , nullable = false)
    private Double importe;

    @Column(name="fecha" , nullable = false)
    @ApiModelProperty("Formato tipo fecha:yyyy-mm-dd-hh-mm")
    private LocalDateTime fecha;

    @Column(name="fecha_valor" , nullable = false)
    @ApiModelProperty("Formato tipo fecha:yyyy-mm-dd-hh-mm")
    private LocalDateTime fechaValor;

    @Column(name="descripcion")
    @ApiModelProperty("Formato texto")
    private String descripcion;
    @ApiModelProperty("Formato texto")
    @Column(name="concepto")
    private String concepto;

    /**
     * Relación Categorias y movimientos n-n
     */
    @ManyToMany
    @ApiModelProperty("Entidad relacionada many to many categorias")
    @JoinTable(
            name = "movimiento_categoria",
            joinColumns = {@JoinColumn(name="movimiento_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="categoria_id", referencedColumnName = "id")}
    )
    @JsonIgnoreProperties("movimientos")
    private List<Categoria> categorias = new ArrayList<>();

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "cuenta_id")
    @ApiModelProperty("Cuenta relativa al movimiento")
    private Cuenta cuenta;

    public Movimiento() {
    }

    public Movimiento(Double importe, LocalDateTime fecha, LocalDateTime fechaValor, String descripcion, String concepto) {
        this.importe = importe;
        this.fecha = fecha;
        this.fechaValor = fechaValor;
        this.descripcion = descripcion;
        this.concepto = concepto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getFechaValor() {
        return fechaValor;
    }

    public void setFechaValor(LocalDateTime fechaValor) {
        this.fechaValor = fechaValor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
