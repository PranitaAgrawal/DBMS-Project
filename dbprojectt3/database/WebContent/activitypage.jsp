<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
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
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}
button {
    padding: 10px 20px;
    background-color: #007BFF;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin: 5px 0;
}

button:hover {
    background-color: #0056b3;
}

table {
    width: 50%;
    border-collapse: collapse;
}

th, td {
    text-align: left;
}

input[type="text"],
input[type="password"],
input[type="submit"] {
    width: 100%;
    padding: 8px;
    margin: 5px 0;
    border: 1px solid #ccc;
    border-radius: 4px;
}

input[type="submit"] {
    background-color: #007B

</style>
</head>

<center><h1>Welcome! You have been successfully logged in</h1> </center>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 
		 </center>
		 <center>
		 <a href="req.jsp" target="_self">Go to request page</a><br><br>
		 </center>
		 <center>
		 <form action="listClientReq" method="post">
		<button>List Your Requests</button>
	</form>
	<form action="listClientQuote" method="post">
		<button>List Your Quotes</button>
	</form>
	<form action="listClientOrder" method="post">
		<button>List Your Orders</button>
	</form>
	<form action="listClientBill" method="post">
		<button>List Your Bills</button>
	</form>
	</center>
	</body>
</html>