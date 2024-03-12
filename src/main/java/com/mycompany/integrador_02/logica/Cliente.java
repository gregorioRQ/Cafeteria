
package com.mycompany.integrador_02.logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
@Entity
public class Cliente extends Persona implements Serializable {
    @OneToMany(mappedBy="cliente")
    private List<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(List<Pedido> pedidos, int id, String nombre, String apellido, String dni, Date fechNac, String telefono, String genero) {
        super(id, nombre, apellido, dni, fechNac, telefono, genero);
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

   
    
}
