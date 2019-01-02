
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Новая Машина</title>
    <style>

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: auto;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body onload="initList()" >
<script>
    function refreshList() {
        alert("You pressed a key inside the input field");
    }
    var initialCustomerList =[];
    var refreshedList=[];

    function initList() {

       <% int i = 1; %>
        <c:forEach items="${customerList}" var="customer">
        <% i++; %>
            initialCustomerList.push(${customer});
       </c:forEach>
              }
       function  testToDisplayInitList(refreshedList){
       document.getElementById("selected").innerHTML=refreshedList[1];
       }
       testToDisplayInitList(initialCustomerList);
</script>

<div style="text-align: center">
    <h2>Замена Владельца Машины</h2> <br>
    <p id="selected"> </p>
<form:form action="/customer/machine/${machineId}/updatedCustoemr">
    <input type="text" onchange="refreshList()" >
</form:form>
</div>
</body>
</html>

