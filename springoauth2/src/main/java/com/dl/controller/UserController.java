package com.dl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dl.model.Users;
import com.dl.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private UsersService usersService;
	
	


	public UserController(UsersService usersService) {
		super();
		this.usersService = usersService;
	}
	//create a new users
	@PostMapping("/register")
	public ResponseEntity<String>  register(@RequestBody Users user) {
		
		return usersService.register(user);
		
	}
	
	 @PostMapping("/login")
	 public String login(@RequestBody Users user) {

	        return usersService.verify(user);
	    }


	 

}
