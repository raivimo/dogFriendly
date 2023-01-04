package com.raimon.dogfriendly.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "tipopaseo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipopaseoEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int duracion;
    
    @OneToMany(mappedBy = "tipopaseo", fetch = FetchType.LAZY)
    private final List<PaseoEntity> paseos;

    public TipopaseoEntity(){
        this.paseos = new ArrayList<>();
    }

    public TipopaseoEntity(Long id, String nombre){
        this.id = id;
        this.nombre = nombre;
        this.paseos = new ArrayList<>();

    }

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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getPaseos() {
        return paseos.size();
    }





   








    
}
