package com.dl.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


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
	
	//create a new user 
//	public String register(Users user) {
//		if (userRepo.existsByUsername(user.getUsername())) {
////	        return "Username already taken. Please use a different one.";
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already taken. Please use a different one.");
//	    }
//		user.setPassword(encoder.encode(user.getPassword()));
//		userRepo.save(user);
//		 return "User registered successfully.";
//	}
//	
	public ResponseEntity<String> register(Users user){
		if (userRepo.existsByUsername(user.getUsername())) {
			return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Username already exists. Please use a different one.");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");		
	}


    //checking user detials for login
	public String verify(Users user) {
	Authentication authentication=
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}else {
			return "Login failed";
		}
	}

}

//json-javascrpit object Notation is used 
