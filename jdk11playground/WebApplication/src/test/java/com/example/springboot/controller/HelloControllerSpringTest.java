package com.example.springboot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.springboot.MockConfig;
import com.example.springboot.service.SystemNameService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MockConfig.class})
@WebAppConfiguration
public class HelloControllerSpringTest {

	@Autowired
	SystemNameService systemNameService;
	
	@Test
	public void checkAop() {
		systemNameService.getSystemName();
	}
}
