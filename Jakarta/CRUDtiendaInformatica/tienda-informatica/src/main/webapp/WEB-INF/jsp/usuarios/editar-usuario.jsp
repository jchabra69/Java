<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<%@ page import="org.iesbelen.model.Usuario" %>
<%@ include file="/boostrap.jspf" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
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
        <div id="contenedora" style="float:none; margin: 0 auto;width: 900px;">
            <div class="clearfix">
                <h1>Editar Usuario</h1>
            </div>

            <form action="${pageContext.request.contextPath}/tienda/usuarios/editar/${usuario.idUsuario}" method="post">
                <input type="hidden" name="__method__" value="put" />
                <input type="hidden" name="idUsuario" value="${usuario.idUsuario}" />

                <div class="clearfix">
                    <div style="float: left; width: 30%;">Nombre</div>
                    <div style="float: left; width: 70%;">
                        <input type="text" name="nombre" value="${usuario.nombreUsuario}" />
                    </div>
                </div>

                <div class="clearfix">
                    <div style="float: left; width: 30%;">Contraseña</div>
                    <div style="float: left; width: 70%;">
                        <input type="password" name="password" value="" placeholder="" />
                    </div>
                </div>

                <div class="clearfix">
                    <div style="float: left; width: 30%;">Rol</div>
                    <div style="float: left; width: 70%;">
                        <select name="rol">
                            <option value="admin" ${usuario.rol == 'admin' ? 'selected' : ''}>Admin</option>
                            <option value="employee" ${usuario.rol == 'employee' ? 'selected' : ''}>Empleado</option>
                            <option value="customer" ${usuario.rol == 'customer' ? 'selected' : ''}>Cliente</option>
                        </select>
                    </div>
                </div>

                <div class="clearfix" style="margin-top: 20px;">
                    <input type="submit" value="Actualizar Usuario" />
                </div>
            </form>

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
