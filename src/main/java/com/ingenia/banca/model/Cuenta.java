package com.ingenia.banca.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Cuenta {
    @Id
    @ApiModelProperty("Clave primaria. Tipo long")
    private Long num_cuenta;

    @ApiModelProperty("Formato Fecha")
    @NotNull
    private LocalDate fechaapertura;

    @ApiModelProperty("Boolean estado cuenta: Activo/Inactivo")
    @NotNull
    private Boolean activo;
    @ApiModelProperty("Double Saldo inicial")
    @NotNull
    @Column(name= "saldo_inicial")
    private Double saldoini;
    @ApiModelProperty("Double Saldo actual")
    @NotNull
    @Column(name= "saldo_actual")
    private Double saldoactual;
    @ApiModelProperty("Listado transaccion")
    //TODO
    //

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

    public LocalDate getFechaapertura() {
        return fechaapertura;
    }

    public void setFechaapertura(LocalDate fechaapertura) {
        this.fechaapertura = fechaapertura;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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
