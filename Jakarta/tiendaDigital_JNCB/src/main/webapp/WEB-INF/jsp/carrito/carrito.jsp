<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Producto" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/header.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/nav.jspf" %>

<html>
<head>
  <title>Carrito de Compras</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
  <%@ include file="/WEB-INF/jsp/carrito/estiloCarrito.jspf" %>
</head>
<body>

<div class="container mt-5">
  <h2>Carrito de Compras</h2>

  <%
    List<Producto> carrito = (List<Producto>) request.getAttribute("carrito");
    double total = (double) request.getAttribute("total");

    if (carrito != null && !carrito.isEmpty()) {
  %>
  <table class="table">
    <thead>
    <tr>
      <th>Producto</th>
      <th>Precio</th>
    </tr>
    </thead>
    <tbody>
    <% for (Producto producto : carrito) { %>
    <tr>
      <td><%= producto.getNombre() %></td>
      <td><%= producto.getPrecio() %>€</td>
    </tr>
    <% } %>
    </tbody>
  </table>

  <div class="d-flex justify-content-between">
    <h3>Total: <%= total %>€</h3>
    <form action="<%= request.getContextPath() + "/tienda/carrito/" %>" method="post">
      <button type="submit" class="btn btn-success" name="action" value="confirmar">Confirmar Compra</button>
    </form>
  </div>

  <% } else { %>
  <div class="alert alert-warning">Tu carrito está vacío.</div>
  <% } %>
</div>

<%@ include file="/WEB-INF/jsp/estructuraWEB/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/style.jspf" %>

</body>
</html>
