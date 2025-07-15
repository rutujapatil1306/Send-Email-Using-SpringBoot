package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Email;

public interface EmailService {

	Email save(Email email);

	Email sendEmailID(String email);

	List<Email> getAll();

	Email getByID(int id);

}
