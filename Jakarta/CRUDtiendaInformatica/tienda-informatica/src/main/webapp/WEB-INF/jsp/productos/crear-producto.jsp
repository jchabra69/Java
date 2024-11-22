<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Fabricante" %>
<%--
  Created by IntelliJ IDEA.
  User: Jose Nicolas
  Date: 21/11/2024
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>


<html>
<head>
    <title>Crear Producto</title>
</head>
<body>

<!--Debe crear un producto, para eso pediremos nombre, precio y fabricante. El id del producto ya se crea solo-->

<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;">
    <form action="${pageContext.request.contextPath}/tienda/productos/crear/" method="post">
        <div class="clearfix">
            <div style="float: left; width: 50%">
                <h1>Crear Producto</h1>
            </div>
            <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

                <div style="position: absolute; left: 39%; top: 39%;">
                    <input type="submit" value="Crear"/>
                </div>

            </div>
        </div>

        <div class="clearfix">
            <hr/>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                Nombre
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="nombre" />
            </div>

            <div style="float: left;width: 50%">
                Precio
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="precio" />
            </div>

            <div style="float: left;width: 50%">
                Fabricante
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <select name="selectFabricante">
                    <%
                        if (request.getAttribute("listaFabricantes") != null) {
                            List<Fabricante> listaFabricantes = (List<Fabricante>) request.getAttribute("listaFabricantes");

                            for (Fabricante fabricante : listaFabricantes) {
                    %>
                    <option value="<%= fabricante.getIdFabricante() %>"><%= fabricante.getNombre() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
        </div>

    </form>
</div>

</body>
</html>

<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>
<%@ include file ="/WEB-INF/jsp/fragmentos/style.jspf"%>