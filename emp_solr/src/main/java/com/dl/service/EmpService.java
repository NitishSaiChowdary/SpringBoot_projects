package com.dl.service;

import javax.crypto.Cipher;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import com.dl.model.Emp;

@Service
public class EmpService {
	
	private SolrClient client;

	public EmpService(SolrClient client) {
		super();
		this.client = client;
	}
	
	
	//Add
	 public String save(Emp emp) throws Exception {
	        SolrInputDocument doc = new SolrInputDocument();
	        doc.addField("id", emp.getId());
	        doc.addField("name", emp.getName());
	        doc.addField("root", emp.getRoot());
	        doc.addField("version", emp.getVersion());
	        doc.addField("skills", emp.getSkills());
	        doc.addField("department", emp.getDepartment());
	        doc.addField("age", emp.getAge());
	        
	        client.add(doc);
	        client.commit();
	        return "Saved";
	    }
	 
	 //get all emp
	 public SolrDocumentList getAll() throws Exception {
	        SolrQuery query = new SolrQuery("*:*");
	        query.setRows(300);
	        QueryResponse response = client.query(query);
	        return response.getResults();
	    }

	 //delete emp
	 public String deleteemp(String id) throws Exception{
		 client.deleteById(id);
		 client.commit();
		 return "deleted";
	 }
	 
	 //getempByid
	 public SolrDocument GetEmpById(String id) throws Exception{
		 return client.getById(id);
	 }
	 
	 
	 //update
	 public String UpdateEmp(Emp emp, String id) throws Exception{
		 var existingDoc = client.getById(id);
		    if (existingDoc == null) {
		        return "Employee with ID " + id + " not found!";
		    }
		    SolrInputDocument doc = new SolrInputDocument();
	        doc.addField("id", id);
	        doc.addField("name", emp.getName());
	        doc.addField("root", emp.getRoot());
	        doc.addField("version", emp.getVersion());
	        doc.addField("skills", emp.getSkills());
	        doc.addField("department", emp.getDepartment());
	        doc.addField("age", emp.getAge());
	        client.add(doc);
	        client.commit();

	        return "Employee updated successfully!";		    
		 
	 }
	

}
