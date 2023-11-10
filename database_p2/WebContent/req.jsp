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
		<form action="reque">
			<table border="1" cellpadding="5">
				<tr>
					<th>Username: </th>
					<td align="center" colspan="3">
						<input type="text" name="email" size="45"  value="example@gmail.com" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Tree Size: </th>
					<td align="center" colspan="3">
						<input type="text" name="size" size="45" value="Size" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Tree Height: </th>
					<td align="center" colspan="3">
						<input type="text" name="height" size="45" value="Height" onfocus="this.value=''">
					</td>
				</tr>

				<tr>
					<th>Tree Location: </th>
					<td align="center" colspan="3"> 
						<input type="text" name="location" size="45" value="Location" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Distance from House: </th>
					<td align="center" colspan="3">
						<input type="text" name="distance" size="45" value="Distance" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Note</th>
					<td align="center" colspan="3">
						<input type="text" name="note" size="45" value="Note" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
                <th>File 1:</th>
                <td colspan="3">
                    <input type="file" id="fileInput1" name="fileInput1" style="display: none;" onchange="document.getElementById('fileLabel1').innerText = this.files[0].name" />
                    <label for="fileInput1" id="fileLabel1" style="cursor: pointer;">Choose File</label>
                </td>
            </tr>
            <tr>
                <th>File 2:</th>
                <td colspan="3">
                    <input type="file" id="fileInput2" name="fileInput2" style="display: none;" onchange="document.getElementById('fileLabel2').innerText = this.files[0].name" />
                    <label for="fileInput2" id="fileLabel2" style="cursor: pointer;">Choose File</label>
                </td>
            </tr>
            <tr>
                <th>File 3:</th>
                <td colspan="3">
                    <input type="file" id="fileInput3" name="fileInput3" style="display: none;" onchange="document.getElementById('fileLabel3').innerText = this.files[0].name" />
                    <label for="fileInput3" id="fileLabel3" style="cursor: pointer;">Choose File</label>
                </td>
            </tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Request"/>
					</td>
				</tr>
			</table>
			<a href="activitypage.jsp" target ="_self" > Go to your home page</a><br><br> 	
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>