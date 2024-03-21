
package com.mycompany.integrador_02.servlets;

import com.mycompany.integrador_02.logica.Cliente;
import com.mycompany.integrador_02.logica.Pedido;
import com.mycompany.integrador_02.persistencia.Controlador;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "svClientes", urlPatterns = {"/svClientes"})
public class svClientes extends HttpServlet {

   Controlador control = new Controlador();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
     
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       String nombre = request.getParameter("nombre");
       String apellido = request.getParameter("apellido");
       String dni = request.getParameter("dni");
       String telefono = request.getParameter("telefono");
       String genero = request.getParameter("genero");
      String fechaNacString = request.getParameter("fechaNac");
      
      
       Date fechaNac = null;
        try {  
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            
            fechaNac = formato.parse(fechaNacString);
            
            
        } catch (ParseException e) {
        }       
       ArrayList<Pedido>pedido = new ArrayList();
       Cliente cli = new Cliente();      
       cli.setPedidos(pedido);
       cli.setNombre(nombre);
       cli.setApellido(apellido);
       cli.setDni(dni);
       cli.setGenero(genero);
       cli.setTelefono(telefono);
       cli.setFechNac(fechaNac);
       control.crearCliente(cli);
      response.sendRedirect("index.jsp");
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
