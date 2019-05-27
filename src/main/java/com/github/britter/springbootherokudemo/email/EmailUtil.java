package com.github.britter.springbootherokudemo.email;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailUtil {
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 

 
	public static void generateAndSendEmail(String toMail,String emailBody,String subject) throws AddressException, MessagingException {
 
		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
 
		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, new javax.mail.Authenticator() {
                            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("b.sravankumar1990@gmail.com","Sravan19999990");
            }
        });
		generateMailMessage = new MimeMessage(getMailSession);
                generateMailMessage.setFrom(new InternetAddress("b.sravankumar1990@gmail.com"));
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
		//generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
		generateMailMessage.setSubject(subject);
		//String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		//Transport transport = getMailSession.getTransport("smtp");
                Transport.send(generateMailMessage);
		// Enter your correct gmail UserID and Password	
		// if you have 2FA enabled then provide App Specific Password
		//transport.connect("smtp.gmail.com", "b.sravankumar1990@gmail.com", "Sravan19999990");
		//transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		//transport.close();
	}
}
