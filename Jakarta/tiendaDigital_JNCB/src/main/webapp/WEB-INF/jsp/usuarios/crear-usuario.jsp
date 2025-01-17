<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/jsp/estructuraWEB/header.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/nav.jspf" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>Crear Usuario</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
  <%@ include file="/WEB-INF/jsp/usuarios/style.jspf" %>
</head>
<body>

<div class="container mt-5">
  <div class="form-container">
    <h1 class="text-center mb-4">Crear Usuario</h1>

    <form action="${pageContext.request.contextPath}/tienda/usuarios/crear/" method="post">

      <!-- Nombre -->
      <div class="form-group">
        <label for="nombre">Nombre</label>
        <input id="nombre" name="nombre" type="text" class="form-control" placeholder="Ingrese el nombre del usuario" required />
      </div>

      <!-- Email -->
      <div class="form-group">
        <label for="email">Email</label>
        <input id="email" name="email" type="email" class="form-control" placeholder="Ingrese el email del usuario" required />
      </div>

      <!-- Contraseña -->
      <div class="form-group">
        <label for="password">Contraseña</label>
        <input id="password" name="password" type="password" class="form-control" placeholder="Ingrese la contraseña" required />
      </div>

      <!-- Rol -->
      <div class="form-group">
        <label for="rol">Rol</label>
        <select name="rol" id="rol" class="form-control" required>
          <option value="">Seleccione un rol</option>
          <option value="admin">Administrador</option>
          <option value="cliente">Cliente</option>
        </select>
      </div>

      <!-- Botón Crear -->
      <div class="create-btn-container">
        <button type="submit" class="btn btn-primary">Crear Usuario</button>
      </div>

    </form>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/estructuraWEB/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/style.jspf" %>
</body>
</html>
