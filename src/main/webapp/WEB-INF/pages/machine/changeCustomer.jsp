
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
    <textarea id="search" rows="1" cols="15" onkeyup="refreshTheTable()">
     </textarea>
    <form:form action="/customer/machine/${machineId}/updatedCustoemr">
                <input type="button" >
    </form:form>

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
    function filterTheList(){
        filteredList=[];
        var searchInput=document.getElementById("search").value;
        for(var i=0; i<initialCustomerList.length; i++){
            if(initialCustomerList[i].indexOf(searchInput)>0){
                filteredList.push(initialCustomerList[i])
                            }
        }
    }

        function tableCreate(filteredList){
            var body = document.body,
                tbl  = document.createElement('table');
             tbl.style.width  = '100px';
            tbl.style.border = '1px solid black';


            for(var i = 0; i <filteredList.length ; i++){
                var tr = tbl.insertRow();
                for(var j = 0; j < 1; j++){
                    if(i == 2 && j == 1){

                        break;
                    } else {
                        var td = tr.insertCell();
                        td.appendChild(document.createTextNode(filteredList[i]));
                        td.style.border = '1px solid black';
                        if(i == 1 && j == 1){
                            td.setAttribute('rowSpan', '2');
                        }
                    }
                }
            }
            body.appendChild(tbl);
         }

</script>

</body>
</html>

