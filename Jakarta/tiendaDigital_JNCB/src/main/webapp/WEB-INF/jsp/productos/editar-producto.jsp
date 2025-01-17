<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="org.iesbelen.model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/header.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/nav.jspf" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Editar Producto</title>
  <%@ include file="/WEB-INF/jsp/productos/styleProductosEditar.jspf" %>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body>
<main>
  <section>
    <div id="contenedora" style="float:none; margin: 0 auto;width: 900px;">
      <h1>Editar Producto</h1>

      <form action="${pageContext.request.contextPath}/tienda/productos" method="POST">
        <input type="hidden" name="__method__" value="put"/>
        <input type="hidden" name="idProducto" value="${producto.idProducto}"/>

        <div class="form-group">
          <label for="nombre">Nombre</label>
          <input type="text" id="nombre" name="nombre" value="${producto.nombre}" required class="form-control"/>
        </div>

        <div class="form-group">
          <label for="descripcion">Descripción</label>
          <textarea id="descripcion" name="descripcion" required class="form-control">${producto.descripcion}</textarea>
        </div>

        <div class="form-group">
          <label for="precio">Precio</label>
          <input type="number" id="precio" name="precio" value="${producto.precio}" required class="form-control" step="0.01"/>
        </div>

        <div class="form-group">
          <label for="imagen">Imagen URL</label>
          <input type="text" id="imagen" name="imagen" value="${producto.imagen}" class="form-control"/>
        </div>

        <div class="form-group">
          <label for="categoria">Categoría</label>
          <select id="categoria" name="categoria" class="form-control" disabled>
            <option value="${producto.categoriaID}"></option>
          </select>
          <small class="form-text text-muted">No puedes cambiar la categoría de este producto.</small>
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
