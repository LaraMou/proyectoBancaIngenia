package com.ingenia.banca.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="numero_tarjeta")
    private Long numeroTarjeta;

    @Column(name="ccv"  , nullable = false)
    private Long ccv;

    @Column(name="fecha_expedicion" , nullable = false)
    private LocalDateTime fechaExpedicion;

    @Column(name="fecha_expiracion" , nullable = false)
    private LocalDateTime fechaExpiracion;

    @Column(name="tipo" , nullable = false)
    private String tipo;

    @Column(name="estado_tarjeta" , nullable = false)
    private String estadoTarjeta;

    @Column(name="limite")
    private Integer limite;

    public Tarjeta() {
    }

    public Tarjeta(Long ccv, LocalDateTime fechaExpedicion, LocalDateTime fechaExpiracion, String tipo, String estadoTarjeta, Integer limite) {
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

    public String getEstadoTarjeta() {
        return estadoTarjeta;
    }

    public void setEstadoTarjeta(String estadoTarjeta) {
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
