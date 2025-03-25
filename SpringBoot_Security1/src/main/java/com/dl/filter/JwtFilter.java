package com.dl.filter;

import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dl.service.JwtService;
import com.dl.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	ApplicationContext context;
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	        response.setStatus(HttpServletResponse.SC_OK);
	        return;
	    }
		  String authHeader =request.getHeader("Authorization");
		  String token =null;
		  String username =null;
		  
		  if (authHeader !=null && authHeader.startsWith("Bearer ")) {
			  token = authHeader.substring(7);
			  username = jwtService.extractUserName(token);
		  }

		  if (username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			 
			  UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
			  if (jwtService.validateToken(token,userDetails)) {
				  UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
				  
				  
			  }
			  
		  }
		  
		 filterChain. doFilter(request, response);
	}

}
