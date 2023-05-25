package com.empportal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/save")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SaveServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String id = request.getParameter("id");
		int id1 = Integer.parseInt(id);
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String age = request.getParameter("age");
		int age1 = Integer.parseInt(age);
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");
		String phone = request.getParameter("phone");
		long phonenumber = Long.parseLong(phone);
		String country = request.getParameter("country");
		
		EmployeeDTO emp = new EmployeeDTO();
		
		emp.setId1(id1);
		emp.setFirstname(firstname);
		emp.setLastname(lastname);
		emp.setAge1(age1);
		emp.setUsername(username);
		emp.setPassword(password);
		emp.setPhonenumber(phonenumber);
		emp.setCountry(country);
		
		int status =0 ;
		status = employeeDAO.saveRegDetails(emp);
		
		
		if(status > 0) {
			response.sendRedirect("succesfullyregistered.html");
		}
		else {
			response.sendRedirect("Notfound.html");
		}
		
	}

}
