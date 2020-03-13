/**
 * 
 */
package com.reader.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.reader.model.BorrowedBook;
import com.reader.model.ReserveBook;
import com.reader.model.ReturnHistory;
import com.utils.ConnDB;

import instance.LibraryAutomation;

/**
 * @author neilly
 *
 */
public class ViewDao {
	
	/**
	 * 查询已预约书
	 * 
	 * <p> 显示信息包括：书ID、书名、作者、预约时间、有效时间
	 * 
	 * @param rId	读者ID
	 * @return 预约记录
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException sql语句错误
	 */
	public static List<ReserveBook> getReserveBookList(String rId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		List<ReserveBook> reserveBookList = new ArrayList<ReserveBook>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bId, bName, bAuthor, resRLendTime, resRDueTime ");
		sql.append("FROM book NATURAL JOIN (SELECT * ");
		sql.append("FROM (SELECT bId, bISBN ");
		sql.append("FROM bookInfo) AS v1 NATURAL JOIN (SELECT rId, bId, resRLendTime, resRDueTime ");
		sql.append("FROM bookReserveRecord ");
		sql.append("WHERE resRStatus='1' AND rId='");
		sql.append(rId);
		sql.append("') AS v2) AS v ");
		sql.append("ORDER BY resRLendTime");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			while (rs.next()) {
				ReserveBook reserveBook = new ReserveBook();
				
				reserveBook.setbId(rs.getString("bId"));
				reserveBook.setbName(rs.getString("bName"));
				reserveBook.setbAuthor(rs.getString("bAuthor"));
				reserveBook.setResRLendTime(rs.getString("resRLendTime"));
				reserveBook.setResRDueTime(rs.getString("resRDueTime"));
				
				reserveBookList.add(reserveBook);
			}
			ConnDB.closeConnection(conn, st, rs);
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		
		return reserveBookList;
	}
	
	/**
	 * 查询当前借阅书籍
	 * 
	 * <p> 显示信息包括：书籍ID、书名、作者、借阅时间、应还时间
	 * 
	 * @param rId 	读者ID
	 * @return 当前借阅书籍列表
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static List<BorrowedBook> getCurrentBorrowedBook(String rId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		List<BorrowedBook> borrowedBooks = new ArrayList<BorrowedBook>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bId, bName, bAuthor, lenRLendTime, lenRDueTime ");
		sql.append("FROM book NATURAL JOIN (SELECT * ");
		sql.append("FROM (SELECT bId, bISBN ");
		sql.append("FROM bookinfo) AS v1 NATURAL JOIN (SELECT rId, bId, lenRLendTime, lenRDueTime ");
		sql.append("FROM booklendrecord ");
		sql.append("WHERE lenRReturnTime IS NULL AND rId='");
		sql.append(rId);
		sql.append("') AS v2) AS v ");
		sql.append("ORDER BY lenRLendTime");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			while (rs.next()) {
				BorrowedBook borrowedBook = new BorrowedBook();
				
				borrowedBook.setbId(rs.getString("bId"));
				borrowedBook.setbName(rs.getString("bName"));
				borrowedBook.setbAuthor(rs.getString("bAuthor"));
				borrowedBook.setLenRLendTime(rs.getString("lenRLendTime"));
				borrowedBook.setLenRDueTime(rs.getString("lenRDueTime"));
				
				borrowedBooks.add(borrowedBook);
			}
			
			ConnDB.closeConnection(conn, st, rs);
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		
		return borrowedBooks;
	}

	/**
	 * 查询已归还书籍
	 * 
	 * <p> 包括：书籍ID、书名、作者、借阅时间、归还时间、罚金、状态（超时、正常）
	 * 
	 * @param rId
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static List<ReturnHistory> getReturnBookRecord(String rId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
			
			List<ReturnHistory> returnHistoryList = new ArrayList<ReturnHistory>();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT bId, bName, bAuthor, lenRLendTime, lenRReturnTime, lenRFine, lenRStatus ");
			sql.append("FROM book NATURAL JOIN (SELECT * ");
			sql.append("FROM (SELECT bId, bISBN ");
			sql.append("FROM bookinfo) AS v1 NATURAL JOIN (SELECT rId, bId, lenRLendTime, lenRReturnTime, lenRFine, lenRStatus ");
			sql.append("FROM booklendrecord ");
			sql.append("WHERE lenRReturnTime IS NOT NULL AND rId='");
			sql.append(rId);
			sql.append("') AS v2) AS v ");
			sql.append("ORDER BY lenRLendTime DESC");
			
			try {
				Connection conn = LibraryAutomation.getInstance().dbInterface();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql.toString());
				
				while (rs.next()) {
					ReturnHistory returnHistory = new ReturnHistory();
					
					returnHistory.setbId(rs.getString("bId"));
					returnHistory.setbName(rs.getString("bName"));
					returnHistory.setbAuthor(rs.getString("bAuthor"));
					returnHistory.setLenRLendTime(rs.getString("lenRLendTime"));
					returnHistory.setLenRReturnTime(rs.getString("lenRReturnTime"));
					returnHistory.setLenRFine(rs.getDouble("lenRFine"));
					returnHistory.setLenRStatus(rs.getInt("lenRStatus"));
					
					returnHistoryList.add(returnHistory);
				}
				ConnDB.closeConnection(conn, st, rs);
				
			} catch (SQLException sqle) {
				// TODO: handle exception
				sqle.printStackTrace();
			}
			
			return returnHistoryList;
			
		}
	
	/**
	 * 读者查看当前罚金
	 * 
	 * @param rId	读者ID
	 * @return 罚金总计
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static double getCurrentFine(String rId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		double totalFine = 0.00;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(lenRFine) AS totalFine ");
		sql.append("FROM bookLendRecord ");
		sql.append("WHERE lenRStatus='0' AND rId='");
		sql.append(rId);
		sql.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			if (rs.next()) {
				totalFine = rs.getDouble("totalFine");
			}
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		
		return totalFine;
	}
}
