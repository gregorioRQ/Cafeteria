
package com.mycompany.integrador_02.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private String descripcion;
    private String precio;
    private boolean estaDisponible;
    private String categoria;
    @ManyToOne
    @JoinColumn(name="fk_pedidos")
    private Pedido unPedido;
    @ManyToOne
    @JoinColumn(name ="fk_barista")
    private Barista unBarista;
 

    public Producto() {
    }

    public Producto(int id, String nombre, String descripcion, String precio, boolean estaDisponible, String categoria, Pedido unPedido, Barista unBarista) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estaDisponible = estaDisponible;
        this.categoria = categoria;
        this.unPedido = unPedido;
        this.unBarista = unBarista;
    }

    public Pedido getUnPedido() {
        return unPedido;
    }

    public void setUnPedido(Pedido unPedido) {
        this.unPedido = unPedido;
    }

    public Barista getUnBarista() {
        return unBarista;
    }

    public void setUnBarista(Barista unBarista) {
        this.unBarista = unBarista;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public boolean isEstaDisponible() {
        return estaDisponible;
    }

    public void setEstaDisponible(boolean estaDisponible) {
        this.estaDisponible = estaDisponible;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


}
