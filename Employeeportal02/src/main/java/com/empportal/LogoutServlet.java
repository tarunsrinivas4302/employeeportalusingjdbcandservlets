package com.empportal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html");
			HttpSession session= request.getSession(false);
			 
			 if(session != null){
				 String username = (String)session.getAttribute("username");
				 session.invalidate();
				 pw.print	("<html><body><h1 style = text-align:center;>You are Succesfully Logged Out    "+username+" </h1></body></html>");
				 pw.print("<a href = 'login.html' style = display: block;\r\n"
				 		+ "    width: 100px;\r\n"
				 		+ "    margin: auto;> Login Here </a>");
			 }
			 else {
			 response.sendRedirect("Login.html");
			 }
		}

}
