/**
 * 
 */
package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 数据库连接，从配置文件读取数据库信息
 * 
 * @author neilly
 * @version 1.0
 */
public class ConnDB {
	
	private static ResourceBundle resource = ResourceBundle.getBundle("com.utils.dbconfig");
	
	private static String DRIVER = resource.getString("DRIVER");
	private static String URL = resource.getString("URL");
	private static String DBNAME = resource.getString("DBNAME");
	private static String DBSTR = resource.getString("DBSTR");
	private static String USER = resource.getString("USER");
	private static String PASSWORD = resource.getString("PASSWORD");
	
	private static ConnDB instance = null;
	
	private ConnDB() { }
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	public static ConnDB getInstance() {
		if (instance == null) {
			synchronized (ConnDB.class) {
				if (instance == null)
					instance = new ConnDB();
			}
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		
		Connection conn = DriverManager.getConnection(URL + DBNAME + DBSTR, USER, PASSWORD);
		if (conn == null)
			System.out.println("error");
		
		return conn;
	}
	
	public static void closeConnection(Connection conn, Statement st, ResultSet rs) {
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				if (st != null) {
					try {
						st.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException sqle) {
								sqle.printStackTrace();
							}
						}
					}
				}
			}
		}
		
		if (st != null) {
			try {
				st.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				}
			}
		}
	}
}
