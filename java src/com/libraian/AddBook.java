package com.libraian;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	
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
		String ISBN=request.getParameter("ISBN");
		String bookId = request.getParameter("bookid");
	    String category = request.getParameter("category");
	    String bookName=request.getParameter("name");
	    String press=request.getParameter("press");
	    String price=request.getParameter("price");
	    String bFloor=request.getParameter("bFloor");
	    String bShelf=request.getParameter("bShelf");
	    String bAreaCode=request.getParameter("bAreaCode");
	    String author=request.getParameter("author");
	    int status=1;
	    String description=request.getParameter("description");
	   
	  
	    
	    
	    
		try {
			if (librarian.addBook(author, bookName, press, price, bFloor,bShelf,bAreaCode, ISBN, status, category, description)) {
				System.out.println("add book success");
				String result= new String("success");
				request.getSession().setAttribute("result", result);
				System.out.println("result:  "+(String)session.getAttribute("result"));
				Connection conn = LibraryAutomation.getInstance().dbInterface();
				String sql1 = "select max(bId) bookid from bookinfo where bISBN="+"'"+ISBN+"'";//SQL语句
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql1);
				String str1="";
				if(rs.next())
					str1 = String.format("%06d", Integer.parseInt(rs.getString("bookid")));  
				response.getWriter().println(str1);
				//response.sendRedirect(request.getContextPath() + "/librarian/librarian.jsp");
				
			}else {
				String result= new String("failed");
				request.getSession().setAttribute("result", result);
				System.out.println("result:  "+(String)session.getAttribute("result"));
				//response.sendRedirect(request.getContextPath() + "/librarian/librarian.jsp");
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
