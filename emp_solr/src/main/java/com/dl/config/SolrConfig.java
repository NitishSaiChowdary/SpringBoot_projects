package com.dl.config;

import org.apache.solr.client.solrj.SolrClient;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.solr.core.SolrTemplate;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

@Configuration
public class SolrConfig {


   
    private static final String SOLR_CORE_URL_STRING = "http://localhost:8983/solr/employee_core";
	@Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(SOLR_CORE_URL_STRING).build();
    }
    
}
