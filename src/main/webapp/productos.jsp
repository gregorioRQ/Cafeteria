
<%@page import="com.mycompany.integrador_02.persistencia.Controlador"%>
<%@page import="com.mycompany.integrador_02.logica.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>Productos disponibles</h1>

        <%
            Controlador control = new Controlador();
            //Castea el objeto traido por getAttribute() a una lista.
            List<Producto> productos = control.buscarProductos();

            //comprueba si la lista no es igual a null para que no ocurra una null pointer exception.
            if (productos != null) {
                for (Producto p : productos) {
        %>   
        <div>
            <h2><%=p.getNombre()%></h2>
            <p><%=p.getDescripcion()%></p>
            <p><%=p.getCategoria()%></p>
            <span><%=p.getPrecio()%></span>
        </div>  
        <%  }
            }%>


    </body>
</html>
