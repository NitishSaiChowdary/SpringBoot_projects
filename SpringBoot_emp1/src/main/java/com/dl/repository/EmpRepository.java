package com.dl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.dl.model.EmpModel;

@Repository
@EnableJpaRepositories
public interface EmpRepository extends JpaRepository<EmpModel, Long> {
	
	EmpModel findLeadByfirstName(String firstName);

	EmpModel findLeadBylastName(String lastName);
	 
	List<EmpModel> findByFirstNameStartingWith(String prefix);
	
	EmpModel findLeadByemail(String email);

}
