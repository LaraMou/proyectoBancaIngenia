package com.ingenia.banca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @ApiModelProperty("Clave Primaria, Formato texto patter: [[A-H][J-N][P-S]UVW][0-9]{7}[0-9A-J]")
    private String nif;
    //Datos personales
    @ApiModelProperty("Formato texto No puede ser nulo")
    @NotNull
    @Column(nullable = false)
    private String nombre;
    @ApiModelProperty("Formato texto No puede ser nulo")
    @NotNull
    @Column(name="primerapellido", nullable = false)
    private String primerapellido;
    @ApiModelProperty("Formato textoo")
    @Column(name="segundoapellido")
    private String segundoapellido;

    @ApiModelProperty("Formato texto ")
    @Column(name="fecha_nacimiento")
    private LocalDate fechanacimiento;

    //Datos contacto
    @NotNull
    @ApiModelProperty("Formato Numerico. No puede ser nulo")
    @Column(name="telefono", nullable = false)
    private String telefono;

    @Email
    @ApiModelProperty("Formato texto. No puede ser nulo")
    @NotNull
    @Size(min = 5, max = 254)
    @Column(name="email",length = 254, unique = true)
    private String email;
    //Datos domicilio
    @NotNull
    @ApiModelProperty("Formato texto. No puede ser nulo")
    @Column(name="ciudad")
    private String ciudad;
    @NotNull
    @ApiModelProperty("Formato texto. No puede ser nulo")
    @Column(name="pais_origen")
    private String pais;
    //Otros datos

    @ApiModelProperty("Formato texto.Tipo Enum ")
    @Column(name="interviniente")
    private Interviniente interviniente;
    @ApiModelProperty("Formato texto")
    @Column(name="ocupacion")
    private String ocupacion;

    @ApiModelProperty("Formato texto")
    @Column(name="genero")
    private String genero;
    //TODO ENTIDAD A PARTE DOMICILIO

    /**
     * Auditory Attributes
     */
    @ApiModelProperty("Fecha creación entidad formato fecha. Autogenerado")
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    private Instant createdDate = Instant.now();
    @ApiModelProperty("Fecha ultima modifcación entidad formato fecha. Autogenerado ")
    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private Instant lastModifiedDate = Instant.now();


    @ManyToMany
    @ApiModelProperty("Entidad relacionada many to many cuentas")
    @JoinTable(
            name = "usuario_cuenta",
            joinColumns = {@JoinColumn(name="usuario_id", referencedColumnName = "nif")},
            inverseJoinColumns = {@JoinColumn(name="cuenta_id", referencedColumnName = "numero_cuenta")}
    )
    @JsonIgnoreProperties("usuarios")
    private List<Cuenta> cuentas = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nombre, String primerapellido, String segundoapellido, LocalDate fechanacimiento, String telefono, String email, Interviniente interviniente, Instant createdDate, Instant lastModifiedDate) {
        this.nombre = nombre;
        this.primerapellido = primerapellido;
        this.segundoapellido = segundoapellido;
        this.fechanacimiento = fechanacimiento;
        this.telefono = telefono;
        this.email = email;
        this.interviniente = interviniente;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Interviniente getInterviniente() {
        return interviniente;
    }

    public void setInterviniente(Interviniente interviniente) {
        this.interviniente = interviniente;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
