package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


// TODO: Auto-generated Javadoc
/**
 * The Class SystemNameService.
 */
@Service
public class SystemNameService {

	@Value("${testvarkey:defaultVALUE}")
	private String testKey;

	/**
	 * Gets the system name.
	 *
	 * @return the system name
	 */
	public String getSystemName() {
		System.out.println("KEYVALUE = " + testKey);
		return "Custom System Name";
	}
}
