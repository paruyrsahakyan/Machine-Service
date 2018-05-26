<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/19/2018
  Time: 9:18 PM
  To change this template use File | Settings | File Templates.
--%>
<script type="text/javascript">

    function stopRKey(evt) {
        var evt = (evt) ? evt : ((event) ? event : null);
        var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
        if ((evt.keyCode == 13) && (node.type=="text"))  {return false;}
    }

    document.onkeypress = stopRKey;

</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание новой компании</title>
</head>
<body>
<h2>Создание новой компании</h2> <br>
<form action="/newCustomer">
    Название компании:<br>
    <input type="text" name="name" style="height: auto"><br><br>
    Контактные данные:<br>
    <textarea name="contacts" cols="40" rows="5"></textarea> <br><br>
    Другая информация: <br>
    <textarea name="otherInfo" cols="40" rows="5"></textarea> <br><br>

    <input type="submit" value="Сохранить">
</form>

</body>
</html>
