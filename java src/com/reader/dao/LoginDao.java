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
 * 读者登录
 * 
 * @author neilly
 * @version 1.0
 * @since 2019/10/9
 */
public class LoginDao {

	/**
	 * 读者输入用户名密码进行登录
	 * 
	 * <p> 根据读者输入的用户名在数据库中查询密码，与输入密码进行匹配
	 * 
	 * @param username 输入用户名
	 * @param password 输入密码
	 * @return 若匹配成功则返回读者ID
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException sql语句错误
	 */
	public static String login(String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append("FROM reader ");
		sql.append("WHERE rUsername='");
		sql.append(username);
		sql.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			if (rs.next()) {
				if (rs.getString("rPassword").equals(password)) {
					return rs.getString("rId");
				}
			}
			ConnDB.closeConnection(conn, st, rs);
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		}
		return null;
	}
	
}
