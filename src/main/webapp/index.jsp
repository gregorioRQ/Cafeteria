<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content=""> 
        <title>Cafeteria</title>
    </head>

    <body>
        

        <h1>INICIO DE LA APP</h1>
       
       
       <%if ("Administrador".equals(session.getAttribute("rol"))) {%>
           <%@include file="administrador.jsp"%>
       
      <%}else if("Camarero".equals(session.getAttribute("rol"))){%>
       <%@include file="camarero.jsp" %>
       
       <%}else if("Barista".equals(session.getAttribute("rol"))){%>
       <%@include file="barista.jsp" %>
        
        <%}else if(session.getAttribute("rol") != "Administrador" || session.getAttribute("rol") != "Camarero" || session.getAttribute("rol") != "Barista" ){%>
       <%@include file="productos.jsp"%>
         <h3>Para empleados del local</h3>
         <a href="login.jsp">Log in</a>
        <%}%>
     
    </body>
</html>