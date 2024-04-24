
package com.mycompany.integrador_02.logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Barista extends Empleado implements Serializable {    
    private boolean habilidadArteLatte;    
    @OneToMany(mappedBy="unBarista")
    private List<Producto> productos;
    @OneToMany(mappedBy="unBarista")
    private List<Cafe> variedadesDeCafe;
    @OneToOne
    @JoinColumn(name="fk_usuario")
    private Usuario unUsuario;

    public Barista() {
    }

    public Barista(boolean habilidadArteLatte, List<Producto> productos, List<Cafe> variedadesDeCafe, Usuario unUsuario, String fechaIngreso, String sueldo, String diasTrabajo, String horariosTrabajo, int id, String nombre, String apellido, String dni, Date fechNac, String telefono, String genero) {
        super(fechaIngreso, sueldo, diasTrabajo, horariosTrabajo, id, nombre, apellido, dni, fechNac, telefono, genero);
        this.habilidadArteLatte = habilidadArteLatte;
        this.productos = productos;
        this.variedadesDeCafe = variedadesDeCafe;
        this.unUsuario = unUsuario;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }


    public boolean isHabilidadArteLatte() {
        return habilidadArteLatte;
    }

    public void setHabilidadArteLatte(boolean habilidadArteLatte) {
        this.habilidadArteLatte = habilidadArteLatte;
    }

    public Usuario getUnUsuario() {
        return unUsuario;
    }

    public void setUnUsuario(Usuario unUsuario) {
        this.unUsuario = unUsuario;
    }

    public List<Cafe> getVariedadesDeCafe() {
        return variedadesDeCafe;
    }

    public void setVariedadesDeCafe(List<Cafe> variedadesDeCafe) {
        this.variedadesDeCafe = variedadesDeCafe;
    }

}
