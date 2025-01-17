<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.iesbelen.model.Usuario" %>

<%@ include file="/WEB-INF/jsp/estructuraWEB/header.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/nav.jspf" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalle Usuario</title>
    <%@ include file="/WEB-INF/jsp/usuarios/detalle-usuario-style.jspf" %> <!-- Incluir el estilo -->
</head>
<body>

<!-- Contenedor principal con espacio entre el nav y el contenedor -->
<div id="contenedora" style="float:none; margin: 40px auto; width: 900px;">
    <div class="clearfix">
        <div style="float: left; width: 50%">
            <h1>Detalle Usuario</h1>
        </div>
        <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
            <div style="position: absolute; left: 39%; top: 39%;">
                <form action="${pageContext.request.contextPath}/tienda/usuarios">
                    <input type="submit" value="Volver" />
                </form>
            </div>
        </div>
    </div>

    <div class="clearfix">
        <hr/>
    </div>

    <%
        // Accede directamente al atributo 'usuario' sin Optional
        Usuario usuario = (Usuario) request.getAttribute("usuario");
        if (usuario != null) {
    %>

    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>ID</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= usuario.getIdUsuario() %>" readonly="readonly"/>
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Nombre</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= usuario.getNombre() %>" readonly="readonly"/>
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Email</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= usuario.getEmail() %>" readonly="readonly"/>
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Contraseña</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= usuario.getPassword() %>" readonly="readonly"/>
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Rol</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= usuario.getRol() %>" readonly="readonly"/>
        </div>
    </div>

    <% } else { %>

    <script>
        window.location.href = "${pageContext.request.contextPath}/tienda/usuarios";
    </script>

    <% } %>
</div>

</body>
</html>

<%@ include file="/WEB-INF/jsp/estructuraWEB/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/style.jspf" %>
