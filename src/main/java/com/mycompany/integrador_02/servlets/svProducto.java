
package com.mycompany.integrador_02.servlets;

import com.mycompany.integrador_02.logica.Producto;
import com.mycompany.integrador_02.persistencia.Controlador;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "svProducto", urlPatterns = {"/svProducto"})
public class svProducto extends HttpServlet {

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
        
        String accion = request.getParameter("accion");
        
        if(accion.equals("agregar")){
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String precio = request.getParameter("precio");
        String disponibleString = request.getParameter("disponible");
        String nombreDeUsuario = request.getParameter("nombreDeUsuario");
        String categoria = request.getParameter("disponible");
              
        control.añadirProducto(nombre, descripcion, precio, disponibleString, categoria, nombreDeUsuario);
        response.sendRedirect("administrarProductos.jsp");
        }else if(accion.equals("eliminar")){
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            control.eliminarProducto(idProducto);
            response.sendRedirect("administrarProductos.jsp");
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
