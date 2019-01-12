<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 1/9/2019
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Новый Тип Машины</title>
</head>
<body>
<div style="text-align: center">
    <h2>Создание Нового Типа Машины</h2> <br>

    <form:form action="/admin/machineType/machineTypeCreated">

        <input type="typeDescription"  name="model"  placeholder="модель, серийные номера" style="height: auto">Тип Машины</input>
        <br><br>
          <input type="submit" value="Сохранить">
    </form:form>
</div>
</body>
</html>
