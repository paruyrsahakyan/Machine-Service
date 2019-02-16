<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/16/2019
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Взаимозаменяемые детали</title>
    <style>
        table, th, td {
            border: 1px solid black;
            align-self: center;
        }

    </style>
</head>
<body>
<h3>Взаимозаменяемые детали</h3>
<a href="/">Главное Меню</a>
<a href="/wareHouse"> Склад </a>
<a href="/wareHouse/interChangeableParts/createNew"> Создать взаимозаменяемую запчасть </a>

<table style="width: auto" align="center">
       <tr>
        <td> N </td>
        <td> Основной артикул </td>
        <td> Замена1 </td>
        <td> Замена2 </td>
        <td> Замена3 </td>
        <td> Замена4 </td>
        <td> Замена5 </td>
        <td> Замена6 </td>
    </tr>
    <% int i = 1; %>
    <c:forEach items="${interChangeableGroupList}" var="interChangeableGroup" >
        <tr>
            <td><%= i++%> </td>
            <th>${interChangeableGroup.basicPartNumber}</th>
            <c:forEach items="${interChangeableGroup.interChangeablePartsList}" var="interChangeablePart">
            <td>${interChangeablePart}</td>
            </c:forEach>
        </tr>
    </c:forEach>

</body>
</html>
