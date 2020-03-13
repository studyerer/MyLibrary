package com.libraian;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import tools.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import instance.Librarian;

/**
 * Servlet implementation class GetDate
 */
@WebServlet("/GetDate")
public class GetDate extends HttpServlet {
	
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
		DateUtils dateUtils = new DateUtils();
		String date=request.getParameter("date");
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date1=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(date.equals(""))
			date=sdf.format(new Date());
		try {    
            date1 = format.parse(date);   
		} catch (ParseException e) {    
	            e.printStackTrace();    
		}
		
		System.out.println("option:  "+request.getParameter("option"));
		if(request.getParameter("option").equals("D")) {  
			date=date+"+"+date;
		}else if(request.getParameter("option").equals("M")) { 
			date="";
			date+=dateUtils.getBeginDayOfMonth(date1)+"+";
			date+=dateUtils.getEndDayOfMonth(date1);
		}else if(request.getParameter("option").equals("W")) {
			
			date="";
			date+=dateUtils.getBeginDayOfWeek(date1)+"+";
			date+=dateUtils.getEndDayOfWeek(date1);
			System.out.println("datefirst:  "+date);
		}else if(request.getParameter("option").equals("ALL")) {
			date="";
			date+="0000-00-00"+"+";
			date+=sdf.format(new Date());
		}
		request.getSession().setAttribute("dateqq", date);
		System.out.println("date:  "+(String)session.getAttribute("dateqq"));
		response.sendRedirect(request.getContextPath() + "/librarian/librarian.jsp");
	}
	




}
