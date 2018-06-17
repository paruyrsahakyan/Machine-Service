<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/19/2018
  Time: 9:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Новая Машина</title>
</head>
<body>
<h2>Регистрация новой машины</h2> <br>
<h3>Влоделец: &nbsp; ${customerName}</h3>
<form:form action="/customer/machine/newMachine/${customerId}" method="Post" >

    Модель Машины:<br>
    <input type="text" name="model" style="height: auto"><br><br>
    Серийный номер:<br>
    <input type="text" name="serialNumber" style="height: auto"><br><br>
    Модель ДВС: <br>
    <input type="text" name="engineModel" style="height: auto"><br><br>
    Сер. номер ДВС: <br>
    <input type="text" name="engineSerialNumber" style="height: auto"><br><br>
    Год производства: <br>
    <input type="number" name="productionYear" style="height: auto"><br><br>
    Другая информация: <br>
    <textarea name="otherInfo" cols="40" rows="5"></textarea> <br><br>


    <input type="submit" value="Сохранить">
</form:form>

</body>
</html>
