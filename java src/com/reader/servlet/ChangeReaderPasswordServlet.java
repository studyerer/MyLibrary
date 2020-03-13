package com.reader.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reader.dao.ChangeReaderInfoDao;

/**
 * Servlet implementation class ChangeReaderPasswordServlet
 */
@WebServlet("/reader/ChangeReaderPasswordServlet")
public class ChangeReaderPasswordServlet extends HttpServlet {
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
		String rPassword = (String) request.getParameter("oldPsd");
		String newPassword = (String) request.getParameter("newPsd");
		System.out.println(rId);
		System.out.println(rPassword);
		System.out.println(newPassword);
		
		try {
			if (ChangeReaderInfoDao.changePassword(rId, rPassword, newPassword)) {
				request.getSession().setAttribute("result", "success");
			} else {
				request.getSession().setAttribute("result", "fail");
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

		response.sendRedirect(request.getContextPath() + "/reader/information.jsp");
	}

}
