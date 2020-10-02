package com.marqeton.marqetonapi.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailUtility {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	public void sendEmail(String emailId, String subject, String emailBody) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailId);
        msg.setSubject(subject);
        msg.setText(emailBody);

        javaMailSender.send(msg);

    }

}
