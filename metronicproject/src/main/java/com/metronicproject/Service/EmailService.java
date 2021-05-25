package com.metronicproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void mailSenderFunction(String username, String verifyKey) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("coskunhuseyin40@gmail.com");
		msg.setTo(username);
		msg.setSubject("Uyeligi tamamla");
		msg.setText("uyeligi tamamlamak icin asagidaki linke tiklayiniz.\n\n" + "http://localhost:8080/register/verifyKey/" + verifyKey);
		mailSender.send(msg);
	}
	
}