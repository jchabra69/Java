
<%
    //En este ficherito, puedo usar HTML y Java
    System.out.println("Holiwis");

    int var = 1 + 1;

%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Formulario" %>
</h1>

<form action = "hello-servlet" method = "get">

    <div>

        <label>Email</label>
        <input name="email" type = "email">
        <p><%= var%></p>

    </div>

    <button type = "submit"> Enviar</button>

</form>

</body>
</html>

