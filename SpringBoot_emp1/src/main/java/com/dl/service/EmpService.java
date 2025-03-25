package com.dl.service;
import java.util.List;
import org.springframework.data.domain.Page;

import com.dl.model.EmpModel;



public interface EmpService {
	
	EmpModel createEmp(EmpModel empModel);
	////create a multiple employess
	List<EmpModel> createEmployees(List<EmpModel> empModels); 
	
	List<EmpModel> getAllEmp();
	
	EmpModel updateEmp(EmpModel empModel);
	
	Long countEmp();
	
	EmpModel updateEmpById(EmpModel empModel,Long id);
	
	EmpModel getLeadById(Long id);
	
	void deleteById(Long id);
	
	EmpModel findLeadByfirstName(String firstName);
	
	EmpModel findLeadBylastName(String lastName);
	
	EmpModel findleadByemail(String email);
	
	List<EmpModel> findByFirstNameStartingWith(String prefix);
	
	Page<EmpModel> geEmpByPaginationAndSort(String field,int offset,int pageSize);

}
