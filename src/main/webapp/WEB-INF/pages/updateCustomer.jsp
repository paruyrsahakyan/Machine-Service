<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/20/2018
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Редактировать</title>
    <meta>
</head>
<body>
<h3>Редактирование информации заказчика</h3>
<form:form action="/customer/updatedCustomer/${customer.id}" method="post" accept-charset="UTF-8">
    Название компании:<br>
    <input type="text" name="name" style="height: auto" value="${customer.name}"><br><br>
    Контактные данные:<br>
    <textarea name="contacts" cols="40" rows="5"> ${customer.contacts}</textarea> <br><br>
    Другая информация: <br>
    <textarea name="otherInfo" cols="40" rows="5">${customer.otherInfo}</textarea> <br><br>

    <input type="submit" value="Сохранить">
</form:form>

</body>
</html>
