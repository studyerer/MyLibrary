package com.libraian;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import instance.Librarian;

/**
 * Servlet implementation class LendBook
 */
//复制时这里必须改
@WebServlet("/LendBook")
public class LendBook extends HttpServlet {
	
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
		HttpSession session = request.getSession();
		Librarian librarian = new Librarian((String)session.getAttribute("username"),(String)session.getAttribute("psd"));
		String username = request.getParameter("readerName");
		String bookId = request.getParameter("bookId");
		System.out.println("username:"+username);
		System.out.println("bookId:"+bookId);
		try {
			if (librarian.lendBook(username, bookId)) {
				System.out.println("lend book success");
				String result= new String("success");
				request.getSession().setAttribute("result", result);
				System.out.println("result:  "+(String)session.getAttribute("result"));
				response.sendRedirect(request.getContextPath() + "/librarian/librarian.jsp");
			}else {
				String result= new String("failed");
				request.getSession().setAttribute("result", result);
				System.out.println("result:  "+(String)session.getAttribute("result"));
				response.sendRedirect(request.getContextPath() + "/librarian/librarian.jsp");
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
