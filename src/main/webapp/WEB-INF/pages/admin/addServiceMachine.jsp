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
    <title>Создание Сервисной Машины</title>
</head>
<body>
<div style="text-align: center">
    <H2 style="text-align: center"> Создание Сервисной Машины </H2>
    <a href="/admin/serviceMachines"> Отменить создание новой машины </a>
    <br>
    <br>
    <form:form action="/admin/serviceMachine/serviceMachineAdded" method="post" >
        Машина: <br>
        <input type="text" name="name" required="required" placeholder="Марка Гос.Ном.">
        <br><br>
        <input type="submit" value="Сохранить">

    </form:form>
</div>
</body>
</html>
