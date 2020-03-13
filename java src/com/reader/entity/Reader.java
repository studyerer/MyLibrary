/**
 * 
 */
package com.reader.entity;

/**
 * @author neilly
 *
 */
public class Reader {

	private String rId;
	private String rUsername;
	private String rEmail;
	private String rPassword;
	
	
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public String getrUsername() {
		return rUsername;
	}
	public void setrUsername(String rUsername) {
		this.rUsername = rUsername;
	}
	public String getrEmail() {
		return rEmail;
	}
	public void setrEmail(String rEmail) {
		this.rEmail = rEmail;
	}
	public String getrPassword() {
		return rPassword;
	}
	public void setrPassword(String rPassword) {
		this.rPassword = rPassword;
	}
	
}
