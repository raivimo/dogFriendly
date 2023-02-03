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
@Table(name = "tipousuario")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TipousuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "tipousuario", fetch = FetchType.LAZY)
    private final List<UsuarioEntity> usuarios;

    public TipousuarioEntity() {
        this.usuarios = new ArrayList<>();
    }

    public TipousuarioEntity(Long id, String nombre) {
        this.usuarios = new ArrayList<>();
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getUsuarios() {
        return usuarios.size();
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
