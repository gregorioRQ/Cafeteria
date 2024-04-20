  
package com.mycompany.integrador_02.servlets;

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
             String nombreUs = request.getParameter("nombreUs");
             String contrasenia = request.getParameter("contrasenia");
             
            boolean validacion  = false;
             /*
            comprueba si el usuario y contraseña  ya existen o no en la db
            si existe se creará una sesion con: 
            el objeto sesion recibe los atributos usuario: usuario 
            rol: el rol que obtenga el metodo obtenerRol
            estos datos fueron ingresados por el formulario de inicio de sesion
            (no se crea un objeto usuario)
       */
            validacion = control.comprobarIngreso(nombreUs, contrasenia);
       
            if(validacion == true){
           HttpSession sesion = request.getSession(true);
           sesion.setAttribute("nombreUs", nombreUs);
           sesion.setAttribute("rol", control.obtenerRol(nombreUs));
           sesion.setAttribute("id_usuario", control.obtenerId(nombreUs));
           response.sendRedirect("index.jsp");
        }else{
            response.sendRedirect("error.jsp");
            }
        } else if(accion.equals("registrar")){
        String nombreUs = request.getParameter("nombreUs");
        String contra = request.getParameter("contrasenia");
        String rol = request.getParameter("rol");
       
        boolean registroExitoso = control.registrarUsuario(nombreUs, contra, rol);
        
       if(registroExitoso){         
           response.sendRedirect("index.jsp");
       }else{
           response.sendRedirect("error.jsp");
       }

      }else if("eliminarUsuario".equals(accion)){
          int idUsu = Integer.parseInt(request.getParameter("id_usuario"));
          control.eliminarUsuario(idUsu);
          response.sendRedirect("index.jsp");
      }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
