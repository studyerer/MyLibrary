/**
 * 
 */
package com.reader.model;

/**
 * @author neilly
 *
 */
public class ReserveBook {

	private String bId;
	private String bName;
	private String bAuthor;
	private String resRLendTime;
	private String resRDueTime;
	
	
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
	public String getResRLendTime() {
		return resRLendTime;
	}
	public void setResRLendTime(String resRLendTime) {
		this.resRLendTime = resRLendTime;
	}
	public String getResRDueTime() {
		return resRDueTime;
	}
	public void setResRDueTime(String resRDueTime) {
		this.resRDueTime = resRDueTime;
	}
	
}
