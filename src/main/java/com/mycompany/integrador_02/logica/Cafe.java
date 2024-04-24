
package com.mycompany.integrador_02.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Cafe implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String nombre;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name="fk_barista")
    private Barista unBarista;
     @OneToOne
    @JoinColumn(name="fk_producto")
    private Producto unProducto;

    public Cafe() {
    }

    public Cafe(int id, String nombre, String descripcion, Barista unBarista, Producto unProducto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unBarista = unBarista;
        this.unProducto = unProducto;
    }

    public Producto getUnProducto() {
        return unProducto;
    }

    public void setUnProducto(Producto unProducto) {
        this.unProducto = unProducto;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Barista getUnBarista() {
        return unBarista;
    }

    public void setUnBarista(Barista unBarista) {
        this.unBarista = unBarista;
    }
    
    
}
