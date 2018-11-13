package com.example.springboot.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.springboot.controller.HealthController;
import com.example.springboot.service.SystemNameService;

@RunWith(MockitoJUnitRunner.class)
public class HealthControllerMockTest {

	private MockMvc mvc;

	@Mock
	private SystemNameService systemServiceName;

	@InjectMocks
	private HealthController healthController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new HealthController()).build();
	}

	@Test
	public void getIsUp() throws Exception {
		when(systemServiceName.getSystemName()).thenReturn("Mock System Name");
		
		healthController.isServiceUp();
	}
	
	@Test
	public void getHealth() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/health").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("Health from Spring Boot!")));
	}

}