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

import com.reader.model.BookInfoList;
import com.reader.model.SearchBook;
import com.utils.ConnDB;

import instance.LibraryAutomation;

/**
 * 
 * @author neilly
 * @version 1.0
 * @since 2019/10/9
 */
public class SearchBookDao {

	/**
	 * 根据输入关键词查询书籍
	 * 
	 * <p> 关键词包括：书籍类别、书名、作者、出版社
	 * 
	 * @param searchContext 输入内容
	 * @return 查询得到书籍列表
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException sql语句错误
	 */
	public static List<SearchBook> getSearchBookList(String searchContext) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		List<SearchBook> searchBookList = new ArrayList<SearchBook>();
		
		// SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bCategory, bISBN, bName, bAuthor, bPress, bFloor, bShelf, bAreaCode, bRemainingNum ");
		sql.append("FROM book NATURAL JOIN (SELECT DISTINCT bookinfo.bISBN, IFNULL(v1.bRemainingNum,0) bRemainingNum ");
		sql.append("FROM bookinfo ");
		sql.append("LEFT JOIN (SELECT bISBN, COUNT(bId) AS bRemainingNum ");
		sql.append("FROM bookInfo ");
		sql.append("WHERE bStatus='1' ");
		sql.append("GROUP BY bISBN) AS v1 ON bookinfo.bISBN=v1.bISBN) AS v2 ");
		sql.append("WHERE (bName LIKE '%");
		sql.append(searchContext);
		sql.append("%' OR bAuthor LIKE '%");
		sql.append(searchContext);
		sql.append("%' OR bPress LIKE '%");
		sql.append(searchContext);
		sql.append("%' OR bCategory LIKE '%");
		sql.append(searchContext);
		sql.append("%') AND 1=1");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			while (rs.next()) {
				SearchBook searchBook = new SearchBook();
				
				searchBook.setbCategory(rs.getString("bCategory"));
				searchBook.setbISBN(rs.getString("bISBN"));
				searchBook.setbName(rs.getString("bName"));
				searchBook.setbAuthor(rs.getString("bAuthor"));
				searchBook.setbPress(rs.getString("bPress"));
				searchBook.setbRemainingNum(rs.getInt("bRemainingNum"));
				
				String location = "F" + rs.getString("bFloor") + "-S" + rs.getString("bShelf") + "-A" + rs.getString("bAreaCode");
				searchBook.setbLocation(location);
				
				searchBookList.add(searchBook);
			}
			
			ConnDB.closeConnection(conn, st, rs);
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		
		return searchBookList;
	}
	
	/**
	 * 展示预约书的具体信息
	 * 
	 * @param bISBN		想要预约书的ISBN
	 * @return 所有copy书的具体信息
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static List<BookInfoList> getBookInfoLists(String bISBN) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		List<BookInfoList> bookInfoLists = new ArrayList<BookInfoList>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bId, bStatus ");
		sql.append("FROM book NATURAL JOIN bookinfo ");
		sql.append("WHERE bISBN='");
		sql.append(bISBN);
		sql.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			while(rs.next()) {
				BookInfoList bookInfoList = new BookInfoList();
				
				bookInfoList.setbId(rs.getString("bId"));
				bookInfoList.setbStatus(rs.getInt("bStatus"));
				
				bookInfoLists.add(bookInfoList);
			}
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		
		return bookInfoLists;
	}
}
