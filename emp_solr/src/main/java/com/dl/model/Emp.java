package com.dl.model;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@SolrDocument(collection = "employee_core")
public class Emp {
	
    @Field("id")
	private String id;
	
	@Field("_root_")
	private String root ;
	
	@Field("_version_")
	private Long version;
	
	@Field("skills")
	private List<String> skills;
	
    @Field("department")
	private String department;
	
	@Field("age")
	private Long age;
	
	@Field("name")
	private String Name ;
	
	

}
