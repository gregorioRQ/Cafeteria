
package com.mycompany.integrador_02.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int numMesa;
    private boolean fueAtendido;
    @OneToMany(mappedBy="unPedido")
    private List<Producto> productos;
    @ManyToOne
    @JoinColumn(name="fk_camarero")
    private Camarero unCamarero;

    public Pedido() {
    }

    public Pedido(int id, int numMesa, boolean fueAtendido, List<Producto> productos, Camarero unCamarero) {
        this.id = id;
        this.numMesa = numMesa;
        this.fueAtendido = fueAtendido;
        this.productos = productos;
        this.unCamarero = unCamarero;
    }

    

    public Camarero getUnCamarero() {
        return unCamarero;
    }

    public void setUnCamarero(Camarero unCamarero) {
        this.unCamarero = unCamarero;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public boolean isFueAtendido() {
        return fueAtendido;
    }

    public void setFueAtendido(boolean fueAtendido) {
        this.fueAtendido = fueAtendido;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    
}
