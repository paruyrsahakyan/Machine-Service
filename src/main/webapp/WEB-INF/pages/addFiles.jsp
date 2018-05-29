<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/28/2018
  Time: 12:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/fileAdded/${historyRecordId}" method="post" enctype="multipart/form-data">
    описание файла: <input type="text" name="filename[]"> <input type="file" name="file">
    <input type="submit" value="Сохранить">
</form>

</body>
</html>
