package com.reader.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reader.dao.SearchBookDao;
import com.reader.model.BookInfoList;

/**
 * Servlet implementation class DisplayBookInfoServelt
 */
@WebServlet("/reader/DisplayBookInfoServelt")
public class DisplayBookInfoServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bISBN = request.getParameter("bISBN");

		List<BookInfoList> bookInfoLists = null;
		try {
			bookInfoLists = SearchBookDao.getBookInfoLists(bISBN);
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

		StringBuffer str = new StringBuffer();

		str.append("<div class=\"head1\" style=\"margin:auto;text-align:center\">"
					+ "<table style=\"margin:auto\"cellspacing=\"0\" cellpadding=\"0\">"
						+ "<tbody>"
							+ "<tr>"
								+ "<td class=\"by\"><a style=\"font-weight: 700\">BookId</a></td>"
								+ "<td class=\"by\"><a style=\"font-weight: 700\">Status</a></td>"
								+ "<td class=\"by\"><a style=\"font-weight: 700\">Option</a></td>"
							+ "</tr>"
						+ "</tbody>"
					+ "</table>"
				+ "</div>"
				+ "<div class=\"bm\"style=\"margin:auto;text-align:center\">"
					+ "<table style=\"margin:auto\"cellspacing=\"0\" cellpadding=\"0\" id=\"table1\">");
		
		for( BookInfoList bookInfoList : bookInfoLists) {
			
			String bId = bookInfoList.getbId();
			int bStatus = bookInfoList.getbStatus();
			
			str.append(		"<tbody id=\"?\">"
								+ "<tr>");
			str.append(				"<td class=\"by\"><a>00000" + bId + "</a></td>");
			
			if (bStatus == 1) {
				str.append(				"<td class=\"by\"><a style=\"color: green\">available</a></td>");
				str.append(				"<td class=\"by\"><a href=\"ReserveBookServlet?bId=" + bId + "&rId=" + request.getSession().getAttribute("rId") + "\" onClick=\"{if(confirm('ARE YOU SURE TO RESERVE THIS BOOK ?')){return ture;}return false;}\"style=\"color: blue;\">reserve</a></td>");
			} else if (bStatus == -1) {
				str.append(				"<td class=\"by\"><a style=\"color: red\">unavailable</a></td>");
				str.append(				"<td class=\"by\"><a style=\"color: grey;\">reserve</a></td>");
			}
			str.append(			  "</tr>"
						  + "</tbody>");
			
		}
		str.append(	 "</table>"
				+ 	 "<br><br>"
				+ "</div>");
		response.getWriter().println(str);
//		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
