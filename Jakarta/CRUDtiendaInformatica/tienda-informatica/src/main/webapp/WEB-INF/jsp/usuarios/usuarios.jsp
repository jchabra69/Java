<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<%@ include file="/boostrap.jspf"%>

<html>
<head>
    <title>Usuarios</title>
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
            <!-- Título -->
            <div class="clearfix">
                <div style="float: left; width: 50%;">
                    <h1>Usuarios</h1>
                </div>
            </div>


            <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
                <!-- Línea Horizontal TOP -->
                <div class="clearfix">
                    <hr/>
                </div>

                <!-- Encabezados -->
                <div class="clearfix">
                    <div style="float: left;width: 10%">ID</div>
                    <div style="float: left;width: 30%">Usuario</div>
                    <div style="float: left;width: 20%">Password</div>
                    <div style="float: left;width: 20%;overflow: hidden;">Rol</div>
                    <div style="float: left;width: 20%;">Acciones</div> <!-- Nueva columna de acciones -->
                </div>

                <!-- Línea BOTTOM -->
                <div class="clearfix">
                    <hr/>
                </div>

                <!-- Mostrar usuarios -->
                <%

                    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");

                    if (usuarios != null && !usuarios.isEmpty()) {
                        for (Usuario usuario : usuarios) {
                %>

                <!-- Mostrar fila de usuario -->
                <div class="clearfix" style="margin-top: 6px;">
                    <div style="float: left;width: 10%"><%= usuario.getIdUsuario() %></div>
                    <div style="float: left;width: 30%"><%= usuario.getNombreUsuario() %></div>
                    <div style="float: left;width: 20%">******</div>
                    <div style="float: left;width: 20%"><%= usuario.getRol() %></div>

                    <!-- Acciones -->
                    <div style="float: left;width: 20%;">
                        <!-- Ver detalles -->
                        <form action="${pageContext.request.contextPath}/tienda/usuarios/<%= usuario.getIdUsuario() %>" style="display: inline;">
                            <input type="submit" value="Ver Detalles" />
                        </form>

                        <!-- Editar -->
                        <form action="${pageContext.request.contextPath}/tienda/usuarios/editar/<%= usuario.getIdUsuario() %>" style="display: inline;">
                            <input type="submit" value="Editar" />
                        </form>

                        <!-- Eliminar -->
                        <form action="${pageContext.request.contextPath}/tienda/usuarios/borrar" method="post" style="display: inline;">
                            <input type="hidden" name="__method__" value="delete" />
                            <input type="hidden" name="idUsuario"  value="<%= usuario.getIdUsuario() %>" />
                            <input type="submit" class="btn btn-danger btn-sm" value="Eliminar" />
                        </form>
                    </div>
                </div>

                <%
                    }
                } else {
                %>
                <p>No hay usuarios registrados</p>
                <%
                    }
                %>
            </div>

        </div>

        <!--Botón para ir a crear-usuario.jsp-->
        <div style="position: absolute; left: 39%; top : 39%;">
            <form action="${pageContext.request.contextPath}/tienda/usuarios/crear">
                <input type="submit" value="Crear">
            </form>
        </div>

    </section>

</main>

</body>
</html>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/style.jspf" %>
