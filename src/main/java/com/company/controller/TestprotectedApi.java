package com.company.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestprotectedApi {
	
	
	
	

	    @GetMapping("/hello")
	    public String hello() {
	        return "Hello Secure World";
	    }
	}



