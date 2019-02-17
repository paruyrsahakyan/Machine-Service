<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/15/2019
  Time: 12:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title> Склад</title>
</head>
<body>
<div style="text-align: center">
<h2>Склад</h2>

<a href="/"> Главное меню</a>
 <br> <br>
<a href="/wareHouse/interChangeableParts" > Взаимозаменяемые запчасти </a>
<br> <br>
<a href="/wareHouse/interChangeableParts/createNew"> Создать заменяемую зачасть </a>
<br> <br>
<p style="color: #dc161c"> дата обновления склада ${updateDate}
    <form:form action="/wareHouse/updated" method="post"  accept-charset="UTF-8"
               enctype="multipart/form-data">
    Загрузить склад:
    <input type="file" name="wareHouseFile">
    <input  type="submit" value="загрузить">
    </form:form>
</p>
</div>
</body>
</html>
