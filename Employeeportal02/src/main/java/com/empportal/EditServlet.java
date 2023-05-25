package com.empportal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html><head>  <link rel=\"stylesheet\" href=\"style.css\">"
				+ "<style>"
				
				+"th{ text-align:left;width : 40%} tr{width:60%}"
				+ ".type {\r\n"
				+ "    width: 85%;\r\n"
				+ "    border: none;\r\n"
				+ "    border-bottom: 2px solid #088;\r\n"
				+ "    padding: 13px;\r\n"
				+ "    font-size: 15px;\r\n"
				+ "}"
				+ ".btn{margin:50px 150px}"
				
				+ "</style>"
				+ " </head>");
		
		out.print("<body>");
		out.print("<div class = 'update'>");
		out.print("<H1> Update Employee </H1>");
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		EmployeeDTO emp = employeeDAO.getEmployeeDetailsById(id);
		out.print("<form action='EditServlet2' method='post'>");
		
		
		out.print("<table class = 'update'>");
		out.print("<tr> <th><label>Your Employee ID</label></th> <td><input type='text' name='id' class = 'type'   "
				+ "value='" + emp.getId1() + "'></td>" + "</tr>");
		
		
		
		out.print("<tr><th><label>Enter Your Updated First Name </label> </th>  <td> <input type='text' "
				+ "class = 'type' "+"name='fname'" + "value='" + emp.getFirstname()
				+ "'></td></tr>");
		
				
		out.print("<tr><th><label> Enter Your Updated Last Name </label> </th>  " + "<td> <input type='text' "
		+"class = 'type'"+ "name='lname' value='" + emp.getLastname()
				+ "'/></td></tr>");
		

		
		out.print("<tr><th>Enter Your Updated Age Here</th> " + "<td> <input type='number' "
				+"class = 'type'"+ "name='age' " + "value='" + emp.getAge1() + "'/>"
				+ "</td></tr>");
		
		
		out.print("<tr><th>Enter Your Updated User name</th>" + "<td> <input type='text' "
				+"class = 'type' "+ "name='uname' value='" + emp.getUsername()
				+ "'/></td></tr>");
		
		
		out.print("<tr><th><label>Enter Your Updated Password</label></th> " + "<td> <input type='text' " +
		"name='password'"+"class = 'type'"+" value='" + emp.getPassword()
				+ "'/></td></tr>");
		
		
		out.print("<tr><th> Enter Your Updated Phone Number</th> " + "<td> <input type='number' "
				+"class = 'type'"+ "name='phonenumber' " + "value='" + emp.getPhonenumber()
		+ "'/></td></tr>");
		

		
		
		out.print("<tr><th><label>Select Your Country Here: </label></th>");
		out.print("<td><select name='country' class = 'type'>");
		out.print("<option> India </option>");
		out.print("<option> USA </option>");
		out.print("<option> UK </option>");
		out.print("<option> Others </option>");
		out.print("</select></td>");
		out.print("</tr>");
		out.print("<br><br>");
		out.print("<tr><td colspan='2'><input type='submit' value='Update' class = 'btn' /><td></tr>");
		
		out.print("</table>");
		out.print("</form>");
		out.print("</div></body></html>");
		out.close();
	}
}
