
package com.mycompany.integrador_02.logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String hora;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String estado;
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="id_camarero")
    private Camarero camarero;
    @ManyToMany(mappedBy="pedidos")
    private List<Producto> productos;

    public Pedido() {
    }

    public Pedido(int id, String hora, Date fecha, String estado, Cliente cliente, Camarero camarero, List<Producto> productos) {
        this.id = id;
        this.hora = hora;
        this.fecha = fecha;
        this.estado = estado;
        this.cliente = cliente;
        this.camarero = camarero;
        this.productos = productos;
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
