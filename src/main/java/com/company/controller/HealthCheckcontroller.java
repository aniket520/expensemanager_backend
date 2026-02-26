package com.company.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckcontroller {
	
	    @GetMapping("/")
	    public String health() {
	        return "Expense Manager Backend is Running";
	    }
	}



