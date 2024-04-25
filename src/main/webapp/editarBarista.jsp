
<%@page import="com.mycompany.integrador_02.logica.Barista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%Barista barista = (Barista) request.getSession().getAttribute("baristaEditar"); %>
        <h2>Editar un Barista</h2>
            <form action="svBarista" method="POST">
                <fieldset>
                    <legend>Datos principales</legend>
                    <input type="hidden" name="accion" value="editar">
                    <input type="hidden" name="baristaId" value="<%= barista.getId() %>">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" required="" value="<%= barista.getNombre() %>">
                    <br>
                    <label for="apellido">Apellido</label>
                    <input type="text" id="apellido" name="apellido" required="" value="<%= barista.getApellido() %>">
                    <br>
                    <label for="dni">Numero de documento</label>
                    <input type="text" id="dni" name="dni" required="" value="<%= barista.getDni() %>">
                    <br>
                    <label for="fechaNac">Fecha de nacimiento</label>
                    <p><%= barista.getFechNac() %></p>
                    <input type="date" id="fechaNac" name="fechaNac" required="">
                    <br>
                    <label for="genero">Genero</label>
                    <input type="text" id="genero" name="genero" required="" value="<%= barista.getGenero()%>">
                    <br>
                    <label for="telefono">Numero de telefono</label>
                    <input type="text" name="telefono" id="telefono" required="" value="<%= barista.getTelefono() %>">
                </fieldset>
                <fieldset>
                    <legend>Datos secundarios</legend>
                    <label for="fechaIngreso">Fecha en la que ingreso a trabajar</label>
                    <p><%= barista.getFechaIngreso() %></p>
                    <input type="date" id="fechaIngreso" name="fechaIngreso" required="">
                    <br>
                    <label for="salario">Importe del salario del barista</label>
                    <input type="text" id="salario" name="salario" required="" value="<%= barista.getSueldo() %>">
                    <br>
                    <label for="diasDeTrabajo">Dias de la semana que trabaja</label>
                    <input type="text" id="diasDeTrabajo" name="diasDeTrabajo" required="" value="<%= barista.getDiasTrabajo() %>">                       
                    <br>
                    <label for="horariosDeTrabajo">Horarios de trabajo (pEj: 10 a 12 pm)</label>
                    <input type="text" id="horariosDeTrabajo" name="horariosDeTrabajo" required="" value="<%= barista.getHorariosTrabajo() %>">                    
                    <br>
                    <label for="habilidadArteLatte">Posee habilidad de arte latte?</label>
                    <input type="checkbox" name="habilidadArteLatte" value="si">(marcar)                 
                    <br>         
                </fieldset>
                <button type="submit">Guardar</button>
            </form>
    </body>
</html>
