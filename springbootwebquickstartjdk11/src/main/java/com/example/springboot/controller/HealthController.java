package com.example.springboot.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.service.SystemNameService;

@RestController
public class HealthController {

	@Autowired
	public SystemNameService systemNameService;

	@RequestMapping("/customisup")
	public final String isServiceUp() {

		String systemName = systemNameService.getSystemName();
		return "System " + systemName + " is up. Time is " + new Date();
	}

	@RequestMapping("/health")
	public String health() {
		return "Health from Spring Boot!";
	}

}