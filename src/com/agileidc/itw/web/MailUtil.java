package com.agileidc.itw.web;
/*
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	private String SMTP_HOST = "smtp.gmail.com";
	private String FROM_ADDRESS = "gaddigimathpuneet@gmail.com";
	private String PASSWORD = "Puneet@23";
	private String FROM_NAME = "PuneetG";

	public boolean sendMail(String[] recipients, String[] bccRecipients,
			String subject, String message) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			// props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {

						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"gaddigimathpuneet", "Puneet@23");
						}
					});

			// session.setDebug(true);

			Message msg = new MimeMessage(session);

			InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);
			msg.setFrom(from);

			InternetAddress[] toAddresses = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				
				
				toAddresses[i] = new InternetAddress(recipients[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, toAddresses);

			InternetAddress[] bccAddresses = new InternetAddress[bccRecipients.length];
			for (int j = 0; j < bccRecipients.length; j++) {
				bccAddresses[j] = new InternetAddress(bccRecipients[j]);
			}
			msg.setRecipients(Message.RecipientType.BCC, bccAddresses);

			msg.setSubject(subject);
			msg.setContent(message, "text/plain");
			Transport transport = session.getTransport("smtps");
			String host = "smtp.gmail.com";

			transport.connect(host, "gaddigimathpuneet", "Puneet@23");
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			// Transport.send(msg);
			return true;
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null,
					ex);
			return false;

		} catch (MessagingException ex) {
			Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null,
					ex);
			return false;
		}
	}

	class SocialAuth extends Authenticator {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {

			return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);

		}
	}
}
*/


import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	private String SMTP_HOST = "smtp.agileidc.com";
	private String FROM_ADDRESS = "puneet@agileidc.com";
	private String PASSWORD = "Puneet@23";
	private String FROM_NAME = "IT WorkBoob Admin";

	public boolean sendMail(String[] recipients, String[] bccRecipients,
			String subject, String message) {
		
		System.out.println("======Mail Sent 1 ==========");
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			// props.put("mail.smtp.ssl.enable", "true");
			//props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "25");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
				
			

						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"puneet@agileidc.com", "Puneet@23");
						}
					});

			// session.setDebug(true);
			
			
			System.out.println("======Mail Sent 1 ==========");

			Message msg = new MimeMessage(session);

			InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);
			msg.setFrom(from);

			InternetAddress[] toAddresses = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				toAddresses[i] = new InternetAddress(recipients[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, toAddresses);

			InternetAddress[] bccAddresses = new InternetAddress[bccRecipients.length];
			for (int j = 0; j < bccRecipients.length; j++) {
				bccAddresses[j] = new InternetAddress(bccRecipients[j]);
			}
			msg.setRecipients(Message.RecipientType.BCC, bccAddresses);

			msg.setSubject(subject);
			msg.setContent(message, "text/plain");
			Transport transport = session.getTransport("smtps");
			String host = "smtp.agileidc.com";

			
			System.out.println("======Mail Sent end ==========");
			
			transport.connect(host, "puneet@agileidc.com", "Puneet@23");
		//	transport.sendMessage(msg, msg.getAllRecipients());
			//transport.close();
			 Transport.send(msg);
			 System.out.println("======Mail Sent end end ==========");
			return true;
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null,
					ex);
			return false;

		} catch (MessagingException ex) {
			Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null,
					ex);
			return false;
		}
	}

	class SocialAuth extends Authenticator {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {

			return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);

		}
	}
}