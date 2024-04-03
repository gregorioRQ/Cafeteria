
<%@page import="com.mycompany.integrador_02.logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <!-- ***** Header Area Start ***** -->
    <header class="header-area header-sticky">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav class="main-nav">
                        <!-- ***** Logo Start ***** -->
                        <a href="index.html" class="logo">
                            <img src="assets/images/klassy-logo.png" align="klassy cafe html template">
                        </a>
                       
                        <ul class="nav">
                            <li class="scroll-to-section"><a href="index.jsp" class="active">Home</a></li>
                            
                             <% if ("Camarero".equals(session.getAttribute("rol"))) { %>
       
                              <li class="scroll-to-section"><a href="registro.jsp">Clientes</a></li>
                                <% } %>                   
                            
                            <li class="scroll-to-section"><a href="#chefs">Chefs</a></li> 
                            <li class="submenu">
                                <a href="#registro">Registro</a>
                                <ul>
                                    <li><a href="registro.jsp">Clientes</a></li>
                                    <li><a href="pr.jsp">Empleados</a></li>
                                    <li><a href="registro.jsp">Pedidos</a></li>
                                    <li><a href="registro.jsp">Mesa</a></li>
                                </ul>
                            </li>
                            <!-- <li class=""><a rel="sponsored" href="https://templatemo.com" target="_blank">External URL</a></li> -->
                            <li class="scroll-to-section"><form action="svCerrarSesion" method="POST">
    <input type="submit" value="Cerrar Sesión">
</form></li> 
                        </ul>        
                        <a class='menu-trigger'>
                            <span>Menu</span>
                        </a>
                        <!-- ***** Menu End ***** -->
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- ***** Header Area End ***** -->

