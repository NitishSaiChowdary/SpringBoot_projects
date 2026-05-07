package com.dl.controller;


import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dl.model.Emp;
import com.dl.service.EmpService;

@RestController
@RequestMapping("/api")
public class EmpController {
	
	private final EmpService empService;

	public EmpController(EmpService empService) {
		super();
		this.empService = empService;
	}
	
	@PostMapping("/save")
    public String save(@RequestBody Emp emp) throws Exception {
        return empService.save(emp);
    }
	
	@GetMapping("/getall")
	public SolrDocumentList getAll() throws Exception {
        return empService.getAll();
    }
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") String id) throws Exception {
		return empService.deleteemp(id);
		
	}
	
	@GetMapping("/getbyid/{id}")
	public SolrDocument GetEmpById(@PathVariable("id") String id) throws Exception{
		return empService.GetEmpById(id);
	}
	
	@PutMapping("/update/{id}")
	public String UpdateEmpByid(@RequestBody Emp emp , @PathVariable("id") String id) throws Exception{
		return empService.UpdateEmp(emp, id);
	}

}
