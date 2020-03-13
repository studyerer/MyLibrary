package com.libraian;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import instance.Librarian;
import instance.LibraryAutomation;
import tools.CheckEmailValidityUtil;


/**
 * Servlet implementation class registerReader
 */
@WebServlet("/registerReader")
public class registerReader extends HttpServlet {
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
       	String acntnum = request.getParameter("acntnum");//获取教程名称
       	String password = request.getParameter("password");//获取教程分类
       	String email=request.getParameter("email");//获取图片信
       	String booknumber="0";
       	String Deposit=request.getParameter("Deposit");//获取教程内容
    	Librarian librarian = new Librarian("12","23");
    	
      	java.util.Date date=new java.util.Date();
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String RegisterDate = sdf3.format(date);
		
	  
	    
	    
	    
		try {
	     	if(librarian.registerReader(acntnum,password,email,booknumber,RegisterDate,Deposit)){
				Connection conn = LibraryAutomation.getInstance().dbInterface();
				String sql1 = "select rId from reader where rUsername="+"'"+acntnum+"'"+"and remail="+"'"+email+"'";//SQL语句
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql1);
				String str1="";
				if(rs.next())
					str1 = String.format("%06d", Integer.parseInt(rs.getString("rId")));
				response.setHeader( "Pragma", "no-cache" );
				response.addHeader( "Cache-Control", "must-revalidate" );
				response.addHeader( "Cache-Control", "no-cache" );
				response.addHeader( "Cache-Control", "no-store" );
				response.setDateHeader("Expires", 0); 
				String result= new String("success");
				request.getSession().setAttribute("result", result);
				response.getWriter().println(str1);
	     	}else{
	     		String result= new String("fail!please check your username,password and email!!");
				request.getSession().setAttribute("result", result);
	     		System.out.println("注册读者失败");
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
