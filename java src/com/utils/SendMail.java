/**
 * 
 */
package com.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 * @author neilly
 * @since 2019/10/25
 */
public class SendMail {

	// 系统发件人邮箱
	public static String sendEmailAccount = "systemposter@163.com";
	// 发件人邮箱授权码
	public static String sendEmailAuthorizationCode = "abc123456";
	// SMTP服务器地址
	public static String sendEmailSMTPHost = "smtp.163.com";
	// 收件人邮箱
	
	public static void sendMail(String receiveEmailAccount, String mailContent) {
		
		Properties props = new Properties();
		
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", sendEmailSMTPHost);
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", "25");
		
		Session session = Session.getDefaultInstance(props);
		
		session.setDebug(true);
		
		try {
			
			MimeMessage message = createMimeMessage(session, sendEmailAccount, receiveEmailAccount, mailContent);
			
			Transport transport = session.getTransport();
			transport.connect(sendEmailAccount, sendEmailAuthorizationCode);
			
			transport.sendMessage(message, message.getAllRecipients());
			
			transport.close();
			
		} catch (NoSuchProviderException nspe) {
			// TODO: handle exception
			nspe.printStackTrace();
			
		} catch (MessagingException me) {
			// TODO: handle exception
			me.printStackTrace();
			
		} catch (UnsupportedEncodingException usee) {
			// TODO: handle exception
			usee.printStackTrace();
		}
		
	}
	
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String mailContent) throws UnsupportedEncodingException, MessagingException {
		
		MimeMessage message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(sendMail, "Library System"));
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "My respectful customer"));
		message.setSubject("Library System Verification Code");
		message.setContent(mailContent, "text/html");
		message.setSentDate(new Date());
		
		message.saveChanges();
		
		return message;
	}
	
	
	
}
