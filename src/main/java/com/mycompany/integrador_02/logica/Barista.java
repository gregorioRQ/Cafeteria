
package com.mycompany.integrador_02.logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Barista extends Empleado implements Serializable {
    private String variedadesDeCafe;
    private boolean habilidadArteLatte;
    private String metodosDeExtraccion;
    @OneToMany(mappedBy="barista")
    private List<Producto> productos;
    @OneToOne
    private Usuario usuario;


    public Barista() {
    }

    public Barista(String variedadesDeCafe, boolean habilidadArteLatte, String metodosDeExtraccion, List<Producto> productos, Usuario usuario, String fechaIngreso, String sueldo, String diasTrabajo, String horariosTrabajo, int id, String nombre, String apellido, String dni, Date fechNac, String telefono, String genero) {
        super(fechaIngreso, sueldo, diasTrabajo, horariosTrabajo, id, nombre, apellido, dni, fechNac, telefono, genero);
        this.variedadesDeCafe = variedadesDeCafe;
        this.habilidadArteLatte = habilidadArteLatte;
        this.metodosDeExtraccion = metodosDeExtraccion;
        this.productos = productos;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }


    public String getVariedadesDeCafe() {
        return variedadesDeCafe;
    }

    public void setVariedadesDeCafe(String variedadesDeCafe) {
        this.variedadesDeCafe = variedadesDeCafe;
    }

    public boolean isHabilidadArteLatte() {
        return habilidadArteLatte;
    }

    public void setHabilidadArteLatte(boolean habilidadArteLatte) {
        this.habilidadArteLatte = habilidadArteLatte;
    }

    public String getMetodosDeExtraccion() {
        return metodosDeExtraccion;
    }

    public void setMetodosDeExtraccion(String metodosDeExtraccion) {
        this.metodosDeExtraccion = metodosDeExtraccion;
    }
    
    
}
