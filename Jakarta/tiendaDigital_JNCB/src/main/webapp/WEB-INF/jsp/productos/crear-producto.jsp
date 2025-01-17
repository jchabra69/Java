<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Categoria" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/jsp/estructuraWEB/header.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/nav.jspf" %>

<html>
<head>
  <%@ include file="/WEB-INF/jsp/productos/style.jspf" %>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body>

<div id="contenedora">
  <form action="${pageContext.request.contextPath}/tienda/productos/crear/" method="post">

    <h1 style="text-align: center;">Crear Producto</h1>

    <div class="clearfix">
      <div style="float: left;width: 100%">
        <label for="nombre">Nombre</label>
        <input id="nombre" name="nombre" type="text" placeholder="Ingrese el nombre del producto" required/>
      </div>

      <div style="float: left;width: 100%">
        <label for="descripcion">Descripción</label>
        <textarea id="descripcion" name="descripcion" placeholder="Ingrese una breve descripción del producto" required></textarea>
      </div>

      <div style="float: left;width: 100%">
        <label for="precio">Precio</label>
        <input id="precio" name="precio" type="number" step="0.01" placeholder="Ingrese el precio del producto" required/>
      </div>

      <div style="float: left;width: 100%">
        <label for="imagen">Imagen</label>
        <input id="imagen" name="imagen" type="text" placeholder="URL de la imagen" required/>
      </div>

      <div style="float: left;width: 100%">
        <label for="categoria">Categoría</label>
        <select name="categoria" id="categoria" required>
          <option value="">Seleccione una categoría</option>
          <%
            if (request.getAttribute("listaCategorias") != null) {
              List<Categoria> listaCategorias = (List<Categoria>) request.getAttribute("listaCategorias");
              for (Categoria categoria : listaCategorias) {
          %>
          <option value="<%= categoria.getIdCategoria() %>"><%= categoria.getNombre() %></option>
          <%
              }
            }
          %>
        </select>
      </div>
    </div>

    <!-- Botón Crear centrado al final -->
    <div style="text-align: center; margin-top: 20px;">
      <input type="submit" value="Crear"/>
    </div>

  </form>
</div>

</body>
</html>

<%@ include file="/WEB-INF/jsp/estructuraWEB/footer.jspf" %>
<%@ include file="/WEB-INF/jsp/estructuraWEB/style.jspf" %>
