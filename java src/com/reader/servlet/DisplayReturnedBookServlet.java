package com.reader.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reader.dao.ViewDao;
import com.reader.model.ReturnHistory;

/**
 * Servlet implementation class DisplayReturnedBookServlet
 */
@WebServlet("/DisplayReturnedBookServlet")
public class DisplayReturnedBookServlet extends HttpServlet {
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
		
		String rId = (String) request.getSession().getAttribute("rId");
		
		List<ReturnHistory> returnHistories = null;
		try {
			returnHistories = ViewDao.getReturnBookRecord(rId);
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
		
		request.getSession().setAttribute("returnHistories", returnHistories);
		request.getRequestDispatcher("/reader/reader.jsp").forward(request, response);
	}

}
