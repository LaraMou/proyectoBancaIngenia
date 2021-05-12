package com.ingenia.banca.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Cuenta {
    @Id
    @ApiModelProperty("Clave primaria. Tipo long")
    private Long num_cuenta;


    @ApiModelProperty("Formato Fecha")
    @NotNull
    private LocalDateTime fechaapertura;

    @ApiModelProperty("Formato Fecha")
    @NotNull
    private LocalDateTime fechaactual;

    @ApiModelProperty("Boolean estado cuenta: Activo/Inactivo")
    private Estado estado;
    @ApiModelProperty("Double Saldo inicial")
    @NotNull
    @Column(name= "saldo_inicial")
    private Double saldoini;
    @ApiModelProperty("Double Saldo actual")
    @NotNull
    @Column(name= "saldo_actual")
    private Double saldoactual;


    @ManyToMany(mappedBy = "cuentas", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Usuario> usuarios = new ArrayList<>();

    public Cuenta() {
    }


    public Long getNum_cuenta() {
        return num_cuenta;
    }

    public void setNum_cuenta(Long num_cuenta) {
        this.num_cuenta = num_cuenta;
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

    public Double getSaldoini() {
        return saldoini;
    }

    public void setSaldoini(Double saldoini) {
        this.saldoini = saldoini;
    }

    public Double getSaldoactual() {
        return saldoactual;
    }

    public void setSaldoactual(Double saldoactual) {
        this.saldoactual = saldoactual;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
