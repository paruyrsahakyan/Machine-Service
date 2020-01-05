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
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
</head>
<body>
<div class="topnav">
    <a href="/"> Главное меню</a>
    <a  href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a class="active" href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
</div>

<div class="mainContent">
    <h2>Создание Нового Типа Машины</h2> <br>

    <form:form action="/machineType/machineTypeCreated" method="post">

        <input type="text"  name="typeDescription"  placeholder="модель, серийные номера" style="height: auto">Тип Машины</input>
        <br><br>
          <input type="submit" value="Сохранить">
    </form:form>
</div>
</body>
</html>
