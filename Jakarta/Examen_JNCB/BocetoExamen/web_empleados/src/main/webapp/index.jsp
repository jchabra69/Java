<%@ page import="jakarta.servlet.http.HttpSession" %>

<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        #contenedor {
            display: flex;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>


<div class="d-grid gap-2" id="contenedor">
    <a class="btn btn-primary btn-lg" href="<%= application.getContextPath() %>/empresa/departamento">DEPARTAMENTOS</a>
    <a class="btn btn-success btn-lg" href="<%= application.getContextPath() %>/empresa/empleado">EMPLEADOS</a>
</div>

<%@ include file="boostrap.jspf" %>
</body>
</html>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/style.jspf" %>
