
<%@page import="com.mycompany.integrador_02.logica.Producto"%>
<%@page import="com.mycompany.integrador_02.logica.Pedido"%>
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
            String usuario = (String) request.getSession().getAttribute("nombreUs");
 
            if (usuario == null) {
                response.sendRedirect("index.jsp");
            }
            Controlador control = new Controlador();
            List<Pedido> pedidos = control.buscarPedidos();
        %>
        <h1>Hello Barista</h1>
        <h5>USUARIO:<%=usuario%> </h5>

        <div>
            <h2>Ordenes pendientes:</h2>
            
            <% for (Pedido p : pedidos) {%>
            <div>
            <h3>Pedido: </h3>
            <p>id: <%=p.getId()%></p>
                    <%for (Producto pro : p.getProductos()) {%>
                    <p>+++++++++++++++++++++++++++++++++++++++++++++++++++++</p>
                    <p>Nombre del Producto: <%= pro.getNombre()%></p>
                    <p>Contenido: </p>
                     <p><%=pro.getUnCafe().getNombre()%></p>
                    <p>Descripcion: <%=pro.getDescripcion()%></p>          
                    <p>Numero de mesa: <%=p.getNumMesa()%></p>                   
                     <%};%>
            <form action="svPedido" method="POST">
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" value="<%=p.getId()%>" name="idPedido">    
                <button  type="submit">Eliminar pedido</button>
            </form>
                <p>-----------------------------------------------------------------</p>
                <div>
            <%};%>
        </div>



        <form action="svCerrarSesion" method="POST">
            <input type="submit" value="Cerrar Sesión">
        </form>
        <%
            int idUsu = (Integer) request.getSession().getAttribute("id_usuario");

        %>

        <form action="svLogin" method="POST">
            <input type="hidden" name="accion" value="eliminarUsuario">
            <input type="hidden" value="<%= idUsu%>" name="id_usuario">    
            <button type="submit">Borrar usuario</button>
        </form>
    </body>
</html>
