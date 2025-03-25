package com.dl.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/home")
	public String greet() {
		return "welcom to aurz ";
	}
}
