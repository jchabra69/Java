<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/header.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/nav.jspf" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Usuarios</title>
  <!-- Enlace a Bootstrap -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
  <style>
    .clearfix::after {
      content: "";
      display: block;
      clear: both;
    }

    /* Margen para los elementos */
    main {
      margin-top: 20px;
    }

    .table-container {
      background-color: #ffffff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    /* Estilo adicional para la tabla */
    table {
      width: 100%;
      margin-bottom: 20px;
    }

    table th, table td {
      text-align: center;
    }

    .form-group {
      margin-bottom: 20px;
    }

    /* Centrado del botón Crear */
    .create-btn-container {
      text-align: center;
      margin-top: 20px;
    }
  </style>
</head>
<body>

<main class="container">
  <section>
    <div class="clearfix table-container">
      <div class="clearfix">
        <div style="float: left; width: 50%">
          <h1>Usuarios</h1>
        </div>
      </div>

      <div class="clearfix">
        <hr />
      </div>

      <table class="table table-bordered table-striped">
        <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Email</th>
          <th>Rol</th>
          <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <%
          if (request.getAttribute("listaUsuarios") != null) {
            List<Usuario> listaUsuario = (List<Usuario>) request.getAttribute("listaUsuarios");

            for (Usuario usuario : listaUsuario) {
        %>
        <tr>
          <td><%= usuario.getIdUsuario() %></td>
          <td><%= usuario.getNombre() %></td>
          <td><%= usuario.getEmail() %></td>
          <td><%= usuario.getRol() %></td>
          <td>
            <form action="${pageContext.request.contextPath}/tienda/usuarios/<%= usuario.getIdUsuario() %>" style="display: inline;">
              <button type="submit" class="btn btn-info btn-sm">Ver Detalle</button>
            </form>
            <form action="${pageContext.request.contextPath}/tienda/usuarios/editar/<%= usuario.getIdUsuario() %>" style="display: inline;">
              <button type="submit" class="btn btn-warning btn-sm">Editar</button>
            </form>
            <form action="${pageContext.request.contextPath}/tienda/usuarios/borrar/" method="post" style="display: inline;">
              <input type="hidden" name="__method__" value="delete"/>
              <input type="hidden" name="id" value="<%= usuario.getIdUsuario() %>"/>
              <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
            </form>
          </td>
        </tr>
        <%
          }
        } else {
        %>
        <tr>
          <td colspan="5" class="text-center">No hay registros de usuarios</td>
        </tr>
        <% } %>
        </tbody>
      </table>

      <!-- Botón Crear centrado fuera del formulario -->
      <div class="create-btn-container">
        <form action="${pageContext.request.contextPath}/tienda/usuarios/crear">
          <button type="submit" class="btn btn-success">Crear</button>
        </form>
      </div>

    </div>
  </section>
</main>

<!-- Enlace a Bootstrap JS y dependencias -->
<%@ include file="/bootstrap.jspf" %>
</body>
</html>

<%@ include file="/WEB-INF/jsp/estructuraWEB/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/style.jspf" %>
