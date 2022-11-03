package com.login.database;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.bean.Format;

@WebServlet("/ChangeEmail")
public class ChangeEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoginDatabase loginDatabase = new LoginDatabase();

    public ChangeEmail() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter(Format.USERNAME);
		String currentEmail = request.getParameter(Format.CURRENTEMAIL);
		String newEmail = request.getParameter(Format.NEWEMAIL);
		String confirmEmail = request.getParameter(Format.CONFIRMEMAIL);
		try {
			if(currentEmail.isEmpty() || newEmail.isEmpty() || confirmEmail.isEmpty()) {
				request.setAttribute("message", "Incorrect Some Value is Invalid");
				RequestDispatcher rd = request.getRequestDispatcher(Format.CHANGEEMAIL);
				rd.include(request, response);
			}else if(!newEmail.equals(confirmEmail)) {
				request.setAttribute("message", "New Password and Confirm Password not Macth!!!");
				RequestDispatcher rd = request.getRequestDispatcher(Format.CHANGEEMAIL);
				rd.include(request, response);

			}else if(!loginDatabase.checkUserName(username)) {
				request.setAttribute("message", "User not Register");
				RequestDispatcher rd = request.getRequestDispatcher(Format.CHANGEEMAIL);
				rd.include(request, response);

			}else{
				request.setAttribute("message", "RegisterSuccess");
				loginDatabase.updateEmail(username, newEmail);
				response.sendRedirect(Format.LOGINSUCCESS);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
