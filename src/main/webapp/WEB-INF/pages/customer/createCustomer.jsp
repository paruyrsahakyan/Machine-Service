<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/19/2018
  Time: 9:18 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Создание новой компании</title>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
</head>
<body>
<div class="topnav">
    <a href="/"> Главное меню</a>
    <a class="active" href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
</div>
<div class="mainContent">
<h2>Создание новой компании</h2> <br>
<form:form action="/customer/newCustomer"  method="post" accept-charset="UTF-8"  >
    Название компании:<br>
    <input type="text" name="name" style="height: auto"><br><br>
    Контактные данные:<br>
    <textarea name="contacts" cols="40" rows="5"></textarea> <br><br>
    Другая информация: <br>
    <textarea name="otherInfo" cols="40" rows="5"></textarea> <br><br>
    Наличие договора: <br>
        <select name="contract" >
        <option value="Не задано" selected > Не задано </option>
         <option value="Да"> Да </option>
         <option value="Нет"> Нет </option>
         </select>
         <br><br>
       <input type="submit" value="Сохранить">
</form:form>
</div>
</body>
</html>