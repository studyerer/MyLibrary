package com.libraian;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import instance.Librarian;
import instance.LibraryAutomation;

import java.net.URL;

import org.json.*;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
/**
 * Servlet implementation class DeleteBook
 */
@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
	
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
		//Librarian librarian = new Librarian((String)session.getAttribute("username"),(String)session.getAttribute("psd"));
		Librarian librarian = new Librarian("12","23");
		String bId = request.getParameter("bId");
		
	    
		try {
			if (librarian.deleteBook(bId)) {
				System.out.println("delete book success");
				String result= new String("success");
				request.getSession().setAttribute("result", result);
				System.out.println("result:  "+(String)session.getAttribute("result"));
				response.sendRedirect(request.getContextPath() + "/librarian/librarian.jsp");
			}else {
				String result= new String(" the book has lent,please return it!!");
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.apache.el.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
