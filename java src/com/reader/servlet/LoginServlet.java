package com.reader.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.reader.dao.LoginDao;

import instance.Librarian;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String psd = request.getParameter("psd");
		String role = request.getParameter("role");
		
		if (role.equals("reader")) {
			
			try {
			
				String rId = LoginDao.login(username, psd);
				if (rId != null) {
					request.getSession().setAttribute("rId", rId);
					response.sendRedirect(request.getContextPath() + "/reader/reader.jsp");
				} else {
					request.getSession().setAttribute("login", "f");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (role.equals("librarian")) {
			HttpSession session = request.getSession();
			try {
				if(Librarian.login(username, psd)) {

			
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("psd", psd);
					response.sendRedirect(request.getContextPath() + "/librarian/librarian.jsp");
				}
				else {
					String login_jsp= new String("failed");
					request.getSession().setAttribute("login_jsp", login_jsp);
					System.out.println("login_jsp:  "+(String)session.getAttribute("login_jsp"));
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
			
//				request.getRequestDispatcher("/reader/reader.jsp").forward(request, response);
				
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
