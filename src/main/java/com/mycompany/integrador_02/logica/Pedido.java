
package com.mycompany.integrador_02.logica;

import java.util.Date;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Pedido {
    private int id;
    private String hora;
    private Date fecha;
    private String estado;
    @ManyToOne
    @JoinColumn(name="id_pedido")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="id_pedido")
    private Mesa mesa;
    @ManyToOne
    @JoinColumn(name="id_pedido")
    private Camarero camarero;
    @ManyToMany(mappedBy="pedidos")
    private List<Producto> productos;

    public Pedido() {
    }

    public Pedido(int id, String hora, Date fecha, String estado, Cliente cliente, Mesa mesa, Camarero camarero, List<Producto> productos) {
        this.id = id;
        this.hora = hora;
        this.fecha = fecha;
        this.estado = estado;
        this.cliente = cliente;
        this.mesa = mesa;
        this.camarero = camarero;
        this.productos = productos;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Camarero getCamarero() {
        return camarero;
    }

    public void setCamarero(Camarero camarero) {
        this.camarero = camarero;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    
}
