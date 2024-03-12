
package com.mycompany.integrador_02.persistencia;

import com.mycompany.integrador_02.logica.Cliente;

public class Controlador {
    ControladorDePersistencia cp = new ControladorDePersistencia();
    
    public void crearCliente(Cliente cli){
        cp.crearCliente(cli);
    }
}
