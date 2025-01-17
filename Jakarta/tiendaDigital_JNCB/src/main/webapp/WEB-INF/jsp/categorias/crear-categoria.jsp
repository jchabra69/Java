<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.iesbelen.model.Categoria" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/header.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/nav.jspf" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Crear Categoría</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
  <%@ include file="/WEB-INF/jsp/categorias/crear-categoria-style.jspf" %>
  <style>
    .clearfix::after {
      content: "";
      display: block;
      clear: both;
    }

    main {
      margin-top: 20px;
    }

    .form-container {
      background-color: #ffffff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .form-group {
      margin-bottom: 20px;
    }

    .create-btn-container {
      text-align: center;
      margin-top: 20px;
    }

  </style>
</head>
<body>

<main class="container">
  <section>
    <div class="clearfix form-container">
      <h1>Crear Nueva Categoría</h1>

      <form action="${pageContext.request.contextPath}/tienda/categorias" method="post">
        <div class="form-group">
          <label for="nombre">Nombre</label>
          <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>

        <div class="form-group">
          <label for="descripcion">Descripción</label>
          <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
        </div>

        <div class="create-btn-container">
          <button type="submit" class="btn btn-success">Crear Categoría</button>
        </div>
      </form>
    </div>
  </section>
</main>

<%@ include file="/WEB-INF/jsp/estructuraWEB/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/style.jspf" %>
</body>
</html>
