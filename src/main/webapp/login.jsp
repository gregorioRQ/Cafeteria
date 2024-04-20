
<%@page import="com.mycompany.integrador_02.persistencia.Controlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style><%@include file="estilos/registro_usuarios.css"%></style>
        <title>Sesion Usuario</title>
    </head>
    <body>
        <form action="svLogin" method="POST">
            <fieldset>
                <legend>Inicie sesion</legend>
                <label for="nombreUs">Nombre de usuario</label>
                <input type="hidden" name="accion" value="inicioSesion">
                <input type="text" required="" name="nombreUs" id="nombreUs">
                <label for="contra">Contraseña</label>
                <input type="password" required="" name="contrasenia" id="contra">
            </fieldset>
            <button type="submit">Ingresar</button>
        </form>

        <%
            /*
            El unico que puede crear usuarios es el admin, para hacerlo el formulario solo tiene que ser visible si no hay
            un rol Administrador en la db el formulario estará visible, de este modo los usuarios solo tienen la opcion de iniciar sesion
            con el nombre de usuario y contraseña que haya creado el administrador.
             */
            Controlador control = new Controlador();
            if (! control.comprobarRolExistente()) {%>
        <form action="svLogin" method="POST">
            <fieldset>
                <legend>Cree su usuario</legend>
                <label for="nombreUsu">Nombre de usuario</label>
                <input type="text" id="nombreUsu" name="nombreUs" required="">
                <input type="hidden" name="accion" value="registro">
                <label for="contraseña">Contraseña</label>
                <input type="password" id="contraseña" name="contrasenia" required="">
                <label for="rol">Si es empleado elija su rol</label>
                <select name="rol" id="rol" required="">                 
                    <optgroup>
                        <option>Barista</option>
                        <option>Camarero</option>
                        <%
                            /*
                         Para poder visualizar el boton Administrador, el la db no tiene que existir un rol llamado Administrador,
                            si existe, el boton no se visualiza. el metodo comprobarRolExistente busca todos los usuarios y por medio de un bucle 
                            verifica si el rol ya existe, si el rol ya existe el metodo retorna el valor false y si no retorna true.                           
                              */

                             if (!control.comprobarRolExistente()) { %>
                        <option>Administrador</option>                    
                        <%}%>

                    </optgroup>
                </select>
            </fieldset>
            <button type="submit">Guardar</button>
        </form>
        <% }%>
    </body>
</html>
