package com.mycompany.integrador_02.servlets;

import com.mycompany.integrador_02.logica.Barista;
import com.mycompany.integrador_02.logica.Cafe;
import com.mycompany.integrador_02.logica.Producto;
import com.mycompany.integrador_02.logica.Usuario;
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
         int id_producto = Integer.parseInt(request.getParameter("idProducto"));
         
        Producto  producto = control.buscarProducto(id_producto);
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("productoEditar",producto);
        
        response.sendRedirect("editarProducto.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if (accion.equals("agregar")) {
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String precio = request.getParameter("precio");
            String disponibleString = request.getParameter("disponible");
            String nombreDeUsuario = request.getParameter("nombreDeUsuario");
            String categoria = request.getParameter("categoria");
            String variedadDeCafe = request.getParameter("variedadDeCafe");
            String descripcionDelCafe = request.getParameter("descripcionDelCafe");
            
            control.añadirProducto(nombre, descripcion, precio, disponibleString, categoria, nombreDeUsuario, variedadDeCafe, descripcionDelCafe);
            response.sendRedirect("administrarProductos.jsp");
        } else if (accion.equals("eliminar")) {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            control.eliminarProducto(idProducto);
            response.sendRedirect("administrarProductos.jsp");
        } else if (accion.equals("editar")) {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            int idCafe = Integer.parseInt(request.getParameter("idCafe"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String precio = request.getParameter("precio");
            Boolean disponible = Boolean.parseBoolean(request.getParameter("disponible"));
            String nombreDeUsuario = request.getParameter("nombreDeUsuario");
            String categoria = request.getParameter("categoria");
            String variedadDeCafe = request.getParameter("variedadDeCafe");
            String descripcionDelCafe = request.getParameter("descripcionDelCafe");
            
            
            Producto pro = control.buscarProducto(idProducto);
            Cafe cafeEncontrado = control.buscarCafe(idCafe);
            Barista baristaEncontrado = control.buscarUsuarioPorNombre(nombreDeUsuario).getUnBarista();
            cafeEncontrado.setNombre(variedadDeCafe);
            cafeEncontrado.setDescripcion(descripcionDelCafe);
            cafeEncontrado.setUnBarista(baristaEncontrado);
            pro.setNombre(nombre);
            pro.setDescripcion(descripcion);
            pro.setPrecio(precio);
            pro.setEstaDisponible(disponible);
            pro.setCategoria(categoria);            
            pro.setUnBarista(baristaEncontrado);
            pro.setUnCafe(cafeEncontrado);
           
            control.editarProducto(pro);
            control.editarCafe(cafeEncontrado);
            response.sendRedirect("administrarProductos.jsp");
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
