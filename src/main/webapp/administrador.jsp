
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
                <input type="hidden" name="accion" value="registrar">
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
                    <input type="text" id="nombre" name="nombre">
                    <br>
                    <label for="apellido">Apellido</label>
                    <input type="text" id="apellido" name="apellido">
                    <br>
                    <label for="dni">Numero de documento</label>
                    <input type="text" id="dni" name="dni">
                    <br>
                    <label for="fechaNac">Fecha de nacimiento</label>
                    <input type="date" id="fechaNac" name="fechaNac">
                    <br>
                    <label for="genero">Genero</label>
                    <input type="text" id="genero" name="genero">
                    <br>
                    <label for="telefono">Numero de telefono</label>
                    <input type="text" name="telefono" id="telefono">
                </fieldset>
                <fieldset>
                    <legend>Datos secundarios</legend>
                    <label for="fechaIngreso">Fecha en la que ingreso a trabajar</label>
                    <input type="text" id="fechaIngreso" name="fechaIngreso">
                    <br>
                    <label for="salario">Importe del salario del barista</label>
                    <input type="text" id="salario" name="salario">
                    <br>
                    <label for="diasDeTrabajo">Dias de trabajo</label>
                    <select id="diasDeTrabajo" name="diasDeTrabajo">
                        <option value="first">text1</option>
                        <option value="second">text2</option>
                        <option value="third">text3</option>
                    </select>
                    <br>
                    <label for="horariosDeTrabajo">Horarios de trabajo.</label>
                    <select id="horariosDeTrabajo" name="horariosDeTrabajo">
                        <option value="first">text1</option>
                        <option value="second">text2</option>
                        <option value="third">text3</option>
                    </select>
                    <br>
                    <label for="habilidadArteLatte">Posee habilidad de arte latte?</label>
                    <input type="checkbox" name="habilidadArteLatte">Si
                    <input type="checkbox" name="habilidadArteLatte">No
                    <br>
                    <label for="variedadesDeCafe">Variedades de Cafe</label>
                    <input type="checkbox" id="variedadesDeCafe" name="variedadesDeCafe">capu
                    <input type="checkbox" id="variedadesDeCafe" name="variedadesDeCafe">expreso
                    <br>
                    <label for="metodosDeExtraccion">Metodos de extraccion</label>
                    <input type="checkbox" id="metodosDeExtraccion" name="metodosDeExtraccion">metodo1
                    <input type="checkbox" id="metodosDeExtraccion" name="metodosDeExtraccion">metodo2
                    <input type="checkbox" id="metodosDeExtraccion" name="metodosDeExtraccion">metodo3
                    <br>
                    <label for="nombreUsuario">Asigne el nombre de usuario para el barista</label>
                    <input type="text" name="nombreDeUsuario" id="nombreUsuario">
                </fieldset>
                <button type="submit">Añadir barista</button>
            </form>

            <form action="svBarista" method="POST">
                <h4>Eliminar Barista</h4>
                <input type="hidden" name="accion" value="eliminar">
                <input type="text" name="nombreUsuario" placeholder="nombre de usuario">
                <button type="submit">Eliminar</button>
            </form>
        </div>

        <h2>Crear un camarero</h2>
        <form method="POST" action="svCamarero">
            <fieldset>
                <legend>Datos principales</legend>
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre">
                <input type="hidden" name="accion" value="agregar">
                <br>
                <label for="apellido">Apellido</label>
                <input type="text" id="apellido" name="apellido">
                <br>
                <label for="dni">Numero de documento</label>
                <input type="text" id="dni" name="dni">
                <br>
                <label for="fechaNac">Fecha de nacimiento</label>
                <input type="date" id="fechaNac" name="fechaNac">
                <br>
                <label for="genero">Genero</label>
                <input type="text" id="genero" name="genero">
                <br>
                <label for="telefono">Numero de telefono</label>
                <input type="text" id="telefono" name="telefono">
            </fieldset>
            <fieldset>
                <legend>Datos secundarios</legend>
                <label for="fechaIngreso">Fecha en la que ingreso a trabajar</label>
                <input type="text" id="fechaIngreso" name="fechaIngreso">
                <br>
                <label for="salario">Importe del salario del camarero</label>
                <input type="text" id="salario" name="salario">
                <br>
                <label for="diasDeTrabajo">Dias de trabajo</label>
                <select id="diasDeTrabajo" name="diasDeTrabajo">
                    <option value="first">text1</option>
                    <option value="second">text2</option>
                    <option value="third">text3</option>
                </select>
                <br>
                <label for="horariosDeTrabajo">Horarios de trabajo.</label>
                <select id="horariosDeTrabajo" name="horariosDeTrabajo">
                    <option value="first">text1</option>
                    <option value="second">text2</option>
                    <option value="third">text3</option>
                </select>
                <br>
                <label for="mesasQueAtiende">Mesas que el camarero atiende</label>
                <input type="text" id="mesasQueAtiende" name="mesasQueAtiende">
                <br>
                <label for="zonaDeTrabajo">La zona del local donde atendera el camarero</label>
                <input type="text" id="zonaDeTrabajo" name="zonaDeTrabajo">
                <br>              
                <label for="nombreUsuario">Asigne el nombre de usuario para el camarero</label>
                <input type="text" name="nombreDeUsuario" id="nombreUsuario">
            </fieldset>
            <button type="submit">Añadir Camarero</button>
        </form>
        <form action="svCamarero" method="POST">
            <h4>Eliminar Camarero</h4>
            <input type="hidden" name="accion" value="eliminar">
            <input type="text" name="nombreUsuario" placeholder="nombre de usuario">
            <button type="submit">Eliminar</button>
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
