package com.ingenia.banca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="numero_tarjeta")
    @ApiModelProperty("Clave primaria tipo Long")
    private Long numeroTarjeta;

    @Column(name="ccv"  , nullable = false)
    @ApiModelProperty("Ccv tipo Long not nullable")
    private Long ccv;

    @Column(name="fecha_expedicion" , nullable = false)
    @ApiModelProperty("fechaExpedicion tipo LocalDateTime not nullable")
    private LocalDateTime fechaExpedicion;

    @Column(name="fecha_expiracion" , nullable = false)
    @ApiModelProperty("fecha_expiracion tipo LocalDateTime not nullable")
    private LocalDateTime fechaExpiracion;

    @Column(name="tipo" , nullable = false)
    @ApiModelProperty("tipo tipo String not nullable")
    private String tipo;

    @Column(name="estado_tarjeta" , nullable = false)
    @ApiModelProperty("estado_tarjeta tipo Estado not nullable")
    private Estado estadoTarjeta;

    @Column(name="limite")
    @ApiModelProperty("limite tipo Integer ")
    private Integer limite;

    @ManyToOne()
    @JoinColumn(name ="cuenta_id")
    @JsonIgnore
    @ApiModelProperty("Cuenta relativa a la tarjeta")
    private Cuenta cuenta;

    public Tarjeta() {
    }

    public Tarjeta(Long ccv, LocalDateTime fechaExpedicion, LocalDateTime fechaExpiracion, String tipo, Estado estadoTarjeta, Integer limite) {
        this.ccv = ccv;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaExpiracion = fechaExpiracion;
        this.tipo = tipo;
        this.estadoTarjeta = estadoTarjeta;
        this.limite = limite;
    }

    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public Long getCcv() {
        return ccv;
    }

    public void setCcv(Long ccv) {
        this.ccv = ccv;
    }

    public LocalDateTime getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDateTime fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Estado getEstadoTarjeta() {
        return estadoTarjeta;
    }

    public void setEstadoTarjeta(Estado estadoTarjeta) {
        this.estadoTarjeta = estadoTarjeta;
    }

    public Integer getLimite() {
        return limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "numeroTarjeta=" + numeroTarjeta +
                ", ccv=" + ccv +
                ", fechaExpedicion=" + fechaExpedicion +
                ", fechaExpiracion=" + fechaExpiracion +
                ", tipo='" + tipo + '\'' +
                ", estadoTarjeta='" + estadoTarjeta + '\'' +
                ", limite=" + limite +
                '}';
    }
}
