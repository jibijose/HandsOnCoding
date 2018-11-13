package com.example.springboot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.springboot.service.SystemNameService;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class SystemNameServiceTest {

	@InjectMocks
	private SystemNameService systemNameService;
	
	@Test
	public void checkSystemName() {
		Assert.assertEquals("Custom System Name", systemNameService.getSystemName());
	}
}
