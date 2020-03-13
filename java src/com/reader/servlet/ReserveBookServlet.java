package com.reader.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reader.dao.ReserveBookDao;

/**
 * Servlet implementation class ReserveBookServlet
 */
@WebServlet("/reader/ReserveBookServlet")
public class ReserveBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchContext = (String) request.getSession().getAttribute("searchContext");
		
		String rId = (String) request.getSession().getAttribute("rId");
		String bId = request.getParameter("bId");
		
		try {
			ReserveBookDao.reserveBook(rId, bId);
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
		
		request.getSession().setAttribute("reserveBook", "s");
		
		response.sendRedirect(request.getContextPath() + "/reader/DisplaySearchBookListServlet?searchContext=" + searchContext);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
