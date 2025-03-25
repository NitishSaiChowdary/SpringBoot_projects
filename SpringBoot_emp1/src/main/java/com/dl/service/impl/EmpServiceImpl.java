package com.dl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.dl.exception.ResourceNotFoundException;
import com.dl.model.EmpModel;
import com.dl.repository.EmpRepository;
import com.dl.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	EmpRepository empRepository;
	
	
	public EmpServiceImpl(EmpRepository empRepository) {
		super();
		this.empRepository = empRepository;
	}
	
	
    //create a single employee
	@Override
	public EmpModel createEmp(EmpModel empModel) {
		return empRepository.save(empModel);
	}
	
	
	//create a multiple employess
    @Override
    public List<EmpModel> createEmployees(List<EmpModel> empModels) {
        return empRepository.saveAll(empModels); // Save a list of employees
    }


	@Override
	public List<EmpModel> getAllEmp() {
		
		return empRepository.findAll();
	}

    //updatelead
	@Override
	public EmpModel updateEmp(@RequestBody EmpModel empModel) {
		
		return empRepository.save(empModel);
	}


	@Override
	public Long countEmp() {
		
		return empRepository.count();
	} 

	//updateEmpById
	@Override
	public EmpModel updateEmpById(EmpModel empModel, Long id) {
		
		// we need to check whether employee with given id is exist in DB or not
		EmpModel existingEmployee = empRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id)); 	
		existingEmployee.setFirstName(empModel.getFirstName());
		existingEmployee.setLastName(empModel.getLastName());
		existingEmployee.setEmail(empModel.getEmail());
		// save existing employee to DB
		empRepository.save(existingEmployee);
		return existingEmployee;
	}


	@Override
	public EmpModel getLeadById(Long id) {
		return empRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee", "Id", id));
	}
	
	@Override
	public void deleteById(Long id) {
		
		empRepository.deleteById(id);
	}


	@Override
	public EmpModel findLeadByfirstName(String firstName) {
		
		return empRepository.findLeadByfirstName(firstName);
	}

	@Override
	public EmpModel findLeadBylastName(String lastName) {
		
		return empRepository.findLeadBylastName(lastName);
	}


	@Override
	public EmpModel findleadByemail(String email) {
		
		return empRepository.findLeadByemail(email);
	}
	
	
	@Override
	public List<EmpModel> findByFirstNameStartingWith(String prefix) {
	
		return empRepository.findByFirstNameStartingWith(prefix);
}


	@Override
	public Page<EmpModel> geEmpByPaginationAndSort(String field, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return empRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.ASC,field)));
	}  




}
