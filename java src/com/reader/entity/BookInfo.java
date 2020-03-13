/**
 * 
 */
package com.reader.entity;

/**
 * @author neilly
 *
 */
public class BookInfo {

	private String bId;
	private String bISBN;
	private String bLocation;
	private boolean status;
	
	
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public String getbISBN() {
		return bISBN;
	}
	public void setbISBN(String bISBN) {
		this.bISBN = bISBN;
	}
	public String getbLocation() {
		return bLocation;
	}
	public void setbLocation(String bLocation) {
		this.bLocation = bLocation;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
