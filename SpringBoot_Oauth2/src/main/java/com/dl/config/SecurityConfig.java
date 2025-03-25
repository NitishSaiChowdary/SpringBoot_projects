package com.dl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
@Configuration
@EnableWebSecurity
@CrossOrigin
public class SecurityConfig {
	
	


    @Value("${frontend.url}")  // Fetch URL from properties file
    private String frontendUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(auth -> auth
    			.anyRequest().authenticated())
//            .oauth2Login(Customizer.withDefaults());
//    	.oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/client", true));
        .oauth2Login(oauth2 -> oauth2
                .successHandler(customSuccessHandler()));
//        .oauth2Login(oauth2 -> oauth2
//                .loginPage("/oauth2/authorization")  // Specify the login page for OAuth2 providers
//                .defaultSuccessUrl("/home", true)  // Redirect after login success
//                .failureUrl("/login?error=true")  // Handle login failure
//                .authorizationEndpoint(authorization -> authorization
//                    .baseUri("/oauth2/authorization")  // Base URI for authorization
//                )
//            );
       return http.build();
    }
    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler(frontendUrl); // Use configurable URL
    
    }
}
