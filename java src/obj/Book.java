
package obj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import instance.LibraryAutomation;

/**
 * @author ALEXWELL
 *
 */
public class Book {
	private int bookId;
	private String bookName;
	private String ISBN;
	private String press;
	private String price;
	private String category;
	private String position;
	private String status;
	
	public int getBookId() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		String inf = new String();
		//记录这段时间之内的总金额
		int totalDeposit=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//显示2017-10-27格式
		Connection conn = LibraryAutomation.getInstance().dbInterface(); 
		//获得这两段时间之内的保证金记录
		String sql = "SELECT max(bookid) maxBookId FROM book";//SQL语句
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) 
		{
			bookId = rs.getInt("maxBookId");
		}
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
