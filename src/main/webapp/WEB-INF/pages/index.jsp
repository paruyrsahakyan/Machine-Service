<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>IKO SERVICE</title>
    <style><%@include file="/WEB-INF/pages/CSS/mainMenu.css"%></style>
</head>
<body>
<div class="topnav">
    <a class="active" href="/"> Главное меню</a>
    <a href="/allCustomers"> Компании </a>
        <a href="/allMachines"> Mашины </a>
        <a href="/analysis/worker/jobs"> Анализ работ</a>
        <a href="/workOrder/home"> Наряды </a>
        <a href="/admin/workers"> Работники </a>
       <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
      <a href="/machineType/allMachineTypes"> Типы Машин</a>
        <a href="/wareHouse"> Склад </a>
</div>
<div class="image">
    <img src='<c:url value="/images/komatsu.jpg"> </c:url>' height="100%" width="100%"/>
</div>

</body>
</html>
