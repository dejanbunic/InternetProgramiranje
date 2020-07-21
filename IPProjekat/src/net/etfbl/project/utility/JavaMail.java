package net.etfbl.project.utility;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.logging.Level;


import java.util.logging.Logger;


import javax.mail.Message;
import javax.mail.MessagingException;



public class JavaMail {
	public static void sendMail(String recepient,String titleMessage, String textMessage) {
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		String myAccountEmail="ipdejanbunicip@gmail.com";
		String password="dejan555";
		Session session = Session.getInstance(properties, new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(myAccountEmail, password);
		}
		});
		Message message = prepareMessage(session,myAccountEmail,recepient, titleMessage, textMessage);
		try {
			Transport.send(message);
			System.out.println("poruka je poslana uspjesno");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("neuspjesno poslano");
		}
	}
	public static Message prepareMessage(Session session, String myAccountEmail,String recepient,String titleMessage,String textMessage) {
		try {
			Message message =  new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject(titleMessage);
			message.setText(textMessage);
			return message;
		}catch (Exception e) {
			Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE,null,e);
		}
		return null;
	}
}
