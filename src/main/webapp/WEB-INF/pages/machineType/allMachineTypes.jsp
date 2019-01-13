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
    <title>Типы Машин</title>
    <style>
        table, th, td {
            border: 1px solid black;

        }
    </style>
</head>
<body>
<div style="text-align: center">
    <h2 style="text-align: center">Типы Машин</h2>
    <a href="/"> В главное меню</a> &nbsp;
    <a href="/machineType/newTypeCreation"> Добавить Новый Тип</a>
    <br>
    <br>

    <table align="center">
        <tr>
            <th>N</th>
            <th>Тып Машины</th>
             </tr>
        <% int i = 1; %>

        <c:forEach items="${allMachineTypes}" var="machineType">
            <tr>
                <td> <%= i++%> </td>
                <td> <a href="/admin/machineType/${machineType.id}"> ${machineType.description} </a> </td>

            </tr>
        </c:forEach>

    </table>

</div>
</body>
</html>

