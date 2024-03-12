
package com.mycompany.integrador_02.persistencia;

import com.mycompany.integrador_02.logica.Cliente;

public class ControladorDePersistencia {
    BaristaJpaController bjpa = new BaristaJpaController();
    CamareroJpaController cjpa = new CamareroJpaController();
    ClienteJpaController clijpa = new ClienteJpaController();
    IngredienteJpaController ijpa = new IngredienteJpaController();
    MesaJpaController mjpa = new MesaJpaController();
    PedidoJpaController pjpa = new PedidoJpaController();
    PersonaJpaController perjpa = new PersonaJpaController();
    ProductoJpaController projpa = new ProductoJpaController();
    
    public void crearCliente(Cliente cli){
        clijpa.create(cli);
    }
    
}
