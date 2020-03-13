/**
 * 
 */
package com.reader.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.reader.model.ViewBookInfo;
import com.utils.ConnDB;

import instance.LibraryAutomation;

/**
 * @author neilly
 *
 */
public class BookDao {

	public static ViewBookInfo getBookInfo(String bName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bName, bAuthor, bCategory, bPress, bISBN, bDescription ");
		sql.append("FROM book ");
		sql.append("WHERE bName='");
		sql.append(bName);
		sql.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			if (rs.next()) {
				ViewBookInfo viewBookInfo = new ViewBookInfo();
				
				viewBookInfo.setbName(rs.getString("bName"));
				viewBookInfo.setbAuthor(rs.getString("bAuthor"));
				viewBookInfo.setbCategory(rs.getString("bCategory"));
				viewBookInfo.setbPress(rs.getString("bPress"));
				viewBookInfo.setbISBN(rs.getString("bISBN"));
				viewBookInfo.setbDescription(rs.getString("bDescription"));
				
				return viewBookInfo;
			}
			
			ConnDB.closeConnection(conn, st, rs);
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		
		return null;
	}
}
