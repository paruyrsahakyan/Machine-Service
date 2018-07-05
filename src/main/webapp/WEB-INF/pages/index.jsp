<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>IKO SERVICE</title>
    <style type="text/css">
        .center {
            margin: auto;
            width: 50%;
            border: 3px solid green;
            padding: 10px;
        }
    </style>
</head>
<body>
<h1 style="text-align: center"> IKO SERVICE </h1>
<a href="/login"> Авторизация пользователя </a>
<br>
<br>
<br>
<a href="/allCustomers" target="_blank" > Kомпании</a>
<br>
<br>
<a href="/allMachines" target="_blank"> Mашины</a>
<br>
<br>
<a href="/analysis/worker/jobs" target="_blank"> Анализ работ</a>
<br>
<br>
<a href="/admin/workers"> Работники </a>


</body>
</html>