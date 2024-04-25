
<%@page import="com.mycompany.integrador_02.logica.Camarero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Camarero cam = (Camarero) request.getSession().getAttribute("camareroEditar"); %>
        <h2>Crear un camarero</h2>
        <form method="POST" action="svCamarero">
            <fieldset>
                <legend>Datos principales</legend>
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" required="" value="<%= cam.getNombre()%>">
                <input type="hidden" name="accion" value="editar">
                <input type="hidden" name="camareroId" value="<%= cam.getId() %>">
                <br>
                <label for="apellido">Apellido</label>
                <input type="text" id="apellido" name="apellido" required="" value="<%= cam.getApellido() %>">
                <br>
                <label for="dni">Numero de documento</label>
                <input type="text" id="dni" name="dni" required="" value="<%= cam.getDni() %>">
                <br>
                <label for="fechaNac">Fecha de nacimiento</label>
                <p>(<%= cam.getFechNac() %>)</p>
                <input type="date" id="fechaNac" name="fechaNac" required="" >
                <br>
                <label for="genero">Genero</label>
                <input type="text" id="genero" name="genero" required="" value="<%= cam.getGenero() %>">
                <br>
                <label for="telefono">Numero de telefono</label>
                <input type="text" id="telefono" name="telefono" required="" value="<%= cam.getTelefono() %>">
            </fieldset>
            <fieldset>
                <legend>Datos secundarios</legend>
                <label for="fechaIngreso">Fecha en la que ingreso a trabajar</label>
                <p>(<%= cam.getFechaIngreso() %>)</p>
                <input type="date" id="fechaIngreso" name="fechaIngreso" required="">
                <br>
                <label for="salario">Importe del salario del camarero</label>
                <input type="text" id="salario" name="salario" required="" value="<%= cam.getSueldo() %>">
                <br>
                <label for="diasDeTrabajo">Dias en los que trabaja el camarero</label>
                <input type="text" name="diasDeTrabajo" id="diasDeTrabajo" required="" value="<%= cam.getDiasTrabajo() %>">
                <br>
                <label for="horariosDeTrabajo">Horarios de trabajo.</label>
                <input type="text" id="horariosDeTrabajo" name="horariosDeTrabajo" required="" value="<%= cam.getHorariosTrabajo() %>">
                <br>
                <label for="mesasQueAtiende">Mesas que el camarero atiende</label>
                <input type="text" id="mesasQueAtiende" name="mesasQueAtiende" required="" value="<%= cam.getMesasQueAtiende() %>">
                <br>
                <label for="zonaDeTrabajo">La zona del local donde atendera el camarero</label>
                <input type="text" id="zonaDeTrabajo" name="zonaDeTrabajo" required="" value="<%= cam.getZonaDeTrabajo() %>">
                <br>              
                
            </fieldset>
            <button type="submit">Guardar</button>
        </form>
    </body>
</html>
