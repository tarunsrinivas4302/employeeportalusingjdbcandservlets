package com.empportal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.println(
				"<html><head> <script src=\"https://kit.fontawesome.com/c552a57d0e.js\" crossorigin=\"anonymous\">"
				+ "</script></head><body bgcolor = '#ECF0F1'>");
		pw.println("<h1 style = 'text-align: center'><u>EMPLOYEE DETAILS</u></h1>");
		
		

		try {
			List<EmployeeDTO> employeeList = employeeDAO.getEmployeeDetails();
			pw.println("<table style = 'text-align: center';  border='1', width = '100%'>");
			pw.println("       <tr>\r\n" + "            <th>ID</th>\r\n" + "            <th>First Name</th>\r\n"
					+ "            <th>Last Name</th>\r\n" + "            <th>Age</th>\r\n"
					+ "            <th>UserName</th>\r\n"  
//					"            <th>Password</th>\r\n"
					+ "            <th>Phone Number</th>\r\n" + "            <th>Country</th>\r\n"
					+ "            <th>EDIT</th>\r\n" + "            <th>DELETE</th>\r\n" + "        </tr>");
			for (EmployeeDTO emp : employeeList) {
				pw.println("<tr>" + "<td>" + emp.getId1() + "</td>" + "<td>" + emp.getFirstname() + "</td>" + "<td>"
						+ emp.getLastname() + "</td>" + "<td>" + emp.getAge1() + "</td>" + "<td>" + emp.getUsername()
						+ "</td>" + 
//						"<td>" + emp.getPassword() +
						"</td>" + "<td>" + emp.getPhonenumber() + "</td>"
						+ "<td>" + emp.getCountry() + "</td>" + 
						"<td><a href = 'EditServlet?id=" + emp.getId1()+ "'>"
						+ "<i class='fa-solid fa-user-pen'></i> </a> </td>"
						
						+"<td><a href = 'DeleteServlet?id=" + emp.getId1() + "'>"
						+ "<i class='fa-solid fa-trash-can'></i></a></td>"
						
						+ "</tr>");
			}
			pw.println("</table>");
			pw.println("<br><br>");
			pw.println("<a href = register.html> Register New Employee</a>");
			
			pw.println("</body></html>");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
