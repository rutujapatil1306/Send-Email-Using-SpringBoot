package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

	Email findByemail(String email);

}
