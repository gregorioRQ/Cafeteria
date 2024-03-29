
package com.mycompany.integrador_02.servlets;

import com.mycompany.integrador_02.logica.Cliente;
import com.mycompany.integrador_02.persistencia.Controlador;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "svEditarCliente", urlPatterns = {"/svEditarCliente"})
public class svEditarCliente extends HttpServlet {

    Controlador control = new Controlador();
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        int id_clienteEdit = Integer.parseInt(request.getParameter("id_clienteEdit"));
        Cliente cli = control.buscarCliente(id_clienteEdit);
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clienteEditar", cli);
        response.sendRedirect("editarCliente.jsp");
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
        
        Cliente cli = (Cliente) request.getSession().getAttribute("clienteEditar");
        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setDni(dni);
        cli.setGenero(genero);
        cli.setTelefono(telefono);
        cli.setFechNac(fechaNac);
        control.editarCliente(cli);
        response.sendRedirect("svClientes");
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
