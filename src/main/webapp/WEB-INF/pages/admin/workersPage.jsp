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
        <style>
        table, th, td {
            border: 1px solid black;

                 }
    </style>
</head>
<body>
<div style="text-align: center">
<h2 style="text-align: center">Работники</h2>
<a href="/"> В главное меню</a> &nbsp;
<a href="/admin/addWorker"> Добавить работника</a>
<br>
<br>

<table>
    <tr>
        <th>N</th>
        <th>Имя раборника</th>
        <th>Удаления</th>
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
                           onclick="return confirm('!!!Вы уверены что хатите удалить Работнка!!!');"
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
