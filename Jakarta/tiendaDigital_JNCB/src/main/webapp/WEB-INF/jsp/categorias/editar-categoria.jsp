<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.iesbelen.model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/header.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/nav.jspf" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Editar Categoría</title>
  <%@ include file="/WEB-INF/jsp/categorias/editar-categoria-style.jspf" %>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body>
<main>
  <section>
    <div id="contenedora" style="float:none; margin: 0 auto;width: 900px;">
      <h1>Editar Categoría</h1>

      <form action="${pageContext.request.contextPath}/tienda/categorias" method="POST">
        <input type="hidden" name="__method__" value="put"/>
        <input type="hidden" name="idCategoria" value="${categoria.idCategoria}"/>

        <div class="form-group">
          <label for="nombre">Nombre</label>
          <input type="text" id="nombre" name="nombre" value="${categoria.nombre}" required class="form-control"/>
        </div>

        <div class="form-group">
          <label for="descripcion">Descripción</label>
          <textarea id="descripcion" name="descripcion" required class="form-control">${categoria.descripcion}</textarea>
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
