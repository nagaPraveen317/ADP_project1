package com.webage.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ServiceAPI {

	
	
	@GetMapping
	public String checkStatus() {
		return "Authentication service is running up good";
	}
	

}