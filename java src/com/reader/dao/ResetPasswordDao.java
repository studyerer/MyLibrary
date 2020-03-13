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
public class ResetPasswordDao {

	/**
	 * 根据输入用户名（手机号）查询邮箱
	 * 
	 * @param rUsername
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static String getReaderEmail(String rUsername) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		String rEmail = "";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT rEmail ");
		sql.append("FROM reader ");
		sql.append("WHERE rUsername='");
		sql.append(rUsername);
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
	
	/**
	 * 根据输入用户名（手机号）查询ID
	 * 
	 * @param rUsername
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static String getReaderId(String rUsername) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		String rEmail = "";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT rId ");
		sql.append("FROM reader ");
		sql.append("WHERE rUsername='");
		sql.append(rUsername);
		sql.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			if (rs.next()) {
				rEmail = rs.getString("rId");
			}
			ConnDB.closeConnection(conn, st, rs);
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		
		return rEmail;
	}
	
	public static void updateRPassword(String rId, String newPassword) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE reader ");
		sql.append("SET rPassword='");
		sql.append(newPassword);
		sql.append("' ");
		sql.append("WHERE rId='");
		sql.append(rId);
		sql.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			
			st.executeUpdate(sql.toString());
			
			ConnDB.closeConnection(conn, st, null);
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
	}
}
