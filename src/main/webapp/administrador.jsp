
<%@page import="com.mycompany.integrador_02.logica.Usuario"%>
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
            String nombreUs = (String) request.getSession().getAttribute("nombreUs");

            if (nombreUs == null) {
                response.sendRedirect("index.jsp");
            }
        %> 

        <h1>Bienvenido Administrador</h1>

        <h2>Crear un Usuario</h2>
        <form action="svLogin" method="POST">
            <fieldset>
                <legend>Datos del nuevo usuario</legend>
                <input type="hidden" name="accion" value="registro">
                <label for="nombreUsu">Nombre de usuario</label>
                <input type="text" id="nombreUsu" name="nombreUs" required="">
                <input type="hidden" name="accion" value="registro">
                <label for="contraseña">Contraseña</label>
                <input type="password" id="contraseña" name="contrasenia" required="">
                <label for="rol">Rol del empleado</label>
                <select name="rol" id="rol" required="">                 
                    <optgroup>
                        <option>Barista</option>
                        <option>Camarero</option>                      
                    </optgroup>
                </select>
            </fieldset>
            <button type="submit">Añadir Usuario</button>
        </form>

        <div>
            <h2>Crear un Barista</h2>
            <form action="svBarista" method="POST">
                <fieldset>
                    <legend>Datos principales</legend>
                    <input type="hidden" name="accion" value="registrar">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" required="">
                    <br>
                    <label for="apellido">Apellido</label>
                    <input type="text" id="apellido" name="apellido" required="">
                    <br>
                    <label for="dni">Numero de documento</label>
                    <input type="text" id="dni" name="dni" required="">
                    <br>
                    <label for="fechaNac">Fecha de nacimiento</label>
                    <input type="date" id="fechaNac" name="fechaNac" required="">
                    <br>
                    <label for="genero">Genero</label>
                    <input type="text" id="genero" name="genero" required="">
                    <br>
                    <label for="telefono">Numero de telefono</label>
                    <input type="text" name="telefono" id="telefono" required="">
                </fieldset>
                <fieldset>
                    <legend>Datos secundarios</legend>
                    <label for="fechaIngreso">Fecha en la que ingreso a trabajar</label>
                    <input type="date" id="fechaIngreso" name="fechaIngreso" required="">
                    <br>
                    <label for="salario">Importe del salario del barista</label>
                    <input type="text" id="salario" name="salario" required="">
                    <br>
                    <label for="diasDeTrabajo">Dias de la semana que trabaja</label>
                    <input type="text" id="diasDeTrabajo" name="diasDeTrabajo" required="">                       
                    <br>
                    <label for="horariosDeTrabajo">Horarios de trabajo (pEj: 10 a 12 pm)</label>
                    <input type="text" id="horariosDeTrabajo" name="horariosDeTrabajo" required="">                    
                    <br>
                    <label for="habilidadArteLatte">Posee habilidad de arte latte?</label>
                    <input type="checkbox" name="habilidadArteLatte" value="si">(marcar)                 
                    <br>         
                    <label for="nombreUsuario">Asigne el nombre de usuario para el barista</label>
                    <input type="text" name="nombreDeUsuario" id="nombreUsuario" required="">
                </fieldset>
                <button type="submit">Añadir barista</button>
            </form>

            <form action="svBarista" method="POST">
                <h4>Eliminar Barista</h4>
                <input type="hidden" name="accion" value="eliminar">
                <input type="text" name="nombreUsuario" placeholder="nombre de usuario">
                <button type="submit">Eliminar</button>
            </form>
            
            <form action="svBarista" method="GET">
                <h4>Editar barista</h4>
                <input type="hidden" name="accion" value="editar">
                <label for="nombreDeUsuario">Nombre del usuario del barista que desea editar </label>
                <br>
                <input type="text" name="nombreDeUsuario" id="nombreDeUsuario" required="">
                <br>
                <button type="submit">Enviar</button>
            </form>
        </div>

        <h2>Crear un camarero</h2>
        <form method="POST" action="svCamarero">
            <fieldset>
                <legend>Datos principales</legend>
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" required="">
                <input type="hidden" name="accion" value="agregar">
                <br>
                <label for="apellido">Apellido</label>
                <input type="text" id="apellido" name="apellido" required="">
                <br>
                <label for="dni">Numero de documento</label>
                <input type="text" id="dni" name="dni" required="">
                <br>
                <label for="fechaNac">Fecha de nacimiento</label>
                <input type="date" id="fechaNac" name="fechaNac" required="">
                <br>
                <label for="genero">Genero</label>
                <input type="text" id="genero" name="genero" required="">
                <br>
                <label for="telefono">Numero de telefono</label>
                <input type="text" id="telefono" name="telefono" required="">
            </fieldset>
            <fieldset>
                <legend>Datos secundarios</legend>
                <label for="fechaIngreso">Fecha en la que ingreso a trabajar</label>
                <input type="date" id="fechaIngreso" name="fechaIngreso" required="">
                <br>
                <label for="salario">Importe del salario del camarero</label>
                <input type="text" id="salario" name="salario" required="">
                <br>
                <label for="diasDeTrabajo">Dias en los que trabaja el camarero</label>
                <input type="text" name="diasDeTrabajo" id="diasDeTrabajo" required="">
                <br>
                <label for="horariosDeTrabajo">Horarios de trabajo.</label>
                <input type="text" id="horariosDeTrabajo" name="horariosDeTrabajo" required="">
                <br>
                <label for="mesasQueAtiende">Mesas que el camarero atiende</label>
                <input type="text" id="mesasQueAtiende" name="mesasQueAtiende" required="">
                <br>
                <label for="zonaDeTrabajo">La zona del local donde atendera el camarero</label>
                <input type="text" id="zonaDeTrabajo" name="zonaDeTrabajo" required="">
                <br>              
                <label for="nombreUsuario">Asigne el usuario para el camarero</label>
                <input type="text" name="nombreDeUsuario" id="nombreUsuario" required="">
            </fieldset>
            <button type="submit">Añadir Camarero</button>
        </form>
        <form action="svCamarero" method="POST">
            <h4>Eliminar Camarero</h4>
            <input type="hidden" name="accion" value="eliminar">
            <input type="text" name="nombreUsuario" placeholder="nombre de usuario">
            <button type="submit">Eliminar</button>
        </form>
         <form action="svCamarero" method="GET">
                <h4>Editar camarero</h4>
                
                <label for="nombreDeUsuario">Nombre del usuario del camarero que desea editar </label>
                <br>
                <input type="text" name="nombreDeUsuario" id="nombreDeUsuario" required="">
                <br>
                <button type="submit">Enviar</button>
            </form>

        <div>
            <h2>Crear un Producto</h2>
            <form action="svProducto" method="POST">
                <fieldset>
                    <legend>Datos del nuevo producto</legend>
                    <label for="nombre">Nombre del producto</label>
                    <input type="text" id="nombre" name="nombre">
                    <input type="hidden" name="accion" value="agregar">
                    <br>
                    <label for="descripcion">Añada una descripcion</label>
                    <input type="text" id="descripcion" name="descripcion">
                    <br>
                    <label for="precio">Precio</label>
                    <input type="text" id="precio" name="precio">
                    <br>
                    <label>El producto esta disponible?</label>
                    <label for="si">Si
                        <input type="checkbox" id="si" name="disponible" value="si">
                    </label>
                    <label for="no">No
                        <input type="checkbox" id="no" name="disponible" value="no">
                    </label>
                    <br>
                    <label for="categoria">Categoria</label>
                    <input type="text" id="categoria" name="categoria">
                    <br>                   
                    <label for="variedadDeCafe">Variedad de cafe</label>
                    <input type="text" name="variedadDeCafe" id="variedadDeCafe">
                    <label for="decripcionDelCafe">Descripion del cafe</label>
                    <input type="text" name="descripcionDelCafe" id="descripcionDelCafe">
                    <br>
                    <label for="barista">Nombre de usuario del barista que se encargara de hacerlo</label>
                    <input type="text" id="barista" name="nombreDeUsuario">

                </fieldset>
                <button type="submit">Añadir Producto</button>
            </form>
        </div>
        
        <button><a href="administrarProductos.jsp">Ver todos lo productos</a></button>
        
        <form action="svCerrarSesion" method="POST">
            <input type="submit" value="Cerrar Sesión">
        </form>
        <%
            int idUsu = (Integer) request.getSession().getAttribute("id_usuario");

        %>

        <form action="svLogin" method="POST">
            <input type="hidden" name="accion" value="eliminarUsuario">
            <input type="hidden" value="<%= idUsu%>" name="id_usuario">    
            <button type="submit">Eliminar</button>
        </form>
    </body>
</html>
