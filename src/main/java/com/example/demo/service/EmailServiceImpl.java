package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Email;
import com.example.demo.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	EmailRepository emailRepository;
	
	@Override
	public Email save(Email email) {
		Email savedEmail = emailRepository.save(email);
		return savedEmail;
	}
		
	@Override
	public List<Email> getAll() {
	    return emailRepository.findAll();
	}
	

	@Override
	  public Email sendEmailID(String email) {
        Email sendEmail1 = emailRepository.findByemail(email);
        
        if (sendEmail1 != null) {
            System.out.println("Email found in DB, now calling sendEmail...");
            sendEmail(sendEmail1.getEmail());
        } else {
            System.out.println("No email found for: " + email);
        }
        return sendEmail1; 
    }

    public void sendEmail(String toEmail) {
    	try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom("rsp86753@gmail.com");
        message.setSubject("SpringBoot Email");
        message.setText("Hi! This is from Rutuja");

        javaMailSender.send(message);
      
        System.out.println("Email sent successfully to: " + toEmail);
    } catch (Exception e) {
        System.out.println("Failed to send email: " + e.getMessage());
        e.printStackTrace();
    }
  }

	@Override
	public Email getByID(int id) {
		 Email sendEmail1 = emailRepository.findById(id).get();
	        
	        if (sendEmail1 != null) {
	            System.out.println("Email found in DB, now calling sendEmail...");
	            sendEmail(sendEmail1.getEmail());
	        } else {
	            System.out.println("No email found for: " + id);
	        }
	        
	        return sendEmail1; 
	    }
}
