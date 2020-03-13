/**
 * 
 */
package com.reader.entity;

/**
 * @author neilly
 *
 */
public class ReaderReserveRecord {

	private String resRId;
	private String rId;
	private String bId;
	private String resRLendTime;
	private String resRDueTime;
	private boolean resRStatus;
	
	
	public String getResRId() {
		return resRId;
	}
	public void setResRId(String resRId) {
		this.resRId = resRId;
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
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
	public boolean isResRStatus() {
		return resRStatus;
	}
	public void setResRStatus(boolean resRStatus) {
		this.resRStatus = resRStatus;
	}
	
}
