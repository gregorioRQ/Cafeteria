
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form action="svPedidos" method="POST">
            <h2>Añada un pedido </h2>
            <fieldset>
                <legend>Datos de la mesa</legend>
                <label for="capacidad">Capacidad</label>
                <input type="text" name="capacidad" id="capacidad" required="">
                <br>
                <label for="ocupada">La mesa esta ocupada?</label>
                <input type="radio" name="ocupada" id="ocupada">
                <br>
                <label for="ubicacion">Donde se halla la mesa</label>
                <input type="radio" name="ubicacion" id="ubicacion" required="" value="hola">
                
            </fieldset>
            <button type="submit">Añadir</button>
        </form>
        <h1>lista de mesas </h1>
    </body>
</html>
