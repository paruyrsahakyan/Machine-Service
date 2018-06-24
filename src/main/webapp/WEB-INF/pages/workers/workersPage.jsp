<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/22/2018
  Time: 10:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


<table>
    <tr>
        <th>N</th>
        <th>Имя раборника</th>
        <th>Отметить для удаления</th>
    </tr>
<% int i = 1; %>

   <%= i++%>

    <c:forEach items="${fileList}" var="file">
        <tr>
            <td>
                    ${file.fileDescription}
            </td>
            <td>
                <a href="/customer/machine/historyRecord/downloadFile/${file.id}" target="_blank" >
                        ${file.fileName}
                </a>
            </td>
            <td><input type="checkbox" name="checkBox" value="${file.id}">
            </td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
