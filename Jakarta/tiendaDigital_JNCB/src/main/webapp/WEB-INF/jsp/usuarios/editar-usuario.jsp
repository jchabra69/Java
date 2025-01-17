<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/header.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/nav.jspf" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <%@ include file="/WEB-INF/jsp/usuarios/editar-usuario-style.jspf" %> <!-- Incluir el estilo -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body>
<main>
    <section>
        <div id="contenedora" style="float:none; margin: 0 auto;width: 900px;">
            <h1>Editar Usuario</h1>

            <form action="${pageContext.request.contextPath}/tienda/usuarios" method="POST">
                <input type="hidden" name="__method__" value="put"/>
                <input type="hidden" name="idUsuario" value="${usuario.idUsuario}"/>

                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="email">Correo Electrónico</label>
                    <input type="email" id="email" name="email" value="${usuario.email}" required class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <input type="password" id="password" name="password" value="${usuario.password}" required class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="rol">Rol</label>
                    <select id="rol" name="rol" required class="form-control">
                        <option value="admin" ${usuario.rol == 'admin' ? 'selected' : ''}>Administrador</option>
                        <option value="cliente" ${usuario.rol == 'cliente' ? 'selected' : ''}>Cliente</option>
                    </select>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-success text-center">Guardar cambios</button>
                </div>
            </form>
        </div>
    </section>
</main>
</body>
</html>

<%@ include file="/WEB-INF/jsp/estructuraWEB/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/style.jspf" %>
