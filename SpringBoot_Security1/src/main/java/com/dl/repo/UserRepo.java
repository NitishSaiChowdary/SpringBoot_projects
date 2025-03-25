package com.dl.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dl.model.Users;

public interface UserRepo extends JpaRepository<Users,Integer>{

	Users findByUsername(String username);
	
	 boolean existsByUsername(String username);
	
	
	
	
}