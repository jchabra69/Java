<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<%@ include file="/boostrap.jspf"%>

<html>
<head>
    <title>Login</title>
    <style>
        .clearfix::after {
            content: "";
            display: block;
            clear: both;
        }
        .login-container {
            width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f9f9f9;
        }

        .login-title {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .login-button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        /* Mensaje de error */
        .error-message {
            color: red;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<main>

    <section>

        <div id="contenedora" style="width: 100%; float: none; margin: 0 auto; text-align: center;">

            <div class="login-title">
                <h1>Login</h1>
            </div>

            <div class="login-container">
                <form action="${pageContext.request.contextPath}/tienda/usuarios" method="post">
                    <input type="hidden" name="__method__" value="login" />

                    <!-- Usuario -->
                    <div class="form-group">
                        <label for="usuario">Usuario:</label>
                        <input type="text" id="usuario" name="usuario" required="required"/>
                    </div>

                    <!-- Contraseña -->
                    <div class="form-group">
                        <label for="password">Contraseña:</label>
                        <input type="password" id="password" name="password" required="required"/>
                    </div>

                    <!-- Botón para iniciar sesión -->
                    <div class="form-group">
                        <button type="submit" class="login-button">Iniciar sesión</button>
                    </div>
                </form>

            </div>

            <!-- Error que se comunica con el servlet para ver si el usuario mete credenciales k no existen -->
            <%
                String errorMessage = (String) request.getAttribute("loginError");
                if (errorMessage != null) {
            %>
            <div class="error-message">
                <strong><%= errorMessage %></strong>
            </div>
            <%
                }
            %>

            <!-- Error que se comunica con el servlet para ver si el usuario mete credenciales k no existen -->
            <%
                String permError = (String) request.getAttribute("permError");
                if (permError != null) {
            %>
            <div class="error-message">
                <strong><%= permError %></strong>
            </div>
            <%
                }
            %>

            <!-- Crear cuenta desde esta página por si no hay usuarios -->
            <div style="margin-top: 20px;">
                <form action="${pageContext.request.contextPath}/tienda/usuarios/crear">
                    <input type="submit" value="Crear Cuenta">
                </form>
            </div>

        </div>

    </section>

</main>

</body>

</html>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/style.jspf" %>