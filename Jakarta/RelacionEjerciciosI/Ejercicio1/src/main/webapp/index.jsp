<%@ page import="java.util.ArrayList" %><% //Mi codigito Java

    String[] nombresAnimales = {"ballena", "Caballito de Mar",
    "Camello", "Cebra", "Elefante", "Hipopotamo", "Jirafa", "León",
            "Leopardo", "Medusa", "Mono", "Oso", "Oso Blanco", "Pájaro",
            "Pinguino", "Serpiente", "Tigre", "Tortuga", "Tortuga-Marina"
    };

    Integer animalAleatorio = (int) (Math.random() * nombresAnimales.length);





%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<h1>Nombres de animales</h1>
<p>Actualice la página para mostrar un nuevo animal</p>

<h2><%= nombresAnimales[animalAleatorio] %></h2>
<img src = "imgAnimales/<%= nombresAnimales[animalAleatorio].toLowerCase() %> .svg" >

</body>
</html>