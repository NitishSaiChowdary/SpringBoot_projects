package com.dl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dl.DTO.ChangePasswordRequest;
import com.dl.model.Users;
import com.dl.service.UsersService;

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
	
	//get all users
	@GetMapping("/allEmp")
	public List<Users> getAllUsers(){
		return usersService.getAllUsers();
	}
	
	//getLeadById
		@GetMapping("/getusersById/{id}")
		public Optional<Users> getUsersById(@PathVariable("id")int id){
			return usersService.getUsersById(id);
			
		}
	
	 @PostMapping("/login")
	 public String login(@RequestBody Users user) {

	        return usersService.verify(user);
	    }
	 
	    //Update password endpoint
	    @PutMapping("/update-password")
	    public ResponseEntity<String> updatePassword(@RequestBody ChangePasswordRequest request) {
	        return usersService.updatePassword(request);
	    }
	    
		@DeleteMapping("delete/{id}")
		public void deleteById(@PathVariable("id")int id) {
			usersService.deleteById(id);
			
		}


}
