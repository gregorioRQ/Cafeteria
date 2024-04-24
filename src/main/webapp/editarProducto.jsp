
<%@page import="com.mycompany.integrador_02.logica.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body>
        <h1>Edite el producto</h1>
        <% Producto p = (Producto) request.getSession().getAttribute("productoEditar"); %>
        <form action="svProducto" method="POST">
                <fieldset>
                    <legend>Añadir un nuevo producto</legend>
                    <label for="nombre">Nombre del producto</label>
                    <input type="hidden" value="editar" name="accion">
                    <input type="hidden" value="<%=p.getId()%>" name="idProducto">
                    <input type="hidden" value="<%=p.getUnCafe().getId()%>" name="idCafe">
                    <input type="text" id="nombre" name="nombre" value="<%=p.getNombre()%>">
                        <br>
                        <label for="descripcion">Añada una descripcion</label>
                        <input type="text" id="descripcion" name="descripcion" value="<%=p.getDescripcion()%>">
                        <br>
                        <label for="precio">Precio</label>
                        <input type="text" id="precio" name="precio" value="<%=p.getPrecio()%>">
                        <br>
                        <label>El producto esta disponible?</label>
                        <label for="si">Si
                        <input type="checkbox" id="si" name="disponible">
                        
                        <br>
                         <label for="categoria">Categoria</label>
                         <input type="text" id="categoria" name="categoria" value="<%=p.getCategoria()%>">
                         <br>                   
                    <label for="variedadDeCafe">Variedad de cafe</label>
                    <input type="text" name="variedadDeCafe" id="variedadDeCafe" value="<%=p.getUnCafe().getNombre()%>">
                    <label for="decripcionDelCafe">Descripion del cafe</label>
                    <input type="text" name="descripcionDelCafe" id="descripcionDelCafe" value="<%=p.getDescripcion()%>">
                    <br>
                    <label for="barista">Nombre de usuario del barista que se encargara de hacerlo</label>
                    <input type="text" id="barista" name="nombreDeUsuario" value="<%=p.getUnBarista().getUnUsuario().getNombreUs()%>">
          
                </fieldset>
                <button type="submit">Guardar</button>
            </form>

    </body>
</html>
