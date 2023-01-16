package com.raimon.dogfriendly.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "raza")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RazaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tamanyo;
/* 
    @OneToMany(mappedBy = "raza", fetch = FetchType.LAZY)
    private final List<PerroEntity> perros;

    public RazaEntity() {
        this.perros = new ArrayList<>();
    }

    public RazaEntity(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.perros = new ArrayList<>();
    } */

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

    public String getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(String tamanyo) {
        this.tamanyo = tamanyo;
    }

/*     public int getPerros() {
        return perros.size();
    } */

}
