package com.reader.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckVerifiedCodeServlet
 */
@WebServlet("/reader/forget/CheckVerifiedCodeServlet")
public class CheckVerifiedCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String vCode = (String) request.getSession().getAttribute("vCode");
		
		String inputVCode = request.getParameter("security");
		
		PrintWriter out = response.getWriter();
		
		if (inputVCode.equals(vCode)) {
			
			response.sendRedirect(request.getContextPath() + "/reader/forget/three.jsp");
		} else {
			
			out.println("<script>alert('Verification Code is Wrong!');window.location.href='two.jsp'</script>");
		}
	}

}
