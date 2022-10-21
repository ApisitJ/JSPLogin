<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.login.database.LoginDatabase"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
		<%
		LoginDatabase logindatabase = new LoginDatabase();
		Cookie[]cookie = request.getCookies();
		String status = (String)request.getSession().getAttribute("status_User");
		try{
			if(!logindatabase.validateCookie(cookie) || status == null){
				throw new Exception("Exception message");
			}
			if(!status.equals("admin")){
				String redirect = "loginSuccess.jsp";
				response.sendRedirect(redirect);
			}
		} catch(Exception e){
			String redirect = "logout.jsp";
			response.sendRedirect(redirect);
		}

		%>
	<div align="center">
		<h1>Register </h1>
		<form action="Register" method="post">
						<h3 style="color: red">${message}</h3>
						
			<table>

				<tr>
					<td>UserName:</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"></td>
				</tr>				
				<tr>
					<td>E-mail:</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>User Status:</td>
					<td><select name="status">
							<option value="user">User</option>
							<option value="admin">Admin</option>
							<option value="super visor">Super Visor</option>
							
					</select></td>
				</tr>				
				<tr>
					<td></td>
					<td><input type="submit" value="register"></td>
					<%--<td><input type="button" value="Back" onclick="javascript:history.go(-1)"></td> --%>
					<td><input type="button" value="Back" onclick="history.back()"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>