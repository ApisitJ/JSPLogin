<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verify Authen Code</title>
</head>
<body>

<div align="center">
		<span>Please Verify code from your Email</span>		
		<form action="VerifyForgot" method="post">
			<h3 style="color: red">${message}</h3>
				<input type= "text" name="authCode">				
				<input type= "submit" value="verify">
		</form>
</div>
</body>
</html>