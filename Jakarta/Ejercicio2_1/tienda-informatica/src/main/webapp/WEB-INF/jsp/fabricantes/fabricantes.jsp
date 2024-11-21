<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page import="org.iesbelen.model.Fabricante" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.dto.FabricanteDTO" %>
<%@ page import="java.util.ArrayList" %>

<%
	List<FabricanteDTO> misFabricantillos = new ArrayList<>();

	if (request.getAttribute("listaFabricantesDTO") != null) {
		List<?> rawList = (List<?>) request.getAttribute("listaFabricantesDTO");
		for (Object obj : rawList) {
			if (obj instanceof FabricanteDTO) {
				misFabricantillos.add((FabricanteDTO) obj);
			} else if (obj instanceof Fabricante) {
				Fabricante fabricante = (Fabricante) obj;
				misFabricantillos.add(new FabricanteDTO(fabricante));
			}
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fabricantes</title>
	<style>
		.clearfix::after {
			content: "";
			display: block;
			clear: both;
		}
	</style>
</head>
<body>

<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;">
	<div class="clearfix">
		<div style="float: left; width: 50%">
			<h1>Fabricantes</h1>
		</div>
		<div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
			<div style="position: absolute; left: 39%; top : 39%;">
				<form action="${pageContext.request.contextPath}/tienda/fabricantes/crear">
					<input type="submit" value="Crear">
				</form>
			</div>
		</div>


		<!--Ordenar por nombre/codigo de manera ascendente o descendente
		-->
		<div>

			<form action="${pageContext.request.contextPath}/tienda/fabricantes/">

				<select name="ordenado-por">
					<option value = "nombre">Nombre</option>
					<option value = "codigo">Código</option>
				</select>

				<select name="modo">
					<option value = "asc">Ascendente</option>
					<option value = "desc">Descendente</option>
				</select>

				<input type="submit" value="ordenar">

			</form>

		</div>

	</div>
	<div class="clearfix">
		<hr/>
	</div>
	<div class="clearfix">
		<div style="float: left; width: 25%;">Código</div>
		<div style="float: left; width: 25%;">Nombre</div>
		<div style="float: left; width: 25%;">Nº productos</div>
		<div style="float: left; width: auto; overflow: hidden;">Acción</div>
	</div>

	<div class="clearfix">
		<hr/>
	</div>
	<%
		if (misFabricantillos != null && !misFabricantillos.isEmpty()) {
			for (FabricanteDTO fabricante : misFabricantillos) {
	%>
	<div style="margin-top: 6px;" class="clearfix">
		<div style="float: left;width: 25%"><%= fabricante.getIdFabricante() %></div>
		<div style="float: left;width: 25%"><%= fabricante.getNombre() %></div>
		<div style="float: left;width: 25%"><%= fabricante.getTotalProductos() %></div>
		<div style="float: none;width: auto;overflow: hidden;">
			<form action="${pageContext.request.contextPath}/tienda/fabricantes/<%= fabricante.getIdFabricante() %>" style="display: inline;">
				<input type="submit" value="Ver Detalle" />
			</form>
			<form action="${pageContext.request.contextPath}/tienda/fabricantes/editar/<%= fabricante.getIdFabricante() %>" style="display: inline;">
				<input type="submit" value="Editar" />
			</form>
			<form action="${pageContext.request.contextPath}/tienda/fabricantes/borrar/" method="post" style="display: inline;">
				<input type="hidden" name="__method__" value="delete" />
				<input type="hidden" name="codigo" value="<%= fabricante.getIdFabricante() %>" />
				<input type="submit" value="Eliminar" />
			</form>
		</div>
	</div>
	<%
		}
	} else {
	%>
	<p>No hay registros de fabricante</p>
	<%
		}
	%>
</div>
</body>
</html>
