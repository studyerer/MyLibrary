/**
 * 
 */
package com.reader.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utils.ConnDB;

import instance.LibraryAutomation;

/**
 * @author neilly
 *
 */
public class ReaderDao {

	public static String getReaderUsername(String rId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		String rUsername = "";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT rUsername ");
		sql.append("FROM reader ");
		sql.append("WHERE rId='");
		sql.append(rId);
		sql.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			if (rs.next()) {
				rUsername = rs.getString("rUsername");
			}
			ConnDB.closeConnection(conn, st, rs);
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		return rUsername;
	}
	
	public static String getReaderEmail(String rId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		String rEmail = "";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT rEmail ");
		sql.append("FROM reader ");
		sql.append("WHERE rId='");
		sql.append(rId);
		sql.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			if (rs.next()) {
				rEmail = rs.getString("rEmail");
			}
			ConnDB.closeConnection(conn, st, rs);
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		return rEmail;
	}
}
