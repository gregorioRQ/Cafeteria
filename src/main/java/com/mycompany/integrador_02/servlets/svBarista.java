
package com.mycompany.integrador_02.servlets;

import com.mycompany.integrador_02.logica.Barista;
import com.mycompany.integrador_02.persistencia.Controlador;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "svBarista", urlPatterns = {"/svBarista"})
public class svBarista extends HttpServlet {

  
    Controlador control = new Controlador();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String nombreDeUsuario = request.getParameter("nombreDeUsuario");
       Barista barista = control.buscarUsuarioPorNombre(nombreDeUsuario).getUnBarista();
       HttpSession sesion = request.getSession();
        sesion.setAttribute("baristaEditar",barista);
        
        response.sendRedirect("editarBarista.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        if(accion.equals("registrar")){
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String dni = request.getParameter("dni");
            String genero = request.getParameter("genero");
            String fechaDeIngreso = request.getParameter("fechaIngreso");
            String diasDeTrabajo = request.getParameter("diasDeTrabajo");
            String horariosDeTrabajo = request.getParameter("horariosDeTrabajo");
            String habilidadArteLatte = request.getParameter("habilidadArteLatte");
            String salario = request.getParameter("salario");
            String telefono = request.getParameter("telefono");
            String nombreDeUsuario = request.getParameter("nombreDeUsuario");
            String fechaNac = request.getParameter("fechaNac");
            
            control.añadirBarista(nombre,apellido,dni,genero,fechaDeIngreso,diasDeTrabajo,horariosDeTrabajo,habilidadArteLatte, salario, telefono, nombreDeUsuario, fechaNac);
           
                response.sendRedirect("administrador.jsp");
            
        }else if(accion.equals("eliminar")){
            String nombreUsuario = request.getParameter("nombreUsuario");
            control.eliminarBarista(nombreUsuario);
            response.sendRedirect("administrador.jsp");
        }else if(accion.equals("editar")){
            int baristaId = Integer.parseInt(request.getParameter("baristaId"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String dni = request.getParameter("dni");
            String genero = request.getParameter("genero");
            String fechaDeIngreso = request.getParameter("fechaIngreso");
            String diasDeTrabajo = request.getParameter("diasDeTrabajo");
            String horariosDeTrabajo = request.getParameter("horariosDeTrabajo");
            String habilidadArteLatte = request.getParameter("habilidadArteLatte");
            String salario = request.getParameter("salario");
            String telefono = request.getParameter("telefono");
            String fechaNac = request.getParameter("fechaNac");
            System.out.println("----------------------------"+fechaNac);
            control.editarUnBarista(nombre, apellido, dni, genero, fechaDeIngreso, diasDeTrabajo, horariosDeTrabajo, habilidadArteLatte, salario, telefono, fechaNac, baristaId);
            response.sendRedirect("administrador.jsp");
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
