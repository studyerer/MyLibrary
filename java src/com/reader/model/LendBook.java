/**
 * 
 */
package com.reader.model;

/**
 * @author neilly
 *
 */
public class LendBook {

	private String bId;
	private String bName;
	private String bAuthor;
	private String lenRLendTime;
	private String lenRDueTime;
	
	
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getbAuthor() {
		return bAuthor;
	}
	public void setbAuthor(String bAuthor) {
		this.bAuthor = bAuthor;
	}
	public String getLenRLendTime() {
		return lenRLendTime;
	}
	public void setLenRLendTime(String lenRLendTime) {
		this.lenRLendTime = lenRLendTime;
	}
	public String getLenRDueTime() {
		return lenRDueTime;
	}
	public void setLenRDueTime(String lenRDueTime) {
		this.lenRDueTime = lenRDueTime;
	}
	
}
