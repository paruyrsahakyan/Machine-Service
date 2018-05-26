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
<div class="center">
    <form method="get">
        <label>
            search:
            <input type = "text" style="width: 300px;"/>
        </label>
        <input type="submit" value="search">
    </form>

    </label>
</div>

<p> <a href="/allCustomers"> все компании</a> </p>
<p> <a href="/allMachines"> все машины</a> </p>
<p> <a href="/allCustomers"> все компании</a> </p>


</body>
</html>