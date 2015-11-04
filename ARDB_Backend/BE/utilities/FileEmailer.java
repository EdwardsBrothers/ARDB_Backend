package utilities;

import java.io.File;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class FileEmailer {
	
	private String senderName, senderEmail, senderPassword, emailHost, smtpAuth, smtpTLS;;
	private int smtpPort;	
	private Properties props;
	
	public FileEmailer(){
		senderName = "Billing Services";
		senderEmail = "ar@mariettadrapery.com";
		senderPassword = "K@thy2222";
		emailHost = "m38.siteground.biz";
		smtpPort = 25;
		smtpAuth = "true";
		smtpTLS = "false";
		
		setProperties();
	}
	
	public FileEmailer(String senderName, String senderEmail, String senderPassword, String emailHost, int smtpPort, boolean smtpAuth, boolean smtpTLS){
		this.senderName = senderName;
		this.senderEmail = senderEmail;
		this.senderPassword = senderPassword;
		this.emailHost = emailHost;
		this.smtpPort = smtpPort;
		if(smtpAuth){ this.smtpAuth = "true";} else { this.smtpAuth = "false"; }
		if(smtpTLS){ this.smtpTLS = "true";} else {this.smtpTLS = "false";}
		
		setProperties();
	}
	
	private void setProperties(){
		props = System.getProperties();
		props.setProperty("mail.smtp.auth", smtpAuth);
		//props.setProperty("mail.smtp.starttls.enable", smtpTLS);
		props.setProperty("mail.smtp.host", emailHost);
		props.setProperty("mail.smtp.port", Integer.toString(smtpPort));		
	}
	
	private Session createSession(){
				Session session = Session.getInstance(props, new javax.mail.Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
				}
			});
			return session;
	}
	
	public boolean sendTextEmail(String recipientEmail, String subject, String text){
		
		Session session = createSession();
				
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipientEmail));
			message.setSubject(subject);
			message.setContent(text,"text/plain");
			message.setSentDate(new Date());
			Transport.send(message);
			return true;			
		} catch(MessagingException mex){
			mex.printStackTrace();
			return false;
		}
	}

	public boolean sendEmailWithAttachment(String recipientEmail, String subject, String text, String fileLocation, String fileName){
		
		Session session = createSession();
		
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject(subject);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(text);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileLocation);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			message.setSentDate(new Date());
			Transport.send(message);	
			
			return true;
		} catch (MessagingException mex){
			mex.printStackTrace();
			return false;
		}

	}
	
	public boolean sendEmailWithAttachment(String recipientEmail, String subject, String text, String[][] files){
		
		Session session = createSession();
		
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject(subject);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(text);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			//file location i,0 fileName i,1
			for (int i = 0; i < files.length; i++){
				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(files[i][0]);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(files[i][1]);
				multipart.addBodyPart(messageBodyPart);
			}
			
			message.setContent(multipart);
			Transport.send(message);	
			
			return true;
		} catch (MessagingException mex){
			mex.printStackTrace();
			return false;
		}
		
		
		
		
		
	}
}
