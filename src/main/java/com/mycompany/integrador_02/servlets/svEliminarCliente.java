
package com.mycompany.integrador_02.servlets;

import com.mycompany.integrador_02.persistencia.Controlador;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "svEliminarCliente", urlPatterns = {"/svEliminarCliente"})
public class svEliminarCliente extends HttpServlet {
Controlador control = new Controlador();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        }
    

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
       control.eliminarCliente(id_cliente);
       response.sendRedirect("svClientes");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
