/**
 * 
 */
package com.reader.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.utils.ConnDB;

import instance.LibraryAutomation;

/**
 * 预约书籍
 * 
 * @author neilly
 * @version 1.0
 * @since 2019/10/9
 */
public class ReserveBookDao {

	/**
	 * 读者预约书籍
	 * <p> 系统操作包括：添加读者预约信息记录、更新书籍状态
	 * 
	 * @param rId 	读者ID
	 * @param bId 	书籍ID
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException sql语句错误
	 */
	public static void reserveBook(String rId, String bId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		Calendar lendTime = Calendar.getInstance();
		Calendar dueTime = Calendar.getInstance();
		dueTime.add(Calendar.HOUR_OF_DAY, 2);
		SimpleDateFormat dfToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		StringBuffer sqlToInsert = new StringBuffer();
		sqlToInsert.append("INSERT INTO bookReserveRecord(rId, bId, resRLendTime, resRDueTime) VALUES ('");
		sqlToInsert.append(rId);
		sqlToInsert.append("', '");
		sqlToInsert.append(bId);
		sqlToInsert.append("', '");
		sqlToInsert.append(dfToDate.format(lendTime.getTime()));
		sqlToInsert.append("', '");
		sqlToInsert.append(dfToDate.format(dueTime.getTime()));
		sqlToInsert.append("')");
		
		StringBuffer sqlToUpdate = new StringBuffer();
		sqlToUpdate.append("UPDATE bookInfo SET bStatus='-1' ");
		sqlToUpdate.append("WHERE bId='");
		sqlToUpdate.append(bId);
		sqlToUpdate.append("'");
		
		try {
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement st = conn.createStatement();
//			System.out.println(sqlToUpdate.toString());
//			System.out.println(sqlToInsert.toString());
			st.executeUpdate(sqlToUpdate.toString());
			st.executeUpdate(sqlToInsert.toString());
			
			ConnDB.closeConnection(conn, st, null);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
}
