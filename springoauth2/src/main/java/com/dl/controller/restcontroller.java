package com.dl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping
public class restcontroller {
	
	
	 @GetMapping("/home")
	 public String welcome() {
		 return "welcome to home";
	 }
	 
	    @GetMapping("/access-denied")
	    public ResponseEntity<String> accessDenied() {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: Only @softsol.com emails are allowed!");
	    }

}
