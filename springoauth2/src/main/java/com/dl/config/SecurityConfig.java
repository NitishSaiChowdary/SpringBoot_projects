package com.dl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import com.dl.filter.JwtFilter;
import com.dl.service.MyUserDetailsService;

import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
	  //  .cors(cors -> cors.configurationSource(corsConfigurationSource()))
		.csrf(customizer -> customizer.disable())
		.authorizeHttpRequests(request ->request
		.requestMatchers("/auth/register","/auth/login","/oauth2/**")
		.permitAll()
		.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(session ->
		session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
		//.oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/home", true))
		//.oauth2Login(Customizer.withDefaults())
        .oauth2Login(oauth2 -> oauth2
                .successHandler(oAuth2AuthenticationSuccessHandler()) // Force redirect to /home
            )
		.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
//		.formLogin(Customizer.withDefaults())
        .formLogin(form -> form
                .defaultSuccessUrl("/home", true) // Redirects to /home after login
                .permitAll()
            )
		.build();		
	}
	
//    @Bean
//    public AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
//        return (request, response, authentication) -> {
//            System.out.println("OAuth2 Login Successful: " + authentication.getName());
//            response.sendRedirect("/home"); // Always redirect to /home after OAuth2 login
//        };
//    }
	
	@Bean
	public AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
	    return (request, response, authentication) -> {
	        Map<String, Object> attributes = ((OAuth2User) authentication.getPrincipal()).getAttributes();
	        
	        // Get the email from the OAuth2 response
	        String email = (String) attributes.get("email");

	        // Check if email is from allowed domain
	        if (email != null && email.endsWith("@gitam.in")) {
	           // System.out.println("Allowed Email: " + email);
	            response.sendRedirect("/home"); // Redirect to home on successful login
	        } else {
	            //System.out.println("Unauthorized Email: " + email);
	            response.sendRedirect("/access-denied"); // Redirect to access denied page
	        }
	    };
	}

	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(myUserDetailsService);

		return provider;
		
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
		
	}
	
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOriginPatterns(List.of("*"));  // Change to your frontend URL
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

//	
//    @Bean
//	public UserDetailsManager userDetailsManager() {
//		
//		UserDetails user1=User
//				.withDefaultPasswordEncoder()
//				.username("Sai")
//				. password("Sai2003")
//				.roles("ADMIN")
//				.build();
//		
//		
//		return new InMemoryUserDetailsManager(user1);
//		
//	}

}
