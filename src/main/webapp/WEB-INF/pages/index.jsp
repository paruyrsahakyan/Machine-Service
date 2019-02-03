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
<div style="text-align: center">
    <h1 style="text-align: center"> IKO SERVICE </h1>
    <a href="/login"> Авторизация пользователя </a>
    <br>
    <br>
    <br>
    <a href="/allCustomers"> Kомпании</a>
    <br>
    <br>
    <a href="/allMachines"> Mашины</a>
    <br>
    <br>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <br>
    <br>
    <a href="/workOrder/new"> Наряды </a>
    <br>
    <br>
    <a href="/admin/workers"> Работники </a>
    <br>
    <br>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <br>
    <br>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <br>
</div>

</body>
</html>