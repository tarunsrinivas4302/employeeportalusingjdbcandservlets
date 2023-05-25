package com.empportal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		
		HttpSession session = request.getSession();
		
		
		
		EmployeeDTO emp = new EmployeeDTO();
		emp.setUsername(username);
		emp.setPassword(password);

		try {
			boolean validate = employeeDAO.validate(emp);
			if (validate) {
				if (username.equalsIgnoreCase(emp.getUsername()) && password.equals(emp.getPassword())) {
					session.setAttribute("username", username);
					response.sendRedirect("index.html");
				}
			} else {
				response.sendRedirect("loginerror.html");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect("loginerror.html");
		} finally {
			pw.close();
		}

	}

}
