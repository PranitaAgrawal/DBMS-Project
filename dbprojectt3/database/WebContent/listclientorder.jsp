<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>List Requests</title>

            
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
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        table {
            width: 100%;
            border-collapse: collapse;
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
Hello! this is Order
<div align = "center">
<center>
		 <a href="activitypage.jsp" target ="_self" > Go to your home page</a><br><br> 
		 
		 </center>
	
	


    <div align="center">
  
        <table border="1" cellpadding="6" id="attable">
            <caption><h1>List of Orders</h1></caption>
            <tr>
                <th>Order_id</th>
                <th>Req_id</th>
                <th>Status</th>
                <th>Client_id</th>

            </tr>
            
             <c:forEach items="${listOrders}" var="orders" >
            <tr style="text-align:center">
           
           
                
                    <td><c:out value="${orders.order_id}" /></td>
                    <td><c:out value="${orders.req_id}" /></td>
                    <td><c:out value="${orders.status}" /></td>
                    <td><c:out value="${orders.client_id}" /></td>
                    
            </tr>
            </c:forEach>
        </table>
        
	
	</div>
	</div>
 
</body>
</html>