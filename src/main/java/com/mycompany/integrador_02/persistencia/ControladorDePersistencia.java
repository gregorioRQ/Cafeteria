
package com.mycompany.integrador_02.persistencia;

import com.mycompany.integrador_02.logica.Cliente;
import com.mycompany.integrador_02.logica.Usuario;
import com.mycompany.integrador_02.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorDePersistencia {
    BaristaJpaController bjpa = new BaristaJpaController();
    CamareroJpaController cjpa = new CamareroJpaController();
    ClienteJpaController clijpa = new ClienteJpaController();
    PedidoJpaController pjpa = new PedidoJpaController();
    PersonaJpaController perjpa = new PersonaJpaController();
    ProductoJpaController projpa = new ProductoJpaController();
    UsuarioJpaController usjpa = new UsuarioJpaController();
    
    public void crearCliente(Cliente cli){
        clijpa.create(cli);
    }

    public List<Cliente> traerClientes() {
       return clijpa.findClienteEntities();
    }

    void eliminarCliente(int id_cliente) {
        try {
            clijpa.destroy(id_cliente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Cliente buscarCliente(int id_clienteEdit) {
       return clijpa.findCliente(id_clienteEdit);
    }

    void editarCliente(Cliente cli) {
        try {
            clijpa.edit(cli);
        } catch (Exception ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    List<Usuario> trarUsuarios() {
        return usjpa.findUsuarioEntities();  
    }

    public void crearUsuario(Usuario us) {
        usjpa.create(us);
    }
    
}
