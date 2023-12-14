<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>List Bills</title>

            
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }

        h1 {
            color: #007BFF;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            bbill-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        table {
            width: 100%;
            bbill-collapse: collapse;
        }

        th, td {
            padding: 5px;
            text-align: center;
        }

        th {
            background-color: #007BFF;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a {
            text-decoration: none;
            color: #007BFF;
        }
    </style>
</head>
<body>
Hello! this is bill
<div align = "center">
	
	<center>
		 <a href="david.jsp"target ="_self" > Go to David's Home page</a><br><br> 
		 
		 </center>


    <div align="center">
  <form action="billUpdate" method="post" id="updateForm">
        <table bbill="1" cellpadding="6" id="attable">
            <caption><h1>List of bills</h1></caption>
            <tr>
            	<th>Bill_id</th>
                <th>Order_id</th>
                <th>Client_id</th>
                <th>Status</th>
                
                <th>Amount</th>
                <th>Note</th>
                <th>Duedate</th>

            </tr>
            
             <c:forEach items="${listBills}" var="bills" >
             
            <tr style="text-align:center">
           
           
                
                    <td><c:out value="${bills.bill_id}" /><input type="hidden" name="bill_id" value="${bills.bill_id}" /></td>
                    <td><c:out value="${bills.order_id}" /></td>
                    <td><c:out value="${bills.client_id}" /><input type="hidden" name="client_id" value="${bills.client_id}" /></td>
                    <td><c:out value="${bills.status}" /><input type="hidden" name="status" value="${bills.status}" /></td>
                    <td><input type="text" name="amount" value="${bills.amount}" onfocus="this.value=''"></td>
                    <td><input type="text" name="note" value="${bills.note}" onfocus="this.value=''"></td>
                    <td><input type="text" name="duedate" value="${bills.duedate}" onfocus="this.value=''"></td>
                    
            </tr>
            </c:forEach>
        </table>
        <button type="submit">Update</button>
	
	</div>
	</div>
 
</body>
</html>