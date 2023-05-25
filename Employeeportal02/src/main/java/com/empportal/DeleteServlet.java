package com.empportal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		
		try {
			int status = employeeDAO.deleteEmployeeById(id);
			if(status > 0) {
				response.sendRedirect("delete.html");
				
			}
			else {
				response.sendRedirect("register.html");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			pw.close();
		}
	
	
	}

}
