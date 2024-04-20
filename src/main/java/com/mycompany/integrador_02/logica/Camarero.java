
package com.mycompany.integrador_02.logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Camarero extends Empleado implements Serializable {
    private String zonaDeTrabajo;
    private String mesasQueAtiende;
    @OneToMany(mappedBy = "unCamarero")
    private List<Pedido> pedidos;
    @OneToOne
    @JoinColumn(name="fk_usuario")
    private Usuario unUsuario;

    public Camarero() {
    }

    public Camarero(String zonaDeTrabajo, String mesasQueAtiende, List<Pedido> pedidos, Usuario unUsuario, String fechaIngreso, String sueldo, String diasTrabajo, String horariosTrabajo, int id, String nombre, String apellido, String dni, Date fechNac, String telefono, String genero) {
        super(fechaIngreso, sueldo, diasTrabajo, horariosTrabajo, id, nombre, apellido, dni, fechNac, telefono, genero);
        this.zonaDeTrabajo = zonaDeTrabajo;
        this.mesasQueAtiende = mesasQueAtiende;
        this.pedidos = pedidos;
        this.unUsuario = unUsuario;
    }

    public Usuario getUnUsuario() {
        return unUsuario;
    }

    public void setUnUsuario(Usuario unUsuario) {
        this.unUsuario = unUsuario;
    }

    public String getZonaDeTrabajo() {
        return zonaDeTrabajo;
    }

    public void setZonaDeTrabajo(String zonaDeTrabajo) {
        this.zonaDeTrabajo = zonaDeTrabajo;
    }

    public String getMesasQueAtiende() {
        return mesasQueAtiende;
    }

    public void setMesasQueAtiende(String mesasQueAtiende) {
        this.mesasQueAtiende = mesasQueAtiende;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    
}
