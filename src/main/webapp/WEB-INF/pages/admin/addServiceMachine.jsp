<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 1/7/2019
  Time: 12:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/1/2018
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Добавление Машины</title>
</head>
<body>
<div style="text-align: center">
    <H2 style="text-align: center"> Добавление Машины </H2>
    <a href="/admin/serviceMachines/allServiceMachines"> Отменить создание новой машины </a>

    <form:form action="/admin/workers/workerAdded" method="post" >
        Имя фамиля: <br>
        <input type="text" name="name" required="required">
        <br><br>
        <input type="submit" value="Сохранить">

    </form:form>
</div>
</body>
</html>
