<%--
  Created by IntelliJ IDEA.
  User: Jose Nicolas
  Date: 22/11/2024
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<html>
<head>
    <title>Usuarios</title>
    <style>
        .clearfix::after {
            content: "";
            display: block;
            clear: both;
        }
    </style>
</head>
<body>

<main>

    <section>

        <div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
            <div class="clearfix">
                <div style="float: left; width: 50%">
                    <h1>Usuarios</h1>
                </div>
                <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

        <div class="clearfix">
            <hr/>
        </div>
        <div class="clearfix">
            <div style="float: left;width: 10%">ID</div>
            <div style="float: left;width: 30%">Usuario</div>
            <div style="float: left;width: 20%">Password</div>
            <div style="float: left;width: 20%;overflow: hidden;">Rol</div>
        </div>
        <div class="clearfix">
            <hr/>
        </div>

    </section>

</main>

</body>
</html>


<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>
<%@ include file ="/WEB-INF/jsp/fragmentos/style.jspf"%>