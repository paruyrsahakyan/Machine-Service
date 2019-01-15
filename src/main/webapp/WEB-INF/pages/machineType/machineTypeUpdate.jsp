<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 1/15/2019
  Time: 10:56 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Редактирование Типа Машины</title>
</head>
<body>
<div style="text-align: center">
    <h2>Редактирование Типа Машины</h2> <br>

    <form:form action="/machineType/${machineType.id}/updated" method="post">

        <input type="text"  name="typeDescription"  value="${machineType.typeDescription}"> </input>
        <br><br>
        <input type="submit" value="Сохранить">
    </form:form>
</div>
</body>
</html>
