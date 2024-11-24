<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<%@ page import="org.iesbelen.model.Usuario" %>

<%@ include file="/boostrap.jspf" %>

<html>
<head>
    <title>Detalle del Usuario</title>
    <style>
        .clearfix::after {
            content: "";
            display: block;
            clear: both;
        }
    </style>
</head>
<body>

<main>
    <section>

        <!-- Mostrar detalles del usuario -->
        <%
            Usuario usuario = (Usuario) request.getAttribute("usuario");
            if (usuario != null) {
        %>

        <div id="contenedora" style="float:none; margin: 0 auto;width: 900px;">
            <div class="clearfix">
                <h1>Detalles del usuario ${usuario.idUsuario} </h1>
            </div>

            <div class="clearfix">
                <div style="float: left; width: 30%;">ID</div>
                <div style="float: left; width: 70%;">${usuario.idUsuario}</div>
            </div>
            <div class="clearfix">
                <div style="float: left; width: 30%;">Nombre</div>
                <div style="float: left; width: 70%;">${usuario.nombreUsuario}</div>
            </div>
            <div class="clearfix">
                <div style="float: left; width: 30%;">Contraseña</div>
                <div style="float: left; width: 70%;">${usuario.password}</div>
            </div>
            <div class="clearfix">
                <div style="float: left; width: 30%;">Rol</div>
                <div style="float: left; width: 70%;">${usuario.rol}</div>
            </div>
            <%
            } else {
            %>
            <p>No se encontró el usuario.</p>
            <%
                }
            %>

            <!-- Volver -->
            <div class="clearfix" style="margin-top: 20px;">
                <form action="${pageContext.request.contextPath}/tienda/usuarios" method="get">
                    <button type="submit" class="btn btn-dark">Volver</button>
                </form>
            </div>
        </div>
    </section>

</main>

</body>
</html>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/style.jspf" %>
