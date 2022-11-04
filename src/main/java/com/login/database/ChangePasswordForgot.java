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
import com.login.bean.LoginBean;

@WebServlet("/ChangePasswordForgot")
public class ChangePasswordForgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoginBean loginBean = new LoginBean();
	LoginDatabase loginDatabase = new LoginDatabase();
       
    public ChangePasswordForgot() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoginBean bean = (LoginBean) session.getAttribute(Format.AUTHEN);
		String email = (String) bean.getEmail();
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		if(newPassword.isEmpty() || confirmPassword.isEmpty()) {
			request.setAttribute("message", "Incorrect Some Value is Invalid");
			RequestDispatcher rd = request.getRequestDispatcher(Format.CHANGEPASSWORD);
			rd.include(request, response);
		}else if(!newPassword.equals(confirmPassword)) {
			request.setAttribute("message", "New Password and Confirm Password not Macth!!!");
			RequestDispatcher rd = request.getRequestDispatcher(Format.CHANGEPASSWORD);
			rd.include(request, response);

		}else {
			request.setAttribute("message", "RegisterSuccess");
			loginDatabase.updatePasswordForgot(newPassword, email);
			response.sendRedirect(Format.LOGOUT);
		}

	}

}
