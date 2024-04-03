
package com.mycompany.integrador_02.logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Cliente extends Persona implements Serializable {
    @OneToMany(mappedBy="cliente")
    private List<Pedido> pedidos;
    @OneToOne
    private Usuario usuario;

    public Cliente() {
    }

    public Cliente(List<Pedido> pedidos, int id, String nombre, String apellido, String dni, Date fechNac, String telefono, String genero, Usuario usuario) {
        super(id, nombre, apellido, dni, fechNac, telefono, genero);
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   
    
}
