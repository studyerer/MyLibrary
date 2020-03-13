/**
 * 
 */
package com.reader.model;

/**
 * @author neilly
 *
 */
public class ReturnHistory {

	private String bId;
	private String bName;
	private String bAuthor;
	private String lenRLendTime;
	private String lenRReturnTime;
	private double lenRFine;
	private int lenRStatus;
	
	
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
	public String getLenRReturnTime() {
		return lenRReturnTime;
	}
	public void setLenRReturnTime(String lenRReturnTime) {
		this.lenRReturnTime = lenRReturnTime;
	}
	public double getLenRFine() {
		return lenRFine;
	}
	public void setLenRFine(double lenRFine) {
		this.lenRFine = lenRFine;
	}
	public int getLenRStatus() {
		return lenRStatus;
	}
	public void setLenRStatus(int lenRStatus) {
		this.lenRStatus = lenRStatus;
	}
	
}
