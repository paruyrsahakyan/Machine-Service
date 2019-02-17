<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/17/2019
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Взаимозаменяемая  группа </title>
    <style>
        table, th, td {
            border: 1px solid black;
            align-self: center;
        }

    </style>
</head>
<body>
<div style="text-align: center">
    <h3>Взаимозаменяемая  группа </h3>
    <a href="/">Главное Меню</a>
    <br> <br>
    <a href="/wareHouse"> Склад </a>
    <br> <br>

    <table style="width: auto" align="center">
        <tr>
            <th> ${basicPartNumber} </th>
            <th> <a href="/wareHouse/interChangeableGroup/${basicPartNumber}/deleteGroup"  style="color: darkred">
                Удалить группу </a>
            </tr>
           <c:forEach items="${interChangeablePartList}" var="interChangeablePart" >
        <tr>
               <td>
                ${interChangeablePart.partNumber}
                </td>
                <td>
                    <a href="/wareHouse/interChangeablePart/${interChangeablePart.id}/deleted">
                    Удалить </a>
                </td>
        </tr>
                        </c:forEach>
       </div>
</body>
</html>
