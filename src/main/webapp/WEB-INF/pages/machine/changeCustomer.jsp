
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
<body >
<div style="text-align: center">
    <h2>Замена Владельца Машины</h2> <br>

    <p id="selected"> xxx </p>
    <textarea id="search" rows="1" cols="15" onkeyup="refreshTheTable()"></textarea>
    <form:form action="/customer/machine/${machineId}/updatedCustoemr">
                <input type="button" >
    </form:form>
    <table id="dynamicTable" style="width: auto" align="center">
        <tr>
            <td > N </td>
            <td>Название</td>
        </tr>
           </table>
    <br>
</div>


<script>
    var initialCustomerList =[];
    var filteredList=[];

    <c:forEach items="${customerList}" var="customer">
    initialCustomerList.push("${customer.name}");
    </c:forEach>

    tableCreate(initialCustomerList);
        function refreshTheTable() {
        filterTheList();

        tableCreate(filteredList);
    }

    function copyToTextArea(event){
            document.getElementById("search").innerText=event.target.innerText;

    }
    function filterTheList(){
        var searchInput=document.getElementById("search").value;
        if(searchInput===""){
            filteredList=initialCustomerList;
        }
        else  {
            filteredList=[];
            for(var i=0; i<initialCustomerList.length; i++){
                if(initialCustomerList[i].toLowerCase().indexOf(searchInput)>0){
                    filteredList.push(initialCustomerList[i])
                }
        }
        }
    }

        function tableCreate(filteredList) {

            var table = document.getElementById("dynamicTable");
            table.innerHTML = "";
            for (var i = 0; i < filteredList.length; i++) {
                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                cell1.innerHTML = (i + 1).toString();
                cell2.innerHTML = filteredList[i];
                cell2.addEventListener("click", copyToTextArea );
            }
        }
</script>

</body>
</html>

