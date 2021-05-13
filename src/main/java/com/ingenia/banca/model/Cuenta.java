package com.ingenia.banca.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Cuenta {
    @Id
    @ApiModelProperty("Clave primaria. Tipo long")
    private Long numerocuenta;


    @ApiModelProperty("Formato Fecha")
    @NotNull
    private LocalDateTime fechaapertura;

    @ApiModelProperty("Formato Fecha")
    @NotNull
    private LocalDateTime fechaactual;
    @ApiModelProperty("Formato Fecha Contable: es aquella en que se apunta la operación,")
    @NotNull
    private LocalDateTime fechacontable;


    @ApiModelProperty("Boolean estado cuenta: Activo/Inactivo")
    private Estado estado;
    @ApiModelProperty("Double Saldo inicial")
    @NotNull
    @Column(name= "saldo_inicial", nullable = false)
    private Double importeinicial;
    @ApiModelProperty("Double Saldo actual")
    @NotNull
    @Column(name= "saldo_actual",nullable = false)
    private Double importeactual;


    @ManyToMany(mappedBy = "cuentas", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Usuario> usuarios = new ArrayList<>();

    public Cuenta() {
    }

    public Cuenta(Long numerocuenta, LocalDateTime fechaapertura, LocalDateTime fechaactual, LocalDateTime fechacontable, Estado estado, Double importeinicial, Double importeactual) {
        this.numerocuenta = numerocuenta;
        this.fechaapertura = fechaapertura;
        this.fechaactual = fechaactual;
        this.fechacontable = fechacontable;
        this.estado = estado;
        this.importeinicial = importeinicial;
        this.importeactual = importeactual;
    }

    public LocalDateTime getFechacontable() {
        return fechacontable;
    }

    public void setFechacontable(LocalDateTime fechacontable) {
        this.fechacontable = fechacontable;
    }

    public Long getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(Long num_cuenta) {
        this.numerocuenta = num_cuenta;
    }

    public LocalDateTime getFechaapertura() {
        return fechaapertura;
    }

    public void setFechaapertura(LocalDateTime fechaapertura) {
        this.fechaapertura = fechaapertura;
    }

    public LocalDateTime getFechaactual() {
        return fechaactual;
    }

    public void setFechaactual(LocalDateTime fechaactual) {
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
