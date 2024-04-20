
package com.mycompany.integrador_02.servlets;


import com.mycompany.integrador_02.logica.Producto;
import com.mycompany.integrador_02.persistencia.Controlador;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "svEditarCliente", urlPatterns = {"/svEditarCliente"})
public class svEditarProducto extends HttpServlet {

    Controlador control = new Controlador();
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        int id_producto = Integer.parseInt(request.getParameter("id_producto"));
        Producto  producto = control.buscarProducto(id_producto);
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("producto_a_editar",producto);
        response.sendRedirect("editarProducto.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String precio = request.getParameter("precio");
        String disponibleString = request.getParameter("disponible");   
        String categoria = request.getParameter("disponible");
        String barista_asignado = request.getParameter("barista_asignado");
        
        control.añadirProducto(nombre, descripcion, precio, disponibleString, categoria, barista_asignado);
        response.sendRedirect("svProducto");
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
