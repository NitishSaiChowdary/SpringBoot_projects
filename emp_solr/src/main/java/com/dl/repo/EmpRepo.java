package com.dl.repo;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.dl.model.Emp;

public interface EmpRepo extends SolrCrudRepository<Emp, String>{

}
