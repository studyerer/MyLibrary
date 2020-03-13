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

import com.reader.model.LendBook;
import com.utils.ConnDB;

import instance.LibraryAutomation;

/**
 * @author neilly
 *
 */
public class LendBookDao {

	/**
	 * 查询已借阅书籍
	 * 
	 * <p> 显示信息包括：书ID、书名、作者、借阅时间、有效时间
	 * 
	 * @param rId	读者ID
	 * @return 借阅记录
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException sql语句错误
	 */
	public static List<LendBook> getLendBookList(String rId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		List<LendBook> lendBookList = new ArrayList<LendBook>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bId, bName, bAuthor, lenRLendTime, lenRDueTime ");
		sql.append("FROM book NATURAL JOIN (SELECT * ");
		sql.append("FROM (SELECT bId, bISBN ");
		sql.append("FROM bookInfo) AS v1 NATURAL JOIN (SELECT rId, bId, lenRLendTime, lenRDueTime ");
		sql.append("FROM bookLendRecord ");
		sql.append("WHERE rId='");
		sql.append(rId);
		sql.append("' AND lenRStatus='1') AS v2) AS v");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			while (rs.next()) {
				LendBook lendBook = new LendBook();
				
				lendBook.setbId(rs.getString("bId"));
				lendBook.setbName(rs.getString("bName"));
				lendBook.setbAuthor(rs.getString("bAuthor"));
				lendBook.setLenRLendTime(rs.getString("lenRLendTime"));
				lendBook.setLenRDueTime(rs.getString("lenRDueTime"));
				
				lendBookList.add(lendBook);
			}
			ConnDB.closeConnection(conn, st, rs);
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		
		return lendBookList;
	}
	
}
