package com.login.database;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.bean.Format;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoginDatabase loginDatabase = new LoginDatabase();
       
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute(Format.USERNAME);
		String oldPassword = request.getParameter(Format.OLDPASSWORD);
		String newPassword = request.getParameter(Format.NEWPASSWORD);
		String confirmPassword = request.getParameter(Format.CONFIRMPASSWORD);
		try {
			if(oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
				request.setAttribute("message", "Incorrect Some Value is Invalid");				
				RequestDispatcher rd = request.getRequestDispatcher(Format.CHANGEPASSWORD);
				rd.include(request, response);
			}else if(!newPassword.equals(confirmPassword)) {
				request.setAttribute("message", "New Password and Confirm Password not Macth!!!");				
				RequestDispatcher rd = request.getRequestDispatcher(Format.CHANGEPASSWORD);
				rd.include(request, response);
				
			}else if(!loginDatabase.checkOLDPassword(username, oldPassword)) {
				request.setAttribute("message", "Current Password not True Please Try Again");				
				RequestDispatcher rd = request.getRequestDispatcher(Format.CHANGEPASSWORD);
				rd.include(request, response);
				
			}else{
				request.setAttribute("message", "RegisterSuccess");	
				loginDatabase.updatePassword(username, newPassword);
				response.sendRedirect(Format.LOGINSUCCESS);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}

}
