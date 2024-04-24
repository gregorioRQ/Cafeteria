
<%@page import="com.mycompany.integrador_02.logica.Usuario"%>
<%@page import="com.mycompany.integrador_02.logica.Producto"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.integrador_02.persistencia.Controlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession misesion = request.getSession();
            String nombreUs = (String) request.getSession().getAttribute("nombreUs");

            if (nombreUs == null) {
                response.sendRedirect("index.jsp");
            }

            Controlador control = new Controlador();
            int idUsu = (Integer) request.getSession().getAttribute("id_usuario");
            //Castea el objeto traido por getAttribute() a una lista.
            List<Producto> productos = control.buscarProductos();
        %>
        <h1>Bienvenido camarero</h1>

        <h1>Productos disponibles</h1>

        <%
            //comprueba si la lista no es igual a null para que no ocurra una null pointer exception.
            if (productos != null) { %>  

        <form action="svPedido" method="POST">

            <%  for (Producto p : productos) {%>
            <p>---------------------------------------------------------------</p>
            <label>Seleccionar éste producto</label>
            <input type="checkbox">
            <br>
            <p>Nombre del Producto: <%=p.getNombre()%></p>
            <p>Contenidos: <%=p.getUnCafe().getNombre()%></p>           
            <input type="hidden" name="id_producto" value="<%=p.getId()%>">                                           
            <%  }%>
            <p>------------------------------------------------------------------</p>
            <label for="numMesa">Número de mesa: </label>            
            <input type="text" name="numeroDeMesa" id="numMesa">
            <br>
            <label for="descripcion">Descripcion: </label>
            <input type="text" id="descripcion" name="descripcion">
            <br>
            <input type="hidden" value="agregar" name="accion">            
            <input type="hidden" name="idUsu" value="<%=idUsu%>">
            <br>
            <button type="submit">Ordenar</button>
        </form>

        <%  }%>



        <form action="svCerrarSesion" method="POST">
            <input type="submit" value="Cerrar Sesión">
        </form>

        <form action="svLogin" method="POST">
            <input type="hidden" name="accion" value="eliminarUsuario">
            <input type="hidden" value="<%= idUsu%>" name="id_usuario">    
            <button type="submit">Eliminar</button>
        </form>
    </body>
</html>
