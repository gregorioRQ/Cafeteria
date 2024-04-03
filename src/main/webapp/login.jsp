
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
                <input type="text" required="" name="usuario" id="nombreUs">
                <label for="contra">Contraseña</label>
                <input type="password" required="" name="contrasenia" id="contra">
            </fieldset>
            <button type="submit">Ingresar</button>
        </form>
        
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
                    </optgroup>
                </select>
            </fieldset>
            <button type="submit">Guardar</button>
        </form>
    </body>
</html>
