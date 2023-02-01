package com.raimon.dogfriendly.entity;


import java.time.LocalDate;
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
@Table(name = "factura")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FacturaEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
    private LocalDate fecha;

    private int iva;
    private boolean pagado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paseo")
    private PaseoEntity paseo;

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

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public PaseoEntity getPaseo() {
        return paseo;
    }

    public void setPaseo(PaseoEntity paseo) {
        this.paseo = paseo;
    }

    



 


    



    
}
