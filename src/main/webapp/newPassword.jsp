<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.login.database.LoginDatabase"%>	
<%@page import="com.login.verify.CheckStatusUser" %>
<%@page import="com.login.bean.Format" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>
		<%		
		String checkConfirm = (String)request.getSession().getAttribute(Format.CONFIRMFORGOT);
		if(checkConfirm.isEmpty()){
			String redirect = Format.LOGOUT;
			request.setAttribute("message", "ERROR Don't have this Email Please Try Again or check your True Email");
			RequestDispatcher rd = request.getRequestDispatcher(Format.FORGOTPASSWORD);
			rd.include(request, response);
		}


		%>
	<div align="center">
		<h1>Change Password </h1>
		<form action="ChangePasswordForgot" method="post">
			<h3 style="color: red">${message}</h3>
			<table>
				<tr>
				<td>New Password:</td>
					<td><input type="password" name="newPassword"></td>
				</tr>				
				<tr>
				<td>Confirm Password:</td>
					<td><input type="password" name="confirmPassword"></td>
				</tr>					
				<tr>
					<td></td>
					<td><input type="submit" value="Submit"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>