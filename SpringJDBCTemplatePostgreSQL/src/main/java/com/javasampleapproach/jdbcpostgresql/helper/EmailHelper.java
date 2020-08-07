package com.javasampleapproach.jdbcpostgresql.helper;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

@Component
public class EmailHelper
{
	public void sendEmail(String correo,String nomArchivo)
	{
		System.out.println("Se envia al correo "+correo+" el archivo "+nomArchivo);
		try
		{
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			
			Session session = Session.getInstance(props, new javax.mail.Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication("infinityproductions1997@gmail.com", "Buele0997549512");
				}
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("infinityproductions1997@gmail.com", false));

			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(correo));
			msg.setSubject("Gracias por su compra");
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("Muchas gracias por su compra. Saludos",
					"text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			MimeBodyPart attachPart = new MimeBodyPart();
			attachPart.attachFile(nomArchivo);
			multipart.addBodyPart(attachPart);
			msg.setContent(multipart);
			Transport.send(msg);
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
	}
}