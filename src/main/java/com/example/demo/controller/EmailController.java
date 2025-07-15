package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Email;
import com.example.demo.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@PostMapping("saveEmail")
	public ResponseEntity savemail(@RequestBody Email email) {
		try {
			Email savedEmail = emailService.save(email);
			return new ResponseEntity(savedEmail,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("getById/{id}")
	public ResponseEntity getByID(@PathVariable int id) {
		try {
			Email getById = emailService.getByID(id);
			return new ResponseEntity(getById,HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("sendEmail/{email}")
	public ResponseEntity sendEmail(@PathVariable String email) {
		try {
			Email sendEmail = emailService.sendEmailID(email);
			return new ResponseEntity("Mail Send Successfully",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	
	}
	

	
	@GetMapping("/getAll")
	public ResponseEntity<List<Email>> getAllEmails() {
	    try {
	        List<Email> getAll = emailService.getAll();
	        if (getAll.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
	        }
	        return new ResponseEntity<>(getAll, HttpStatus.OK); // 200 OK is more appropriate
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500 error
	    }
	}
	
	
	
}
