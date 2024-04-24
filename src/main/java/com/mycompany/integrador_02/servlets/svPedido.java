
package com.mycompany.integrador_02.servlets;

import com.mycompany.integrador_02.logica.Barista;
import com.mycompany.integrador_02.logica.Camarero;
import com.mycompany.integrador_02.logica.Pedido;
import com.mycompany.integrador_02.logica.Producto;
import com.mycompany.integrador_02.logica.Usuario;
import com.mycompany.integrador_02.persistencia.Controlador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "svPedido", urlPatterns = {"/svPedido"})
public class svPedido extends HttpServlet {

    Controlador control = new Controlador();
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
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
        
         String[] arregloProductos = request.getParameterValues("id_producto");

         List<Producto> productos = new ArrayList();
         for(String p : arregloProductos){
             int id_producto = Integer.parseInt(p);
              Producto productoEncontrado = control.buscarProducto(id_producto);
              productos.add(productoEncontrado );             
         }

            Pedido pedido = new Pedido();             
            pedido.setProductos(productos);

            int numeroDeMesa = Integer.parseInt(request.getParameter("numeroDeMesa"));
            pedido.setNumMesa(numeroDeMesa);          
            int id_usuario = Integer.parseInt(request.getParameter("idUsu"));
            Usuario usu = control.buscarUsuario(id_usuario);
            Camarero camarero = usu.getUnCamarero();
            pedido.setFueAtendido(false);
            pedido.setUnCamarero(camarero);

            control.crearPedido(pedido);

            
            response.sendRedirect("index.jsp");
        }else if(accion.equals("eliminar")){
            int idPedido = Integer.parseInt(request.getParameter("idPedido"));
            control.eliminarPedido(idPedido);
            response.sendRedirect("index.jsp");
        }
        } 
        
    
   
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
