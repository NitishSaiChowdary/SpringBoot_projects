package com.dl.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Value("${frontend.url}")
    private String frontendUrl;

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        //.cors(Customizer.withDefaults()) 
        .authorizeHttpRequests(request ->request
        		.requestMatchers("/saml2/**").permitAll().anyRequest().authenticated())
        .saml2Login((saml2 -> saml2
        		.loginProcessingUrl("/login/saml2/sso/Saml2spring")
//        		.defaultSuccessUrl("/home", true)
        		.successHandler(customSuccessHandler())
        		.failureUrl("/")))
        .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true));
        return http.build();
    }
    
    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler(frontendUrl); // Use configurable URL
    
    }
    
}
