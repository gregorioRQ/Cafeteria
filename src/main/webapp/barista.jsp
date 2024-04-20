
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
        HttpSession misesion =   request.getSession();
        String usuario = (String) request.getSession().getAttribute("nombreUs");
        
        if(usuario == null){
        response.sendRedirect("index.jsp");
            }
            Controlador control = new Controlador();
            List<Pedido> pedidos = control.buscarPedidos();
        %>
        <h1>Hello Barista</h1>
        
        
        
        <div>
            <h2>Editar un producto</h2>
            <form action="svEditarProducto" method="GET">
                                    <input type="text" name="nombre_producto_editar">
                                    <button type="submit">Editar</button>
                                </form>
        </div>
        
        <div>
            <% for(Pedido p : pedidos){%>
                <% List<Producto> productos = p.getProductos();%>
                <h2>Pedio entrante</h2>
                <%for(Producto pro : productos){%>
                     <h3><%= pro.getNombre()%></h3>
               <% }%>
               <h4><%=p.getNumMesa()%></h4>
               <form action="svPedido" method="POST">
                   <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" value="<%=p.getId()%>" name="idPedido">    
                                    <button  type="submit">Eliminar orden</button>
                                </form>
            <%}%>
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
