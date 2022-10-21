package com.login.database;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.login.bean.LoginBean;
import com.login.configuration.ConfigName;
import com.login.configuration.Configure;


public class SendEmail {
	
	public String getRandom() {
		Random rnd = new Random();
		int numRandom = rnd.nextInt(999999);
		
		return String.format("%06d", numRandom);
	}
	
	public boolean sendEmail(LoginBean loginbean) {
		boolean success = false;
		String toMail = loginbean.getEmail();
		String fromMail = Configure.getConfig(ConfigName.MAILSEND);
		String password = Configure.getConfig(ConfigName.PASSEMAIL);
//		String fromMail = "sendemail.01java@gmail.com";
//		String password = "mxqicxbgzmwhatpt";
		
	    Properties prop = new Properties();
	        
//	    prop.put("mail.smtp.host", "smtp.gmail.com");
//	    prop.put("mail.smtp.port", "587");
//	    prop.put("mail.smtp.auth", "true");
//	    prop.put("mail.smtp.starttls.enable", "true");	
	    prop.put(Configure.getConfig(ConfigName.MAILHOST), Configure.getConfig(ConfigName.MAILHOSTNAME));
	    prop.put(Configure.getConfig(ConfigName.MAILPORT), Configure.getConfig(ConfigName.MAILPORTNAME));
	    prop.put(Configure.getConfig(ConfigName.MAILAUTH), Configure.getConfig(ConfigName.MAILAUTHNAME));
	    prop.put(Configure.getConfig(ConfigName.MAILTLS), Configure.getConfig(ConfigName.MAILTLSNAME));
	    Session session = Session.getInstance(prop,
	            new Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(fromMail, password);
	                }
	            });
			
        try {			
        	Message message = new MimeMessage(session);
			
        	message.setFrom(new InternetAddress(fromMail));
        	message.setRecipient( Message.RecipientType.TO, new InternetAddress(toMail));
			
        	message.setSubject("User Email Verification");
        	message.setText("Please Verify your login code: "+ loginbean.getCode());
			
//        	System.out.println("send code:"+loginbean.getCode() +"  to:"+ toMail);
        	
			Transport.send(message);
			
			success = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
		return success;
	}

}
