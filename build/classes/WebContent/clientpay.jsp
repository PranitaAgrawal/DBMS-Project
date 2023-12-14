<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Request</title>
<style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f4;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin: 0 auto;
        }

        th, td {
            padding: 25px;
            text-align: left;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            width: 100%;
            padding:5px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        a {
            text-decoration: none;
            color: #007BFF;
        }

        /* Add more CSS styles as needed */
    </style>
    </head>
<body>
	<div align="center">
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="payment">
			<table border="1" cellpadding="5">
				<tr>
					<th>Client_id: </th>
					<td align="center" colspan="3">
						<input type="text" name="client_id" size="45" value="${client_id}" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Bill_id: </th>
					<td align="center" colspan="3">
						<input type="text" name="bill_id" size="45" value="${bill_id}" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Amount:</th>
					<td align="center" colspan="3">
						<input type="text" name="amount" size="45" value="${amount}" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Credit Card Number</th>
					<td align="center" colspan="3">
						<input type="text" name="creditcard" size="45" value="creditcard" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Expiry Date</th>
					<td align="center" colspan="3">
						<input type="text" name="expiry" size="45" value="expiry" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>CVV</th>
					<td align="center" colspan="3">
						<input type="password" name="cvv" size="45" value="cvv" onfocus="this.value=''">
					</td>
				
				</tr>
				
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Payment"/>
					</td>
				</tr>
				
			<a href="activitypage.jsp" target ="_self" > Go to your home page</a><br><br> 	
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>