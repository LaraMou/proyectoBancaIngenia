package com.ingenia.banca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave primaria. Long máx : 10")
    @Column(length=10)
    private Long numerocuenta;
    //datos cuenta IBAN
    @ApiModelProperty("Codigo IBAN: codigo pais: Long máx 2 ")
    @Column(length=2)
    private Long codpais;
    @ApiModelProperty("Codigo IBAN: digito control:Long máx 2 ")
    @Column(length=2)
    private Long digitocontrol;
    @ApiModelProperty("Codigo IBAN: entidad  Long máx : 4")
    @Column(length=4)
    private Long entidad;
    @ApiModelProperty("Codigo IBAN: oficina Long máx : 4")
    @Column(length=4)
    private Long oficina;


    private String tipocuenta;
    @NotNull
    private LocalDate fechaapertura;

    @ApiModelProperty("Formato Fecha")
    @NotNull
    private LocalDate fechaactual;
    @ApiModelProperty("Formato Fecha Contable: es aquella en que se apunta la operación,")
    @NotNull
    private LocalDate fechacontable;


    @ApiModelProperty("Boolean estado cuenta: Activo/Inactivo")
    private Estado estado;
    @ApiModelProperty("Double Saldo inicial")
    //@NotNull
    @Column(name= "saldo_inicial")
    private Double importeinicial;
    @ApiModelProperty("Double Saldo actual")
    @NotNull
    @Column(name= "saldo_actual")
    private Double importeactual;


    @OneToMany(mappedBy= "cuenta", cascade = CascadeType.ALL)
    @ApiModelProperty("Tarjetas en cada cuenta")
    private List<Tarjeta> listaTarjetas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta", cascade = CascadeType.ALL)
    @ApiModelProperty("Movimientos de la cuenta")
    @JsonIgnore
    private List<Movimiento> listaMovimientos;

    @ManyToMany(mappedBy = "cuentas", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Usuario> usuarios = new ArrayList<>();



    public Cuenta() {
    }

    public Cuenta( LocalDate fechaapertura, LocalDate fechaactual, LocalDate fechacontable, Estado estado, Double importeinicial, Double importeactual) {
        this.fechaapertura = fechaapertura;
        this.fechaactual = fechaactual;
        this.fechacontable = fechacontable;
        this.estado = estado;
        this.importeinicial = importeinicial;
        this.importeactual = importeactual;
    }



    public LocalDate getFechacontable() {
        return fechacontable;
    }

    public void setFechacontable(LocalDate fechacontable) {
        this.fechacontable = fechacontable;
    }

    public Long getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(Long num_cuenta) {
        this.numerocuenta = num_cuenta;
    }

    public LocalDate getFechaapertura() {
        return fechaapertura;
    }

    public void setFechaapertura(LocalDate fechaapertura) {
        this.fechaapertura = fechaapertura;
    }

    public LocalDate getFechaactual() {
        return fechaactual;
    }

    public void setFechaactual(LocalDate fechaactual) {
        this.fechaactual = fechaactual;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Double getImporteinicial() {
        return importeinicial;
    }

    public void setImporteinicial(Double saldoini) {
        this.importeinicial = saldoini;
    }

    public Double getImporteactual() {
        return importeactual;
    }

    public void setImporteactual(Double saldoactual) {
        this.importeactual = saldoactual;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Long getCodpais() {
        return codpais;
    }

    public void setCodpais(Long codpais) {
        this.codpais = codpais;
    }

    public Long getDigitocontrol() {
        return digitocontrol;
    }

    public void setDigitocontrol(Long digitocontrol) {
        this.digitocontrol = digitocontrol;
    }

    public Long getEntidad() {
        return entidad;
    }

    public void setEntidad(Long entidad) {
        this.entidad = entidad;
    }

    public Long getOficina() {
        return oficina;
    }

    public void setOficina(Long oficina) {
        this.oficina = oficina;
    }

    public String getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(String tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public List<Tarjeta> getListaTarjetas() {
        return listaTarjetas;
    }

    public void setListaTarjetas(List<Tarjeta> listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
    }

    public List<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(List<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numerocuenta=" + numerocuenta +
                ", fechaapertura=" + fechaapertura +
                ", fechaactual=" + fechaactual +
                ", fechacontable=" + fechacontable +
                ", estado=" + estado +
                ", importeinicial=" + importeinicial +
                ", importeactual=" + importeactual +
                '}';
    }
}
