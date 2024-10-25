<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>


<form action = "MiServlet" method="post">

    <label>Nombre</label>
    <input type="text" name="nombre">
    <label>Apellidos</label>
    <input type="text" name="apellidos">

    <label>Edad</label>
    <select name = "edad">
        <!--El servidor me va a coger el name y el value-->
        <option value= "peque">0-9 años</option>
        <option value = "adolescente">9-18 años</option>
        <option value = "adulto">18-40 años</option>
        <option value = "adultoXL">40-67 años</option>
        <option value = "viejuno" >67-Infinity años</option>
    </select>

    <label>Peso</label>
    <input type="number" name="peso">


    <br>
    <label>Sexo</label>
    <input name = "sexo" type="radio" name="sexo" value = "hombre">
    <label>Hombresito</label>

    <input name = "sexo" type="radio" name="sexo" value = "mujer">
    <label>Mujersita</label>

    <div>
        <%--@declare id="estadocivil"--%><label for="estadoCivil">Estado Civil: </label>
        <input type="radio" name="estadoCivil" value="soltero" id="soltero">
        <label for="soltero">Soltero</label>
        <input type="radio" name="estadoCivil" value="casado" id="casado">
        <label for="casado">Casado</label>
        <input type="radio" name="estadoCivil" value="divorciado" id="divorciado">
        <label for="divorciado">Otro</label>
    </div>

    <div>
        <%--@declare id="aficiones"--%><label for="aficiones">Aficiones: </label>
        <input type="checkbox" name="aficiones" id="cine" value="cine">
        <label for="cine">Cine</label>
        <input type="checkbox" name="aficiones" id="literatura" value="literatura">
        <label for="literatura">Literatura</label>
        <input type="checkbox" name="aficiones" id="tebeos" value="tebeos">
        <label for="tebeos">Tebeos</label>
        <input type="checkbox" name="aficiones" id="deportes" value="deportes">
        <label for="deportes">Deportes</label>
        <input type="checkbox" name="aficiones" id="musica" value="musica">
        <label for="musica">Música</label>
        <input type="checkbox" name="aficiones" id="television" value="television">
        <label for="television">Televisión</label>
    </div>

    <input type="submit" value="Enviar">
    <input type="button" value="Borrar">


</form>

</body>
</html>