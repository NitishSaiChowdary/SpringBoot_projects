package com.dl.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dl.DTO.ChangePasswordRequest;
import com.dl.model.Users;
import com.dl.repo.UserRepo;

@Service
public class UsersService {
	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	private JwtService jwtService;
	
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	//creating the new user and it will check user name was present or not in data base if it present try with another user name 
	public ResponseEntity<String> register(Users user){
		if (userRepo.existsByUsername(user.getUsername())) {
			return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Username already exists. Please use a different one.");
		}
	    if (userRepo.existsByEmail(user.getEmail())) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Email already exists. Please use a different one.");
	    }
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");		
	}
	
	//get all users

	public List<Users> getAllUsers() {
		
		return userRepo.findAll();
	}
	
	
	//	get users by id
	    public Optional<Users> getUsersById(int id) {
		return userRepo.findById(id);
	}


    //checking user Details for login
	public String verify(Users user) {
	Authentication authentication=
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}else {
			return "Login failed";
		}
	}
	
	//  Update password logic
	 public ResponseEntity<String> updatePassword(ChangePasswordRequest request) {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String username = authentication.getName();
	        Users user = userRepo.findByUsername(username);
	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	        // Check if current password matches
	        if (!encoder.matches(request.getCurrentPassword(), user.getPassword())) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Current password is incorrect");
	        }
	        // Encode and set new password
	        user.setPassword(encoder.encode(request.getNewPassword()));
	        userRepo.save(user);
	        return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully");
	    }
	 
	 //delete by id 
	 public void deleteById(int id) {
		 userRepo.deleteById(id);
	 }
}

