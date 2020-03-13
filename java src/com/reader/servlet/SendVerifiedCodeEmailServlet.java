package com.reader.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reader.dao.ResetPasswordDao;
import com.utils.SendMail;

/**
 * Servlet implementation class SendVerifiedCodeEmailServlet
 */
@WebServlet("/reader/forget/SendVerifiedCodeEmailServlet")
public class SendVerifiedCodeEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rUsername = request.getParameter("accountkey");
		String rEmail = null;
		try {
			rEmail = ResetPasswordDao.getReaderEmail(rUsername);
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
		
		String rId = null;
		try {
			rId = ResetPasswordDao.getReaderId(rUsername);
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
		request.getSession().setAttribute("rId", rId);
		
		PrintWriter out = response.getWriter();
		
		if (rEmail.equals("")) {
			
			request.setAttribute("exist", "false");
			
			request.getRequestDispatcher("/reader/forget/one.jsp").forward(request, response);

		} else {
			
			request.setAttribute("exist", "true");

			String vCode = "";
			Random random = new Random();
			for (int i = 0; i < 6; i++ ) {
				int r = random.nextInt(10);
				vCode += String.valueOf(r);
			}
			
			request.getSession().setAttribute("vCode", vCode);
			
			String mailContent = "You are trying to reset password! Your verification code is [ " + vCode + " ], which is valid within 1 minute. If it is not your operation, please check your account security.";
			
			SendMail.sendMail(rEmail, mailContent);
			
			out.println("<script>alert('Send verification code successfully! Check your email.');window.location.href='two.jsp'</script>");
		}
	}

}
