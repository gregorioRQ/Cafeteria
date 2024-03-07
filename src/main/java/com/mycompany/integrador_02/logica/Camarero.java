
package com.mycompany.integrador_02.logica;

import java.util.List;
import javax.persistence.OneToMany;

public class Camarero extends Empleado {
    private String zonaDeTrabajo;
    private String mesasQueAtiende;
    @OneToMany(mappedBy = "camarero")
    private List<Pedido> pedidos;
}
