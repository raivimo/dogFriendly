package com.raimon.dogfriendly.entity;


import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "paseo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaseoEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private String lugar;
    private double precio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipopaseo")
    private TipopaseoEntity tipopaseo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_perro")
    private PerroEntity perro;

    @OneToMany(mappedBy = "paseo", fetch = FetchType.LAZY)
    private final List<FacturaEntity> facturas;


    public PaseoEntity(){
        this.facturas = new ArrayList<>();
    }

    public PaseoEntity(Long id, String lugar){
        this.id = id;
        this.lugar = lugar;
        this.facturas = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getFecha() {
        return fecha;
    }


    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public String getLugar() {
        return lugar;
    }


    public void setLugar(String lugar) {
        this.lugar = lugar;
    }


    public double getPrecio() {
        return precio;
    }


    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public TipopaseoEntity getTipopaseo() {
        return tipopaseo;
    }


    public void setTipopaseo(TipopaseoEntity tipopaseo) {
        this.tipopaseo = tipopaseo;
    }


    public UsuarioEntity getUsuario() {
        return usuario;
    }


    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }


    public PerroEntity getPerro() {
        return perro;
    }


    public void setPerro(PerroEntity perro) {
        this.perro = perro;
    }


    public int getFacturas() {
        return facturas.size();
    }



   








    
}
