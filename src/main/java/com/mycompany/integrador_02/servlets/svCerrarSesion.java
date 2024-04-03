
package com.mycompany.integrador_02.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "svCerrarSesion", urlPatterns = {"/svCerrarSesion"})
public class svCerrarSesion extends HttpServlet {

   
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
        HttpSession session = request.getSession(false); // Obtener la sesión actual sin crear una nueva
        if (session != null) {
            session.invalidate(); // Invalidar la sesión
        }
        response.sendRedirect("index.jsp"); // Redirigir al usuario a la página de inicio
    }
    

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
