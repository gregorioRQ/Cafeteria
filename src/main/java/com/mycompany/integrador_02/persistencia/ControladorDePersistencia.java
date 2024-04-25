
package com.mycompany.integrador_02.persistencia;

import com.mycompany.integrador_02.logica.Administrador;
import com.mycompany.integrador_02.logica.Barista;
import com.mycompany.integrador_02.logica.Cafe;
import com.mycompany.integrador_02.logica.Camarero;
import com.mycompany.integrador_02.logica.Pedido;
import com.mycompany.integrador_02.logica.Producto;
import com.mycompany.integrador_02.logica.Usuario;
import com.mycompany.integrador_02.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorDePersistencia {
    BaristaJpaController bjpa = new BaristaJpaController();
    CamareroJpaController cjpa = new CamareroJpaController();
    ProductoJpaController projpa = new ProductoJpaController();
    UsuarioJpaController usjpa = new UsuarioJpaController();
    AdministradorJpaController adjpa = new AdministradorJpaController();
    PedidoJpaController pedjpa = new PedidoJpaController();
    CafeJpaController cafjpa = new CafeJpaController();

    List<Usuario> trarUsuarios() {
        List<Usuario>usuarios = usjpa.findUsuarioEntities();
        ArrayList<Usuario> usuarios2 = new ArrayList<>(usuarios);
        return usuarios2;
        
    }

    public void crearUsuario(Usuario us) {
        usjpa.create(us);
    }

    void crearAdmin(Administrador ad) {
        adjpa.create(ad);
    }

    void eliminarUsuario(int idUsu) {
        try {
            usjpa.destroy(idUsu);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void crearProducto(Producto p) {
        projpa.create(p);
    }

    void crearBarista(Barista b) {
       bjpa.create(b);
    }

    void editarBarista(Barista b) {
        try {
            bjpa.edit(b);
        } catch (Exception ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void editarUsuario(Usuario usu) {
        try {
            usjpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarProducto(Producto p){
        try {
            projpa.edit(p);
        } catch (Exception ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    List<Producto> buscarProductos() {
        return projpa.findProductoEntities();
    }

    void crearPedido(Pedido p) {
       pedjpa.create(p);
    }

    public Camarero buscarCamarero(int id) {
        return cjpa.findCamarero(id);
    }

    void editarPedido(Pedido pedido) {
       try {
            pedjpa.edit(pedido);
        } catch (Exception ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Producto buscarProducto(int id_producto) {
       return projpa.findProducto(id_producto);
    }

    Usuario buscarUsuario(int id_usuario) {
       return usjpa.findUsuario(id_usuario);
    }

    void crearCamarero(Camarero camarero) {
        cjpa.create(camarero);
    }

    public List<Pedido> buscarPedidos() {
       return pedjpa.findPedidoEntities();
    }

    void eliminarPedido(int idPedido) {
        try {
            pedjpa.destroy(idPedido);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void eliminarBarista(int id) {
        try {
            bjpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void eliminarCamarero(int id) {
        try {
            cjpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void eliminarProducto(int idProducto) {
        try {
            projpa.destroy(idProducto);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    List<Barista> buscarBaristas() {
       return bjpa.findBaristaEntities();
    }

    Barista buscarBaristaPorId(int i) {
        return bjpa.findBarista(i);
    }

    void crearCafe(Cafe cafe) {
        cafjpa.create(cafe);
    }

    void editarCafe(Cafe c) {
        try {
            cafjpa.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cafe buscarCafe(int idCafe) {
        return cafjpa.findCafe(idCafe);
    }

    public List<Cafe> buscarTodosLosCafes(){
        return cafjpa.findCafeEntities();
    }

    public void eliminarCafe(int idCafe){
        try {
            cafjpa.destroy(idCafe);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void editarCamarero(Camarero camarero) {
        try {
            cjpa.edit(camarero);
        } catch (Exception ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
