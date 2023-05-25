package com.empportal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet2")
public class EditServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditServlet2() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		
		String fname = request.getParameter("fname");

		String lname = request.getParameter("lname");

		String age = request.getParameter("age");
		int age1 = Integer.parseInt(age);

		String username = request.getParameter("uname");

		String password = request.getParameter("password");
		
		String phonenumber = request.getParameter("phonenumber");
		long phonenumber1 = Long.parseLong(phonenumber);

		String country = request.getParameter("country");

		EmployeeDTO emp = new EmployeeDTO();

		emp.setId1(id);
		emp.setFirstname(fname);
		emp.setLastname(lname);
		emp.setAge1(age1);
		emp.setUsername(username);
		emp.setPassword(password);
		emp.setPhonenumber(phonenumber1);
		emp.setCountry(country);

		int status = 0;
		try {
			status = employeeDAO.updateAndSaveEmployeeDetails(emp);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (status == 1) {
			response.sendRedirect("ViewServlet");
		} else {
			out.println("<body bgcolor='cyan'>");
			out.println("<H1>Sorry unable to proceed for updation<H1>");
		}
		out.close();
	}

}
