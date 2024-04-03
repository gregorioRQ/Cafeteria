
package com.mycompany.integrador_02.persistencia;

import com.mycompany.integrador_02.logica.Cliente;
import com.mycompany.integrador_02.logica.Usuario;
import java.util.ArrayList;
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
    
    public void crearUsuario(Usuario us){      
        cp.crearUsuario(us);
    }

    public boolean comprobarIngreso(String usuario, String contrasenia) {
      boolean ingreso = false;
      
      /*este metodo de validacion no es el mas optimo ya que trae todos los usarios de la db a la logica y los comprueba uno por uno 
        en lugar de hacer todo esto en la db.
      El 1er if se encarga de inicializar el objeto us, para que su metodo get.NombreUs() no arroje null.
      */     
      List<Usuario> usuarios = new ArrayList<Usuario>();
      usuarios = cp.trarUsuarios();
      
      for(Usuario us : usuarios){
          if(us.getNombreUs() == null){
              System.out.println("------------EL NOMBRE DE USUARIO ES NULL");
          }else{
          if(us.getNombreUs().equals(usuario)){
              if(us.getContrasenia().equals(contrasenia)){
                  ingreso = true;
              }else{
                  ingreso = false;
              }
          }
      }}
      return ingreso;
    }
    
    
    public String obtenerRol(String us){
        
    String result = "";
         List<Usuario> usuarios = new ArrayList<Usuario>();
      usuarios = cp.trarUsuarios();
      
      for(Usuario usu : usuarios){
          if(usu.getNombreUs() == null){
              System.out.println("------------EL NOMBRE DE USUARIO ES NULL");
          }else{
          if(usu.getNombreUs().equals(us)){
              result = usu.getRol();
          }else{
              System.out.println("-----EL USUARIO NO EXISTE");
          }
    }
      }
      return result;
}
    
    public boolean registrarUsuario(String nombreUs, String contra , String rol){
        boolean registro = false;
         List<Usuario> usuarios = new ArrayList<Usuario>();
      usuarios = cp.trarUsuarios();
      
      for(Usuario us : usuarios){
          if(us.getNombreUs() == null){
              System.out.println("------------EL NOMBRE DE USUARIO ES NULL");
          }else{
          registro =  us.getNombreUs().equals(nombreUs) && us.getContrasenia().equals(contra);
      }}
        return registro;
    }
    
}
