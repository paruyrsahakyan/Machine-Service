<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/16/2019
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Создание Заменаемой запчасти</title>
</head>
<body>
<div style="text-align: center">
<h3>Создание Заменаемой запчасти </h3>
<a href="/workOrder/home"> Отмена </a>
<br> <br>
 <form:form action="/workOrder/createdNewInterChangeable" method="post">
     Основной артикул <input type="text" name="basicPartNumber">  &nbsp; | &nbsp;
     Артикул замены <input type="text"   name="partNumber" >
     <input type="submit" value="сохранить">
 </form:form>
    </div>
</body>
</html>
