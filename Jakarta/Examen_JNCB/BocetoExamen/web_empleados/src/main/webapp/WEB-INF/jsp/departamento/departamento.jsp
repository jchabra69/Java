<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.model.Departamento"%>
<%@page import="java.util.List"%>
<%@ page import="org.iesbelen.model.Departamento" %>
<%@ page import="org.iesbelen.dto.DepartamentoDTO" %>
<%@ page import="java.util.ArrayList" %>

<!--ESTO ES PARA MOSTRA EL TOTAL DE EMPLEADOS POR DEPARTAMENTO, QUE POR ALGUN ERROR TONTO NO ME VA, Y NO ME DA TIEMPO YA-->
<%
    List<DepartamentoDTO> misDepartamentos = new ArrayList<>();

    if (request.getAttribute("listaDepartamentos") != null) {
        List<?> rawList = (List<?>) request.getAttribute("listaDepartamentos");
        for (Object obj : rawList) {
            if (obj instanceof DepartamentoDTO) {
                misDepartamentos.add((DepartamentoDTO) obj);
            } else if (obj instanceof Departamento) {
                Departamento departamento = (Departamento) obj;
                misDepartamentos.add(new DepartamentoDTO(departamento));
            }
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Departamentos</title>
    <style>
        .clearfix::after {
            content: "";
            display: block;
            clear: both;
        }
    </style>
</head>
<body>
<body>

<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
    <div class="clearfix">
        <div style="float: left; width: 50%">
            <h1>Departamentos</h1>
        </div>
        <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
            <div style="position: absolute; left: 39%; top : 39%;">

                <form action="${pageContext.request.contextPath}/empresa/departamento/crear">
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
        <div style="float: left;width: 20%">Nombre</div>
        <div style="float: left;width: 10%">Nº Empleados</div>
        <div style="float: left;width: 20%">Presupuesto</div>
        <div style="float: left;width: 20%">Gastos</div>
        <div style="float: none;width: auto;overflow: hidden;">Acción</div>
    </div>
    <div class="clearfix">
        <hr/>
    </div>
    <%
        if (request.getAttribute("listaDepartamento") != null) {
            List<Departamento> listaDepartamento = (List<Departamento>)request.getAttribute("listaDepartamento");

            for (Departamento departamento : listaDepartamento) {
    %>

    <!--PARA MOSTRAR EL TOTAL DE EMPLEADO-->
    <%
        /* if (misDepartamentos != null && !misDepartamentos.isEmpty()) {
            for (DepartamentoDTO d : misDepartamentos) { */
    %>

    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 10%"><%= departamento.getCodigoDepartamento()%></div>
        <div style="float: left;width: 20%"><%= departamento.getNombre()%></div>

        <div style="float: left;width: 20%"><%= departamento.getPresupuesto()%></div>
        <div style="float: left;width: 20%"><%= departamento.getGastos()%></div>
        <div style="float: none;width: auto;overflow: hidden;">
            <form action = "" style="display: inline;">
                <input type="submit" value="Editar" />
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
    No hay registros de departamentos
    <% } %>

</div>
</body>
</body>
</html>