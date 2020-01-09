<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/22/2018
  Time: 10:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Работники</title>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
</head>
<div class="topnav">
    <a href="/"> Главное меню</a>
    <a  href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a class="active" href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
</div>
<body>
<div class="mainContent">
<a href="/admin/addWorker"> Добавить работника</a>
<br>
<br>

<table class="mainTables">
    <tr>
        <th>N</th>
        <th>Имя раборника</th>
        <th>Удаление</th>
    </tr>
<% int i = 1; %>

    <c:forEach items="${allWorkers}" var="worker">
          <tr>
              <td> <%= i++%> </td>
            <td>${worker.name}</td>
            <td>
                <form:form action="/admin/workers/workerDeleted" method="post" >
                    <input type="hidden" name="id" value="${worker.id}">
                    <input type="submit"  value="Удалить"
                           onclick="return confirm('!!!Вы уверены что хатите удалить Работника!!!');"
                           style="color: #dc161c;"
                           style="align-items: center"
                                    </form:form>
            </td>

        </tr>
    </c:forEach>

</table>

</div>
</body>
</html>
