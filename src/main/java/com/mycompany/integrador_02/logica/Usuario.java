
package com.mycompany.integrador_02.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreUs;
    private String contrasenia;
    private String rol;
    @OneToOne(mappedBy="unUsuario")
    private Barista unBarista;
    @OneToOne(mappedBy="unUsuario")
    private Camarero unCamarero;
     @OneToOne(mappedBy="unUsuario")
    private Administrador unAdmin;
    

    public Usuario() {
    }

    public Usuario(int id, String nombreUs, String contrasenia, String rol, Barista unBarista, Camarero unCamarero, Administrador unAdmin) {
        this.id = id;
        this.nombreUs = nombreUs;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.unBarista = unBarista;
        this.unCamarero = unCamarero;
        this.unAdmin = unAdmin;
    }

    public Camarero getUnCamarero() {
        return unCamarero;
    }

    public void setUnCamarero(Camarero unCamarero) {
        this.unCamarero = unCamarero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUs() {
        return nombreUs;
    }

    public void setNombreUs(String nombreUs) {
        this.nombreUs = nombreUs;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Barista getUnBarista() {
        return unBarista;
    }

    public void setUnBarista(Barista unBarista) {
        this.unBarista = unBarista;
    }

    public Administrador getUnAdmin() {
        return unAdmin;
    }

    public void setUnAdmin(Administrador unAdmin) {
        this.unAdmin = unAdmin;
    }
  
}
