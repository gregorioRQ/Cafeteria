
package com.mycompany.integrador_02.persistencia;

import com.mycompany.integrador_02.logica.Cliente;
import java.util.List;

public class Controlador {
    ControladorDePersistencia cp = new ControladorDePersistencia();
    
    public void crearCliente(Cliente cli){
        cp.crearCliente(cli);
    }

    public List<Cliente> traerClientes() {
        return cp.traerClientes();
    }

    public void eliminarCliente(int id_cliente) {
       cp.eliminarCliente(id_cliente);
    }

    public Cliente buscarCliente(int id_clienteEdit) {
        return cp.buscarCliente(id_clienteEdit);
    }

    public void editarCliente(Cliente cli) {
       cp.editarCliente(cli);
    }
}
