
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
        <h1>Hello World!</h1>
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
            <form method="POST" action="svProducto">
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="idProducto" value="<%=p.getId()%>">
                <button type="submit">Eliminar producto</button>
            </form>
        </div>  
        <%  }
            }%>
            <button><a href="administrador.jsp">Volver</a></button>
    </body>
</html>
