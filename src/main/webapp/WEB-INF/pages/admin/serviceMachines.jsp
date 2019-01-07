<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 1/6/2019
  Time: 11:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Работники</title>
    <style>
        table, th, td {
            border: 1px solid black;

        }
    </style>
</head>
<body>
<div style="text-align: center">
    <h2 style="text-align: center">Сервисные Машины</h2>
    <a href="/"> В главное меню</a> &nbsp;
    <a href="/admin/addServiceMachine"> Добавить Машину</a>
    <br>
    <br>

    <table align="center">
        <tr>
            <th>N</th>
            <th>Машина</th>
            <th>Удаление</th>
        </tr>
        <% int i = 1; %>

        <c:forEach items="${allServiceMachines}" var="serviceMachine">
            <tr>
                <td> <%= i++%> </td>
                <td>${serviceMachine.name}</td>
                <td>
                    <form:form action="/admin/serviceMachineAdded/workerDeleted" method="post" >
                        <input type="hidden" name="id" value="${serviceMachine.id}">
                        <input type="submit"  value="Удалить"
                               onclick="return confirm('!!!Вы уверены что хатите удалить машину!!!');"
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

