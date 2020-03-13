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
public class ChangeReaderInfoDao {
	
	/**
	 * 读者更改个人信息（除密码）
	 * 
	 * @param rId			读者ID
	 * @param newUsername	新用户名
	 * @param newEmail		新邮件地址
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException sql语句错误
	 */
	public static void changeInfo(String rId, String newUsername, String newEmail) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE reader ");
		sql.append("SET rUsername='");
		sql.append(newUsername);
		sql.append("' AND rEmail='");
		sql.append(newEmail);
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
	
	/**
	 * 
	 * @param rId
	 * @param newUsername
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static boolean changeUsername(String rId, String newUsername) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE reader ");
		sql.append("SET rUsername='");
		sql.append(newUsername);
		sql.append("' ");
		sql.append("WHERE rId='");
		sql.append(rId);
		sql.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			st.executeUpdate(sql.toString());
			
			ConnDB.closeConnection(conn, st, null);
			
			return true;
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @param rId
	 * @param newEmail
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static boolean changeEmail(String rId, String newEmail) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE reader ");
		sql.append("SET rEmail='");
		sql.append(newEmail);
		sql.append("' ");
		sql.append("WHERE rId='");
		sql.append(rId);
		sql.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			st.executeUpdate(sql.toString());
			
			ConnDB.closeConnection(conn, st, null);
			
			return true;
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 读者更改密码
	 * 
	 * @param rId			读者ID
	 * @param newPassword	新密码
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException sql语句错误
	 */
	public static boolean changePassword(String rId, String rPassword, String newPassword) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		StringBuffer sqlToCheck = new StringBuffer();
		sqlToCheck.append("SELECT * ");
		sqlToCheck.append("FROM reader ");
		sqlToCheck.append("WHERE rId='");
		sqlToCheck.append(rId);
		sqlToCheck.append("' AND rPassword='");
		sqlToCheck.append(rPassword);
		sqlToCheck.append("'");
		
		StringBuffer sqlToUpdate = new StringBuffer();
		sqlToUpdate.append("UPDATE reader ");
		sqlToUpdate.append("SET rPassword='");
		sqlToUpdate.append(newPassword);
		sqlToUpdate.append("' ");
		sqlToUpdate.append("WHERE rId='");
		sqlToUpdate.append(rId);
		sqlToUpdate.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqlToCheck.toString());

			if (rs.next()) {
				st.executeUpdate(sqlToUpdate.toString());
				
				return true;
			}
			
			ConnDB.closeConnection(conn, st, rs);
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		return false;
	}
	
	
}
