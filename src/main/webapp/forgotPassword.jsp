<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>
	<div align="center">
		<h1>Forgot Password</h1>
			<form action=SendMailForgot method="post">
						<h3 style="color: red">${message}
						</h3>
			<table>
				<tr>
					<td>email:</td>
					<td><input type="text" name="email"></td>
				</tr>

				<tr>
					<td></td>
					<td><input type="submit" value="Send"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>