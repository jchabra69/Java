<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.model.Departamento"%>
<%@page import="java.util.List"%>
<%@ page import="org.iesbelen.model.Departamento" %>
<%@ page import="org.iesbelen.dto.DepartamentoDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.iesbelen.model.Empleado" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Empleados</title>
    <style>
        .clearfix::after {
            content: "";
            display: block;
            clear: both;
        }
    </style>
</head>

<body>

<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
    <div class="clearfix">
        <div style="float: left; width: 50%">
            <h1>Empleados</h1>
        </div>
        <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
            <div style="position: absolute; left: 39%; top : 39%;">

                <form action="">
                    <input type="submit" value="Crear">
                </form>
            </div>

        </div>
    </div>
    <div class="clearfix">
        <hr/>
    </div>
    <div class="clearfix">
        <div style="float: left;width: 10%">Código</div>
        <div style="float: left;width: 20%">NIF</div>
        <div style="float: left;width: 10%">Nombre</div>
        <div style="float: left;width: 20%">Apellido1</div>
        <div style="float: left;width: 20%">Apellido2</div>s
        <div style="float: none;width: auto;overflow: hidden;">Acción</div>
    </div>
    <div class="clearfix">
        <hr/>
    </div>

    <%
        if (request.getAttribute("listaEmpleado") != null) {
            List<Empleado> listaEmpleado = (List<Empleado>)request.getAttribute("listaEmpleado");

            for (Empleado empleado : listaEmpleado) {
    %>

    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 20%"><%= empleado.getCodigoEmpleado()%></div>
        <div style="float: left;width: 10%"><%= empleado.getDni()%></div>
        <div style="float: left;width: 20%"><%= empleado.getNombre()%></div>
        <div style="float: left;width: 20%"><%= empleado.getApellido1()%></div>
        <div style="float: left;width: 20%"><%= empleado.getApellido2()%></div>
        <div style="float: left;width: 20%"><%= empleado.getCodigoDepartamento()%></div>
        <div style="float: none;width: auto;overflow: hidden;">
            <form action="${pageContext.request.contextPath}/empresa/empleado/editar">
                <input type="submit" value="Editar">
            </form>
            <form action = "" style="display: inline;">
                <input type="submit" value="Eliminar" />
            </form>
        </div>
    </div>

    <%
        }
    } else {

    %>
    <p>No hay registros de empleados</p>

    <% } %>


</div>

</body>
</html>