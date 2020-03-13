package com.libraian;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.el.parser.ParseException;

import instance.Librarian;

/**
 * Servlet implementation class ReturnBook
 */
@WebServlet("/ReturnBook")
public class ReturnBook extends HttpServlet {
	
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
		Librarian librarian = new Librarian("12","23");
		String username = request.getParameter("readerName");
		String bookId = request.getParameter("bookId");
		String frontFine = request.getParameter("frontFine");
		System.out.println(username);
		System.out.println(bookId);
		try {
			if (librarian.returnBook(username, bookId, Float.parseFloat(frontFine))) {
				System.out.print("return book success");
				String result= "success";
				request.getSession().setAttribute("result", result);
				response.sendRedirect(request.getContextPath() + "/librarian/librarian.jsp");
			}else {
				String result= "fail";
				request.getSession().setAttribute("result", result);
				System.out.print("return book failed");
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
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
