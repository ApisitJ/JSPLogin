package com.login.database;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.bean.Format;
//import com.login.bean.LoginBean;
import com.login.bean.RegisBean;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    LoginDatabase loginDatabase = new LoginDatabase();

    public Register() {
        super();

    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id		= request.getParameter(Format.EMPID);
		String username = request.getParameter(Format.USERNAME);
		String password = request.getParameter(Format.PASSWORD);
		String email    = request.getParameter(Format.EMAIL);
		String group	= request.getParameter(Format.STATUS);

		try {
			if(username.isEmpty() || password.isEmpty() || email.isEmpty()) {
				request.setAttribute("message", "Register Not Success Sumeone is Invalid please try again");
				RequestDispatcher rd = request.getRequestDispatcher(Format.REGISTER);
				rd.include(request, response);
			} else if(loginDatabase.checkUserName(username)) {
				request.setAttribute("message", "Register Not Success Because Username already used ");
				RequestDispatcher rd = request.getRequestDispatcher(Format.REGISTER);
				rd.include(request, response);

			} else if(loginDatabase.checkEmpID(id)) {
				request.setAttribute("message", "Register Not Success Because EmployeeID already used ");
				RequestDispatcher rd = request.getRequestDispatcher(Format.REGISTER);
				rd.include(request, response);

			} else if(loginDatabase.checkEmail(email)) {
				request.setAttribute("message", "Register Not Success Because Email already used ");
				RequestDispatcher rd = request.getRequestDispatcher(Format.REGISTER);
				rd.include(request, response);

			} else {
				request.setAttribute("message", "RegisterSuccess");
				RegisBean regis = new RegisBean(username, password, email, group);
				loginDatabase.getRegisterUser(regis);
				response.sendRedirect(Format.LOGINSUCCESS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(Format.LOGINSUCCESS);
		}



	}

}
