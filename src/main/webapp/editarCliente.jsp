
<%@page import="com.mycompany.integrador_02.logica.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Cliente cli = (Cliente) request.getSession().getAttribute("clienteEditar"); %>
        <form action="svEditarCliente" method="POST">
               <fieldset>
                        <legend>Datos principales</legend>
                        <label for="nombre">Nombre del cliente</label>
                        <input name="nombre" type="text" id="nombre" required="" value="<%= cli.getNombre()%>">
                        <br>
                        <label for="apellido">Apellido del cliente</label>
                        <input name="apellido" type="text" id="apellido"   required="" value="<%= cli.getApellido()%>">
                        <br>
                        <label for="dni">DNI del cliente</label>
                        <input name="dni" type="text" id="dni"   required="" value="<%= cli.getDni()%>">
                    </fieldset>

                    <fieldset>
                        <legend>Datos secundarios</legend>
                        <label for="telefono">Telefono</label>
                        <input name="telefono" type="text" id="telefono" value="<%= cli.getTelefono()%>">
                        <br>
                        <label for="fechaNac">Fecha de nacimiento</label>
                        <input  name="fechaNac" id="fechaNac" type="date" required="">
                        <br>

                        <label for="genero">Genero</label>
                        <select  name="genero" id="genero">  
                            <option>Masculino</option>
                            <option>Femenino</option>                                   
                        </select>
                    </fieldset>
                    <button type="submit">Guardar</button>
        </form>
    </body>
</html>
