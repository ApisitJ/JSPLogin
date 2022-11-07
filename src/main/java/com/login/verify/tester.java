package com.login.verify;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.database.LoginDatabase;

/**
 * Servlet implementation class tester
 */
@WebServlet("/tester")
public class tester extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoginDatabase loginDatabase = new LoginDatabase();
	
    public tester() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPassword = request.getParameter("username");
		String confirmPassword = request.getParameter("password");
		boolean test = loginDatabase.test(newPassword, confirmPassword);
		
		System.out.println(test);
	}

}
