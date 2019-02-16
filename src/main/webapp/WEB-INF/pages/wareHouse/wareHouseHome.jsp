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
<h2>Склад</h2>

<a href="/"> Главное меню</a>
<br>
<a href="/wareHouse/interChangeableParts" > Взаимозаменяемые запчасти </a>
<br>
<a href="/wareHouse/interchangeableParts/createNew"> Создать заменяемую зачасть </a>
<br>
<p> дата обновления склада ${updateDate}
    <form:form action="/wareHouse/saved" method="post"  accept-charset="UTF-8"
               enctype="multipart/form-data">
    Загрузить склад:
    <input type="file" name="wareHouseFile">
    <input  type="submit" value="загрузить">
    </form:form>
</p>
</body>
</html>
