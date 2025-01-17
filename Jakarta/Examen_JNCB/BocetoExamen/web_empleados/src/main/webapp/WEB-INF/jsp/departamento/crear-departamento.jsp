<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="org.iesbelen.model.Departamento"%>
<%@page import="java.util.Optional"%>

<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Crear Departamento</title>
  <style>
    .clearfix::after {
      content: "";
      display: block;
      clear: both;
    }

  </style>
</head>
<body>

<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
  <form action="${pageContext.request.contextPath}/empresa/departamento/crear/" method="post">
    <div class="clearfix">
      <div style="float: left; width: 50%">
        <h1>Crear Departamento</h1>
      </div>
      <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

        <div style="position: absolute; left: 39%; top : 39%;">
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
        Presupuesto
      </div>
      <div style="float: none;width: auto;overflow: hidden;">
        <input name="presupuesto" />
      </div>
      <div style="float: left;width: 50%">
        Gastos
      </div>
      <div style="float: none;width: auto;overflow: hidden;">
        <input name="gastos" />
      </div>

    </div>

  </form>
</div>

</body>
</html>

<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>
<%@ include file ="/WEB-INF/jsp/fragmentos/style.jspf"%>