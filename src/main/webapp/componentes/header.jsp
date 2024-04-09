
<%@page import="com.mycompany.integrador_02.logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header>

    <nav>


        <ul>
            <li><a href="index.jsp" class="active">Home</a></li>

            <% if ("Camarero".equals(session.getAttribute("rol"))) { %>

            <li><a href="svClientes">Clientes</a></li>
                <% } %>


            <% HttpSession misesion = request.getSession();
                String usuario = (String) request.getSession().getAttribute("usuario");

                if (usuario == null) {%>
            <li><a href="login.jsp">Log in</a></li>
                <%} else {%>
            <li><form action="svCerrarSesion" method="POST">
                    <input type="submit" value="Cerrar Sesión">
                </form></li>


            <% }%>

        </ul>

    </nav>


</header>

