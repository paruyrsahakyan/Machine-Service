<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/28/2018
  Time: 12:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page contentType="text/html; charset=Windows-1251" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<div style="text-align: center">
<a href="/"> Главное меню</a>
<br>
<br>
<a href="/customer/machine/historyRecord/${historyRecord.id}"> Вернутся в страницу записи </a>
<H3>${historyRecord.machine} &nbsp; | &nbsp;${historyRecord.title} &nbsp; |&nbsp; ${historyRecord.recordDate} </H3>
<form:form action="/customer/machine/historyRecord/files/updatedList/${historyRecordId}"
           method="post"  accept-charset="UTF-8" enctype="multipart/form-data">
    описание файла: <input type="text" name="fileDescription1" maxlength="24" > <input type="file" name="file1">
    <br> <br>
    описание файла: <input type="text" name="fileDescription2" maxlength="24" > <input type="file" name="file2">
    <br> <br>
    описание файла: <input type="text" name="fileDescription3" maxlength="24" > <input type="file" name="file3">
    <br> <br>
    описание файла: <input type="text" name="fileDescription4" maxlength="24" > <input type="file" name="file4">
    <br>

<h4>Прикрепленные файлы:</h4>

                 <table align="center">
                 <tr>
                     <th>Описание</th>
                     <th>Название файла</th>
                     <th>Отметить для удаления</th>
                 </tr>

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
        <br>
        <input type="submit" value="Обновить">

    </form:form>
</div>
</body>
</html>
