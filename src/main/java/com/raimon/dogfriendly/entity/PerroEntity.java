package com.raimon.dogfriendly.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "perro")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PerroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime fechaNacimiento;

    private int genero;
    private Long imagen;
    private int peso;
    private boolean sociable;
    private boolean puedeIrSuelto;
    private boolean esJugueton;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_raza")
    private RazaEntity razas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

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

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public Long getImagen() {
        return imagen;
    }

    public void setImagen(Long imagen) {
        this.imagen = imagen;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isSociable() {
        return sociable;
    }

    public void setSociable(boolean sociable) {
        this.sociable = sociable;
    }

    public boolean isPuedeIrSuelto() {
        return puedeIrSuelto;
    }

    public void setPuedeIrSuelto(boolean puedeIrSuelto) {
        this.puedeIrSuelto = puedeIrSuelto;
    }

    public boolean isEsJugueton() {
        return esJugueton;
    }

    public void setEsJugueton(boolean esJugueton) {
        this.esJugueton = esJugueton;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public RazaEntity getRazas() {
        return razas;
    }

    public void setRazas(RazaEntity razas) {
        this.razas = razas;
    }

}
