package instance;

import obj.User;
import tools.CheckEmailValidityUtil;

import java.util.Date;
import java.util.Vector;

import org.apache.el.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
/**
 * @bAuthor ALEXWELL
 *
 */
public class Librarian extends User {

	/**
	 * 
	 */
	public Librarian(String rId, String rPassword) {
		// TODO Auto-generated constructor stub
		super(rId, rPassword, "Librarian");
	}
	public boolean addBook(String bAuthor,String bName,String bPress,String bPrice,String bFloor,String bShelf,String bAreaCode,String bISBN,int bStatus,String bCategory,String bDescription) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException{

		 if(bAuthor.equals("")||bName.equals("")||bPress.equals("")||bFloor.equals("")||bShelf.equals("")||bAreaCode.equals("")||bISBN.equals("")||bCategory.equals("")||bDescription.equals(""))
		 {		
			 System.out.println("请填写书籍完整信息");
		 	 return false;
		 }
		 String sql;
		 PreparedStatement pstmt;
		 Connection conn = LibraryAutomation.getInstance().dbInterface();
		 String sql_add="select * from book where bISBN='"+bISBN+"'";
		 Statement stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery(sql_add);
		 if(!rs.next()) {	
		 	sql = "INSERT INTO book(bAuthor, bName, bPress, bPrice, bFloor,bShelf,bAreaCode, bISBN, bCategory, bDescription) VALUES (?,?,?,?,?,?,?,?,?,?)";
		 	pstmt = conn.prepareStatement(sql);	
	        pstmt.setString(2, bName);
	        pstmt.setString(1, bAuthor);
	        pstmt.setString(3, bPress);
	        pstmt.setString(4, bPrice);
	        pstmt.setString(5, bFloor);
	        pstmt.setString(6, bShelf);
	        pstmt.setString(7, bAreaCode);
	        pstmt.setString(8, bISBN);  
	        pstmt.setString(9, bCategory);
	        pstmt.setString(10, bDescription);
	        pstmt.executeUpdate();
	    }
		sql = "INSERT INTO bookInfo(bISBN,bStatus) VALUES (?,?)";		
		pstmt = conn.prepareStatement(sql);	
		pstmt.setString(1,bISBN);
		pstmt.setInt(2,bStatus);
		pstmt.executeUpdate();
	    return true;
	}
	public void editBook(String bISBN,String bFloor,String bShelf,String bAreaCode,String bCategory)throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		Connection conn = LibraryAutomation.getInstance().dbInterface();
		 String sql = "update book set bFloor="+"'"+bFloor+"'"+","+" bAreaCode="+"'"+bAreaCode+"'"+","+" bShelf="+"'"+bShelf+"'"+","+" bCategory="+"'"+bCategory+"'"+" where bISBN='"+bISBN+"'";//SQL���
		 System.out.println(sql);
		 PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	        System.out.println("success");
	}
	public boolean deleteBook(String bId)throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException, java.text.ParseException, ParseException {
			/*System.out.println("deleteBook()---bId: " + bId);
			int bookIdInt= Integer.parseInt(bId);
			System.out.println("deleteBook()---bookIdInt: " + bookIdInt);*/
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement stmt = conn.createStatement();
			String sqlGetAcntNum = "select * from booklendrecord where bId="+bId;
			ResultSet rs=stmt.executeQuery(sqlGetAcntNum);
			if(rs.next()) {
				if(rs.getInt("lenRstatus")==1)
					return false;
			}
			Date date = new Date();
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateNowStr = sdf3.format(date); 
			Date d2=sdf3.parse(dateNowStr);
			
			LibraryAutomation automation = LibraryAutomation.getInstance();
			float fine=automation.getBookFineValue();
			
			
			String sql = "delete from bookInfo where bId="+bId;//SQL���
		 	
		 	String sql_delete="select * from bookinfo where bId='"+bId+"'";

			//ResultSet rs1 = stmt.executeQuery(sqlGetAcntNum);
			rs = stmt.executeQuery(sql_delete);
		 	
		 	String bISBN="";
		 	if(rs.next())
		 		bISBN=rs.getString("bISBN");
		 	sql_delete="select * from bookinfo where bISBN='"+bISBN+"'";

	     	PreparedStatement pstmt2 = conn.prepareStatement(sql);
	        pstmt2.executeUpdate();
	        rs = stmt.executeQuery(sql_delete);
	        if(!rs.next())
	        {
	        	sql = "delete from book where bISBN='"+bISBN+"'";
	        	pstmt2 = conn.prepareStatement(sql);
		        pstmt2.executeUpdate();
	        }
	        return true;
	}
	//增加了telephone,acntnum自动生成
	public boolean  registerReader(String rUsername,String rPassword,String rEmail,String bookNumber,String registerDate,String Deposit)  throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException{ 
		if(rUsername.equals("")||rPassword.equals("")||rEmail.equals("")||Deposit.equals(""))
		 {		
			 System.out.println("请填写完整信息");
		 	 return false;
		 }
		CheckEmailValidityUtil isEmail=new CheckEmailValidityUtil();
		if(!isEmail.isEmailValid(rEmail))
			return false;
		Connection conn = LibraryAutomation.getInstance().dbInterface();
		 String sql = "INSERT INTO reader(rUsername,rPassword,rEmail,bookNumber,registerDate,Deposit) "+"VALUES (?,?,?,?,?,?)";//SQL���
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, rUsername);		
	        pstmt.setString(2, rPassword);
	        pstmt.setString(3, rEmail);
	        pstmt.setString(4,bookNumber);
	        pstmt.setString(5,registerDate);
	        pstmt.setString(6,Deposit);
	        pstmt.executeUpdate();
	        System.out.println("success");
	    return true;
	}
	@SuppressWarnings("static-access")
	public boolean editReader(String rId,String rUserName,String rPassword,String rEmail) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		CheckEmailValidityUtil isEmail=new CheckEmailValidityUtil();
		if(!isEmail.isEmailValid(rEmail))
			return false;
		Connection conn = LibraryAutomation.getInstance().dbInterface();
		String sql = "update reader set rPassword="+"'"+rPassword+"'"+","+" rEmail="+"'"+rEmail+"'"+","+" rUsername="+"'"+rUserName+"'"+" where rId="+rId;//SQL���
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.executeUpdate();
	    System.out.println("success");
	    return true;
	}
	public boolean deleteReader(String rId)throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		Connection conn = LibraryAutomation.getInstance().dbInterface();
		String sql = "delete from reader where rId='"+rId+"'";//SQL���
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.executeUpdate();
	    System.out.println("success");
	    return true;
	}
	public Vector<String> viewBorrowRecords() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		Vector<String> borrowRecords=new Vector<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//显示2017-10-27格式
		String borrowRecord=null;
		Connection conn = LibraryAutomation.getInstance().dbInterface();
		String sql1 = "select * from bookLendRecord where lenRReturnTime is null" ;//SQL语句
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);
		while(rs.next()) {
        	//BorrowDate为null如何解决
        	borrowRecord = rs.getString("bId")+'\t'+rs.getString("rId")+'\t'+sdf.format(rs.getDate("lenRLendTime"))+'\t'+"——"+'\t'+"lent";
        	borrowRecords.addElement(borrowRecord);
        }
		String sql2 = "select * from bookLendRecord where lenRReturnTime is not null" ;//SQL语句
		rs = stmt.executeQuery(sql2);
        while(rs.next()) {
        	//BorrowDate为null如何解决
        	borrowRecord = rs.getString("bId")+'\t'+rs.getString("rId")+'\t'+sdf.format(rs.getDate("lenRLendTime"))+'\t'+sdf.format(rs.getDate("lenRReturnTime"))+'\t'+"returned";
        	borrowRecords.addElement(borrowRecord);
        }
		return borrowRecords; 
	}
	public Vector<String> viewFineRecords(String recordId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		Vector<String> FineRecords = new Vector<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//显示2017-10-27格式
		String FineRecord=null;
		Connection conn = LibraryAutomation.getInstance().dbInterface();
		String sql = "select rId,lenRFine,lenRLendTime,lenRReturnTime from bookLendRecord where lenRFine!=0 and lenRReturnTime is not null";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) { 
        	FineRecord= rs.getString("rId")+'\t'+rs.getString("lenRFine")+'\t'+sdf.format(rs.getDate("lenRLendTime"))+'\t'+sdf.format(rs.getDate("lenRReturnTime"));
        	FineRecords.addElement(FineRecord);
        }
		return FineRecords;
	}
	
	public boolean lendBook(String rId,String bId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		if(rId.length()!=6||bId.length()!=6)
			return false;
		
		Date date = new Date();
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf3.format(date); 
		String str1="select booknumber from reader where reader.rId='"+rId+"'";
		Connection conn1 = LibraryAutomation.getInstance().dbInterface();
		Statement stmt1 = conn1.createStatement();
		ResultSet rs1 = stmt1.executeQuery(str1);
		int number=0;
		LibraryAutomation automation = LibraryAutomation.getInstance();
		int days=automation.getBookReturnPeriod();
		if(rs1.next()) 
			number = rs1.getInt("booknumber");
		else {
			System.out.println("lendBook()-str1出错");
			return false;//必须保证此读者存在
			}
		if(number<3)
		{		
			String str ="select bStatus from bookInfo  where bId = "+bId;
			String getReader ="select rId from bookLendRecord  where bId = "+bId;
			int GetStatus;
			String GetReader=null;
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(str);
			if(rs.next()) {
				GetStatus=rs.getInt("bStatus");
			}else {
				System.out.println("lendBook()-2错");
				return false;//必须保证此读者存在
				}
		/*	rs = stmt.executeQuery(getReader);
			if(rs.next()) {
				GetReader=rs.getString("rId");
			}
			else {
				System.out.println("lendBook()-3错");
				return false;//必须保证此读者存在
				}*/
			System.out.println(GetStatus);
			if(GetStatus==1)
			{
				String deleteReserve = "UPDATE bookLendRecord SET lenRStatus =1 where bId='"+bId+"'";
				String sql = "UPDATE bookinfo SET bStatus =-1 where bId = '"+bId+"'";//SQL语句
				String sql1="UPDATE reader set booknumber=booknumber+1 where rId='"+rId+"'";
				String bookLendRecord="insert into  bookLendRecord(lenRFine, lenRLendTime,lenRReturnTime,bId,rId,lenRDueTime) VALUES (?,?,?,?,?,?)";
				
				
				
				PreparedStatement pstmt3 = conn.prepareStatement(bookLendRecord);		
		        pstmt3.setFloat(1, 0);
		        pstmt3.setString(2, dateNowStr);		
		        pstmt3.setString(3, null);
		        pstmt3.setString(4, bId);		
		        pstmt3.setString(5, rId);
		        pstmt3.setString(6, sdf3.format(new Date((new Date()).getTime() + (long)days * 24 * 60 * 60 * 1000)));
				pstmt3.executeUpdate();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				PreparedStatement pstmt2 = conn.prepareStatement(deleteReserve);
				pstmt2.executeUpdate();
				PreparedStatement pstmt1 = conn.prepareStatement(sql1);
				pstmt1.executeUpdate();
				return true;
			}
		}	
		return false;
	}
	
	public boolean returnBook(String rId,String bId,float frontFine) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException, ParseException, java.text.ParseException{
		if(rId.length()!=6||bId.length()!=6)
			return false;
		
		Date date = new Date();
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf3.format(date); 
		Connection conn = LibraryAutomation.getInstance().dbInterface();
		String sql4 = "select bStatus from bookInfo where bId='"+bId+"'";//SQL语句
		String sql5 = "select * from bookLendRecord  where rId='"+rId +"'and bId='"+bId+"' and lenRReturnTime is null";//SQL语句
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql5);
		//罚金值
		LibraryAutomation automation = LibraryAutomation.getInstance();
		int days=automation.getBookReturnPeriod();
		float fine=automation.getBookFineValue();
		float totalFine=0;
		String sql2="";
		String sql6="";
		//d是Date类型，Date->String, String->Date,Date通过getTime又变为long类型
		Date d2=sdf3.parse(dateNowStr);
		PreparedStatement pstmt;
		if(rs.next()&&(d2.getTime()-rs.getDate("lenRLendTime").getTime())>days) {
			System.out.println("time:"+(d2.getTime()-rs.getDate("lenRLendTime").getTime())/(3600*24*1000));
			totalFine=(d2.getTime()-rs.getDate("lenRLendTime").getTime())/(3600*24*1000)*fine;
			sql2="UPDATE bookLendRecord set lenRReturnTime='"+dateNowStr+"' where rId='"+rId +"'and bId='"+bId+"' and lenRFine='"+frontFine+"' and lenRReturnTime is null";
			if((totalFine-frontFine)>0) {
		    	 sql6="UPDATE bookLendRecord set lenRStatus=0";	
		    }
		    else {
		    	 sql6="UPDATE bookLendRecord set lenRStatus=-1";	
		    }
			pstmt = conn.prepareStatement(sql6);
			pstmt.executeUpdate();
		}else {
			sql2="UPDATE bookLendRecord set lenRReturnTime='"+dateNowStr+"',lenRStatus=-1 where rId='"+rId +"'and bId='"+bId+"' and lenRReturnTime is null";
		}
		rs = stmt.executeQuery(sql4);
		if(rs.next()) {
			if(rs.getInt("bStatus")==-1) {
				String sql = "UPDATE bookInfo SET bStatus=1 where bId = '"+bId+"'";//SQL语句
				String sql1="UPDATE reader set booknumber=booknumber-1 where rId='"+rId+"'";				
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				PreparedStatement pstmt1 = conn.prepareStatement(sql1);
				pstmt1.executeUpdate();
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.executeUpdate();
				return true;
			}
		}
		return false;
	}
	
	public Vector<String> viewTotalDeposit(String beginDate,String endDate) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		//分条记录这段时间之内的所有信息
		Vector<String> totalDepositInf=new Vector<String>();
		String inf = new String();
		//记录这段时间之内的总金额
		int totalDeposit=0;
		System.out.println("viewTotalDeposit:  "+beginDate+"   "+endDate);
		Connection conn = LibraryAutomation.getInstance().dbInterface(); 
		//获得这两段时间之内的保证金记录
		String sql = "SELECT Deposit,registerDate,rId FROM reader where date(registerDate) between "+"'"+beginDate+"'"+" and "+"'"+endDate+"'";//SQL语句
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) 
		{
			inf=rs.getString("rId")+'\t'+rs.getDate("registerDate")+'\t'+rs.getInt("Deposit");
			totalDepositInf.addElement(inf);
			totalDeposit+=rs.getInt("Deposit");
		}
		totalDepositInf.addElement("totalDeposit: "+totalDeposit);
		return totalDepositInf; 
	}
	public Vector<String> viewTotalFine(String beginDate,String endDate) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		Vector<String> totalFineInf= new Vector<String>();
		String inf = new String();
		//记录这段时间之内的总金额
		int totalFine=0;
		Connection conn = LibraryAutomation.getInstance().dbInterface(); 
		//获得这两段时间之内的保证金记录
		String sql = "SELECT lenRReturnTime,lenRFine,rId FROM bookLendRecord where lenRReturnTime is not null and date(lenRReturnTime) between "+"'"+beginDate+"'"+" and "+"'"+endDate+"' and lenRFine!=0" ;//SQL语句
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			inf=rs.getString("rId")+'\t'+rs.getDate("lenRReturnTime")+'\t'+rs.getInt("lenRFine");
			totalFineInf.addElement(inf);
			totalFine+=rs.getInt("lenRFine");
		}
		totalFineInf.addElement("totalDeposit: "+totalFine);
		return totalFineInf;
	}
	public void editRecord(String recordId,String title,String content) {
		
	}
	
	public void addAnnouncement(String textId, String text,String date1)throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException{
		Connection conn = LibraryAutomation.getInstance().dbInterface();
		 String sql = "INSERT INTO announcement "+"VALUES (?,?,?)";
		 PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, textId);		
	        pstmt.setString(2, text);
	        pstmt.setString(3, date1);
	        pstmt.executeUpdate();
	        System.out.println("success");
	}
	
	public void editAnnouncement(String targetTextId, String changeText)throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException{
		Connection conn = LibraryAutomation.getInstance().dbInterface();
		Date date = new Date();
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf3.format(date); 
		 String sql = "update announcement set Text="+"'"+changeText+"'"+","+" Date="+"'"+dateNowStr+"'"+" where TextId="+targetTextId;//SQL���
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	        System.out.println("success");
	}
	
	public void deleteAnnouncement(String targetTextId)throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException{
		Connection conn = LibraryAutomation.getInstance().dbInterface();
		String sql="delete from announcement where TextId="+targetTextId;
		 PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	        System.out.println("success");
	}
	public Vector<String> searchBook(String content)throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException{
		Vector<String> totalbook= new Vector<String>();
		Connection conn = LibraryAutomation.getInstance().dbInterface();
		/*
		String sql="select * from book";
		Statement stmt = conn.createStatement();
    	ResultSet rs = stmt.executeQuery(sql);
    	String bId=null;
    	String bName=null;
    	String bPress=null;
    	String bPrice=null;
    	String position=null;
    	String bCategory=null;
    	String bAuthor=null;
    	String bDescription=null;
    	String bISBN=null;
    	 while(rs.next())
         {   
       	  
             bId = rs.getString("bId");
             bName = rs.getString("bName");
             bPress=rs.getString("bPress");
             bPrice=rs.getString("bPrice");
             position = rs.getString("Position");
             bCategory = rs.getString("bCategory"); 
             bAuthor=rs.getString("bAuthor");
             bDescription=rs.getString("bDescription");
             bISBN=rs.getString("bISBN");
             if(content.equals(bId)||content.equals(bName)||content.equals(bPress)||content.equals(bPrice)||content.equals(position)||content.equals(bCategory)||content.equals(bAuthor)||content.equals(bDescription)||content.equals(bISBN)) {
            	 totalbook.addElement(bId);
             }
            	 
         }
    	 return totalbook;*/
		/*StringBuffer sql = new StringBuffer();
		sql.append("select * from book");
		sql.append("where bId like '%");
		sql.append(content);
		sql.append("%' or bName like '%");
		sql.append(content);
		sql.append("%' or bPress like '%");
		sql.append(content);
		sql.append("%'or bPrice like '%");
		sql.append(content);
		sql.append("%'or Position like '%");
		sql.append(content);
		sql.append("%'or bCategory like '%");
		sql.append(content);
		sql.append("%'or Author like '%");
		sql.append(content);
		sql.append("%'or bISBN like '%");
		sql.append(content);
		sql.append("%' AND 1=1");*/
		String sql="select * from book where bISBN like '%"+content+"%'or bName like '%"+content+"%'or bPress like '%"+content+"%'or bPrice like '%"+content+"%'or bDescription like '%"+content+"%'or bCategory like '%"+content+"%'or bAuthor like '%"+content+"%'or bFloor like '%"+content+"%'or bShelf like '%"+content+"%'or bAreaCode like '%"+content+"%'";
		Statement stmt = conn.createStatement();
    	ResultSet rs = stmt.executeQuery(sql);
    	String bISBN=null;
    	 while(rs.next())
         {   
       	  
        	 bISBN = rs.getString("bISBN");
        	 totalbook.addElement(bISBN);
            	 
         }
    	 return totalbook;
	}
	@Override
	public String toString() {
		return "Librarian [toString()=" + super.toString() + "]";
	}
	public static boolean login(String rId, String rPassword) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException{
			Connection conn = LibraryAutomation.getInstance().dbInterface();
			String sql = "SELECT count(*) num FROM Librarian where acntnum='"+rId+"'";//SQL语句
		
		
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				if(rs.getInt("num")==1) {
					
					sql = "SELECT Password FROM Librarian where acntnum = '"+rId+"'";//SQL语句
					rs = stmt.executeQuery(sql);
					if(rs.next()) {
						if(rs.getString("Password").equals(rPassword)) {
							return true;
						}
					}
					
				}
				
			}
		return false;
	}

}
