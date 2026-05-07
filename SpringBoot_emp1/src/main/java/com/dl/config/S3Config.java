package com.dl.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
	@Value("${cloud.aws.credentials.access-key}")
	private String accesskey;
	
	
	@Value("${cloud.aws.credentials.secret-key}")
	private String SecretKey;
	
	@Value("${cloud.aws.region.static}")
	private String region;
	
    @Bean
	public S3Client s3client(){
    	
    	
		AwsBasicCredentials aws =AwsBasicCredentials.create(accesskey,SecretKey);
    	
    	return S3Client.builder()
    	        .region(Region.of(region))
    	        .credentialsProvider(StaticCredentialsProvider.create(aws))
    	        .build();
	
	}

}
