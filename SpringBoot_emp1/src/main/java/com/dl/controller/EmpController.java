package com.dl.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dl.model.EmpModel;
import com.dl.service.EmpService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class EmpController {
	
	EmpService empService;

	public EmpController(EmpService empService) {
		super();
		this.empService = empService;
	}
	//create a single employee
	@PostMapping("/single")
	public ResponseEntity<EmpModel> saveEmployee(@RequestBody EmpModel empModel){
		return new ResponseEntity<EmpModel>(empService.createEmp(empModel), HttpStatus.CREATED);
	}
	//create a multiple employess
	@PostMapping("/create")
    public ResponseEntity<List<EmpModel>> createEmployees(@RequestBody List<EmpModel> empModels) {
        List<EmpModel> createdEmps = empService.createEmployees(empModels);
        return ResponseEntity.ok(createdEmps);
    }
	
	//get all leads by
	@GetMapping("/allEmp")
	public List<EmpModel> getAllEmp(){
		return empService.getAllEmp();
	}
	
	//update
	@PutMapping("/updateLead")
	public EmpModel updateEmp(@RequestBody EmpModel empModel) {
		
		return empService.updateEmp(empModel);
		
	}
	@GetMapping("/count")
	public Long countEmp() {
		
		return empService.countEmp();
		
		
	}
	
	//updateEmpById
	@PutMapping("update/{id}")
	public ResponseEntity<EmpModel> updateEmpById(@PathVariable("id")Long id , @RequestBody EmpModel empModel){
		return new ResponseEntity<EmpModel>(empService.updateEmpById(empModel,id), HttpStatus.CREATED);
	}
	//getLeadById
	@GetMapping("/getEmpById/{id}")
	public EmpModel getLeadById(@PathVariable("id")Long id){
		return empService.getLeadById(id);
		
	}
	
	//deleteById
	@CrossOrigin
	@DeleteMapping("delete/{id}")
	public void deleteById(@PathVariable("id")Long id) {
		empService.deleteById(id);
		
	}
	
	//findLeadByfirstName
	@GetMapping("/fname/{firstName}")
    public ResponseEntity<EmpModel> findLeadByfirstName(@PathVariable("firstName") String firstName) {
		EmpModel lead = empService.findLeadByfirstName(firstName);
        if (lead != null) {
            return ResponseEntity.ok(lead);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@GetMapping("/lname/{lastName}")
	public ResponseEntity<EmpModel> findLeadBylastName(@PathVariable("lastName") String lastName){
		EmpModel lead1 =empService.findLeadBylastName(lastName);
		if(lead1 != null) {
			return ResponseEntity.ok(lead1);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	@GetMapping("/{email}")
	public ResponseEntity<EmpModel> findLeadByemail(@PathVariable("email")String email){
		EmpModel lead2 = empService.findleadByemail(email);
		if(lead2 != null) {
			return ResponseEntity.ok(lead2);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping("/findFirstNameStartWith/{prefix}")
	public ResponseEntity<Map<String,Object>> findByFirstNameStartingWith(@PathVariable("prefix") String prefix){
	List<EmpModel> orders =empService.findByFirstNameStartingWith(prefix);
		int count = orders.size();
	
	HashMap<String,Object> response = new HashMap<String,Object>();
	response.put("count", count);//your record count
		response.put("orders", orders);//your record detelis
	return ResponseEntity.ok(response);
	
	}
	
    @GetMapping("/PaginationAndSorting/{offSet}/{pageSize}/{field}")
    public Page<EmpModel> getEmpByPaginationAndSort(@PathVariable int offSet,@PathVariable int pageSize,@PathVariable("field") String field){
		return empService.geEmpByPaginationAndSort(field, offSet, pageSize);
    	
    }


}
