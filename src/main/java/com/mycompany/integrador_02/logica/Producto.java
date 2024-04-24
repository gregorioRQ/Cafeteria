
package com.mycompany.integrador_02.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    @ManyToMany(mappedBy="productos")
    private List<Pedido> pedidos;
    @ManyToOne
    @JoinColumn(name ="fk_barista")
    private Barista unBarista;
    @OneToOne(mappedBy="unProducto")
    private Cafe unCafe;

    public Producto() {
    }

    public Producto(int id, String nombre, String descripcion, String precio, boolean estaDisponible, String categoria, List<Pedido> pedidos, Barista unBarista, Cafe unCafe) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estaDisponible = estaDisponible;
        this.categoria = categoria;
        this.pedidos = pedidos;
        this.unBarista = unBarista;
        this.unCafe = unCafe;
    }

 

    public Cafe getUnCafe() {
        return unCafe;
    }

    public void setUnCafe(Cafe unCafe) {
        this.unCafe = unCafe;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
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
