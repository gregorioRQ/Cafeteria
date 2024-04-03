
package com.mycompany.integrador_02.servlets;

import com.mycompany.integrador_02.logica.Usuario;
import com.mycompany.integrador_02.persistencia.Controlador;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "svLogin", urlPatterns = {"/svLogin"})
public class svLogin extends HttpServlet {

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
        
        if("inicioSesion".equals(accion)){
             String usuario = request.getParameter("usuario");
             String contrasenia = request.getParameter("contrasenia");
             
            boolean validacion  = false;
            validacion = control.comprobarIngreso(usuario, contrasenia);
       
            if(validacion == true){
           HttpSession sesion = request.getSession(true);
           sesion.setAttribute("usuario", usuario);
           sesion.setAttribute("rol", control.obtenerRol(usuario));
           response.sendRedirect("index.jsp");
        }else{
            response.sendRedirect("index2.jsp");
            }
        } else if("registro".equals(accion)){
        String nombreUs = request.getParameter("nombreUs");
        String contra = request.getParameter("contrasenia");
        String rol = request.getParameter("rol");
       
        boolean registroExitoso = control.registrarUsuario(nombreUs, contra, rol);
        
       if(registroExitoso){
           response.sendRedirect("index.jsp");
       }else{
           response.sendRedirect("index2.jsp");
       }
      }
      
           
     /*comprueba si el usuario y contraseña son correctos (si ya existen o no en la db)
        si es correcto se crea una sesion con su usuario y contraseña ingresados (no se crea un usuario)
       */
       
      
           
       
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
