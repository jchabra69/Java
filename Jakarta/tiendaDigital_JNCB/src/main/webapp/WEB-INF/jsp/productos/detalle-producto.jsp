<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="java.util.Optional" %>

<%@ include file="/WEB-INF/jsp/estructuraWEB/header.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/nav.jspf" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Detalle Producto</title>
  <%@ include file="/WEB-INF/jsp/productos/styleDetalleProducto.jspf" %> <!-- Incluir el estilo -->
</head>
<body>

<!-- Contenedor principal con espacio entre el nav y el contenedor -->
<div id="contenedora" style="float:none; margin: 40px auto; width: 900px;">
  <div class="clearfix">
    <div style="float: left; width: 50%">
      <h1>Detalle Producto</h1>
    </div>
    <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
      <div style="position: absolute; left: 39%; top: 39%;">
        <form action="${pageContext.request.contextPath}/tienda/productos">
          <input type="submit" value="Volver" />
        </form>
      </div>
    </div>
  </div>

  <div class="clearfix">
    <hr/>
  </div>

  <%
    Optional<Producto> optProd = (Optional<Producto>)request.getAttribute("producto");
    if (optProd.isPresent()) {
      Producto producto = optProd.get();
  %>

  <div style="margin-top: 6px;" class="clearfix">
    <div style="float: left;width: 50%">
      <label>Código</label>
    </div>
    <div style="float: none;width: auto;overflow: hidden;">
      <input value="<%= producto.getIdProducto() %>" readonly="readonly"/>
    </div>
  </div>
  <div style="margin-top: 6px;" class="clearfix">
    <div style="float: left;width: 50%">
      <label>Nombre</label>
    </div>
    <div style="float: none;width: auto;overflow: hidden;">
      <input value="<%= producto.getNombre() %>" readonly="readonly"/>
    </div>
  </div>
  <div style="margin-top: 6px;" class="clearfix">
    <div style="float: left;width: 50%">
      <label>Descripción</label>
    </div>
    <div style="float: none;width: auto;overflow: hidden;">
      <textarea readonly="readonly"><%= producto.getDescripcion() %></textarea>
    </div>
  </div>
  <div style="margin-top: 6px;" class="clearfix">
    <div style="float: left;width: 50%">
      <label>Precio</label>
    </div>
    <div style="float: none;width: auto;overflow: hidden;">
      <input value="<%= producto.getPrecio() %>" readonly="readonly"/>
    </div>
  </div>
  <div style="margin-top: 6px;" class="clearfix">
    <div style="float: left;width: 50%">
      <label>Imagen</label>
    </div>
    <div style="float: none;width: auto;overflow: hidden;">
      <input value="<%= producto.getImagen() %>" readonly="readonly"/>
    </div>
  </div>
  <div style="margin-top: 6px;" class="clearfix">
    <div style="float: left;width: 50%">
      <label>Categoría</label>
    </div>
    <div style="float: none;width: auto;overflow: hidden;">
      <input value="<%= producto.getCategoriaID() %>" readonly="readonly"/>
    </div>
  </div>

  <% } else { %>

  <script>
    window.location.href = "${pageContext.request.contextPath}/tienda/productos";
  </script>

  <% } %>
</div>

</body>
</html>

<%@ include file="/WEB-INF/jsp/estructuraWEB/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/style.jspf" %>
