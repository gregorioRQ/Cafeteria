
<%@page import="java.util.Locale"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <style><%@include file="estilos/registro.css"%></style>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Registro</title>
    </head>
    <body>
        
       <%
           /*
        Con este procedimiento el sistema verifica si existe una sesion.
           en el caso de que exista permite al usuario ver el html
           en el caso que no exista se lo redirije hacia la pagina principal.
        */
      HttpSession misesion =   request.getSession();
      String usuario = (String) request.getSession().getAttribute("usuario");
     
      if(usuario == null){
      response.sendRedirect("index.jsp");
    }
%>
        <section>
            <div class="div_a">
                <a class="a_div" href="index.jsp">Home</a>
            </div>
         

                <form class="formulario" id="contact" action="" method="POST">
                    <fieldset class="fielset_form">
                        <legend>Datos principales</legend>
                        <label for="nombre">Nombre del cliente</label>
                        <input name="nombre" type="text" id="nombre" required="">
                        <br>
                        <label for="apellido">Apellido del cliente</label>
                        <input name="apellido" type="text" id="apellido"   required="">
                        <br>
                        <label for="dni">DNI del cliente</label>
                        <input name="dni" type="text" id="dni" maxlength="8"   required="">
                    </fieldset>

                    <fieldset class="fielset_form">
                        <legend>Datos secundarios</legend>
                        <label for="telefono">Telefono</label>
                        <input name="telefono" type="text" id="telefono"  required="" placeholder="sin espacios ni guiones" maxlength="10">
                        <br>
                        <label for="fechaNac">Fecha de nacimiento</label>
                        <input  name="fechaNac" id="fechaNac" type="date">
                        <br>

                        <label for="genero">Genero</label>
                        <select class="select_form"  name="genero" id="genero">  
                            <option>Masculino</option>
                            <option>Femenino</option>                                   
                        </select>
                    </fieldset>
                    <button class="boton_form" type="submit">Guardar</button>
                </form>
  
 

            <div class="cont_table">
                <h2>Clientes registrados</h2>
                <table>
                    <thead>
                        <tr>
                    <th>NOMBRE</th>
                    <th>APELLIDO</th>
                    <th>TELEFONO</th>
                    <th>GENERO</th>
                    <th>FECHA NAC</th>
                    <th>DNI</th>
                    <th></th>
                    <th></th>
                    </tr>
                    </thead>
                    <tbody>
                     <%                      
                   /* List<Cliente> clientes = (List) request.getSession().getAttribute("clientes");
                    if (clientes != null) {
                        for (Cliente cli : clientes) {
                           Date fechaEmision = cli.getFechNac();
                           SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                           String fechaFormateada = formatoFecha.format(fechaEmision);*/
                     %> 
                        <tr>
                            <td><%=//cli.getNombre()%></td>
                            <td><%=//cli.getApellido()%></td>
                            <td><%=//cli.getTelefono()%></td>
                            <td><%=//cli.getGenero()%></td>
                            <td><%=//fechaFormateada%></td>
                            <td><%=// cli.getDni() %></td>
                            <td>
                                <form action="svEditarCliente" method="GET">
                                    <input type="hidden" value="<%=// cli.getId()%>" name="id_clienteEdit">
                                    <button type="submit">Editar</button>
                                </form>
                            </td>
                            <td>
                                <form action="svEliminarCliente" method="POST">
                                    <input type="hidden" value="<%=// cli.getId()%>" name="id_cliente">    
                                    <button class="td_form_boton-eliminar" type="submit">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                          <% }
                //  }%> 
                    </tbody>
                </table>  
         

            </div>
        </section>
    </body>
</html>
