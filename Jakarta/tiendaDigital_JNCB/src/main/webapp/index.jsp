<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Producto" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/header.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/nav.jspf" %>

<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Amazona</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
  <style>
    #contenedor {
      display: flex;
      align-items: center;
      justify-content: center;
    }
  </style>
</head>
<body>

<%-- Contenedor de los productos --%>
<div class="container mt-5">

  <div class="row">
    <%
      // Obtener la lista de productos desde el Servlet
      List<Producto> productos = (List<Producto>) request.getAttribute("productosDisponibles");

      // Comprobar si la lista no está vacía
      if (productos != null && !productos.isEmpty()) {
        for (Producto producto : productos) {
    %>

    <div class="col-md-4 mb-4">
      <div class="card">
        <img src="<%= producto.getImagen() %>" class="card-img-top" alt="<%= producto.getNombre() %>">
        <div class="card-body">
          <h5 class="card-title"><%= producto.getNombre() %></h5>
          <p class="card-text">Precio: <%= producto.getPrecio() %>€</p>
          <a href="<%= request.getContextPath() + "/tienda/carrito/?action=add&productoId=" + producto.getIdProducto() %>" class="btn btn-primary">Añadir al carrito</a>
        </div>
      </div>
    </div>

    <%
      }
    } else {
    %>
    <div class="alert alert-warning text-center w-100">No hay productos disponibles.</div>
    <% } %>
  </div>
</div>

<%@ include file="bootstrap.jspf" %>

</body>
</html>

<%@ include file="/WEB-INF/jsp/estructuraWEB/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/style.jspf" %>
