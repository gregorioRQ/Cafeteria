
    <% HttpSession misesion = request.getSession();
                String usuario = (String) request.getSession().getAttribute("usuario");

                if (usuario == null) {%>
            <li><a href="login.jsp">Log in</a></li>
                <%}%>
 
    <section>
        
        <h1>Bienvenido camarero </h1>
        
       <% if ("Camarero".equals(session.getAttribute("rol"))) { %>
                <%@include file="camarero/listaMesas.jsp"%>
                       
      } %>  
    </section>
    
