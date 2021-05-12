package com.ingenia.banca.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="importe")
    private Double importe;

    @Column(name="fecha")
    private LocalDateTime fecha;

    @Column(name="fecha_valor")
    private LocalDateTime fechaValor;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="concepto")
    private String concepto;

    @Column(name="historico_movimiento")
    @ElementCollection
    private Map<Double,String> historicoMovimiento ;

    public Movimiento() {
    }

    public Movimiento(Double importe, LocalDateTime fecha, LocalDateTime fechaValor, String descripcion, String concepto, Map<Double, String> historicoMovimiento) {
        this.importe = importe;
        this.fecha = fecha;
        this.fechaValor = fechaValor;
        this.descripcion = descripcion;
        this.concepto = concepto;
        this.historicoMovimiento = historicoMovimiento;
    }

    public Long getId() {
        return id;
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

    public Map<Double, String> getHistoricoMovimiento() {
        return historicoMovimiento;
    }

    public void setHistoricoMovimiento(Map<Double, String> historicoMovimiento) {
        this.historicoMovimiento = historicoMovimiento;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", importe=" + importe +
                ", fecha=" + fecha +
                ", fechaValor=" + fechaValor +
                ", descripcion='" + descripcion + '\'' +
                ", concepto='" + concepto + '\'' +
                ", historicoMovimiento=" + historicoMovimiento +
                '}';
    }
}
