
package com.mycompany.integrador_02.logica;

import java.util.List;
import javax.persistence.OneToMany;

public class Mesa {
    private int id;
    private int capacidad;
    private boolean estaOcupada;
    private String ubicacion;
    @OneToMany(mappedBy="mesa")
    private List<Pedido> pedidos;

    public Mesa() {
    }

    public Mesa(int id, int capacidad, boolean estaOcupada, String ubicacion, List<Pedido> pedidos) {
        this.id = id;
        this.capacidad = capacidad;
        this.estaOcupada = estaOcupada;
        this.ubicacion = ubicacion;
        this.pedidos = pedidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isEstaOcupada() {
        return estaOcupada;
    }

    public void setEstaOcupada(boolean estaOcupada) {
        this.estaOcupada = estaOcupada;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    
}
