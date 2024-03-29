
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            
            html{
                font-size: 20px;
            }
            
            *{
                
                margin: 0;
                padding: 0;
            }
           
            div{
                width: 500px;
                background-color: #ff33cc;
                padding: 20px;
              
            }
            
            h1{
               width: 400px;
                text-align: center;
                display: block;
                background-color: #b21f2d;
                padding: 4px;
                margin: 0 auto;
                box-sizing: border-box;
                font-size: 3rem;
            }
            h1::before{
                content: "♥";
            }
            h1::after{
                content: "♥";
            }
        </style>
    </head>
    <body>
        <div>
            <h1>Hello!</h1>
        </div>
        
    </body>
</html>
