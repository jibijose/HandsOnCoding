package com.spring.webflux.selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomChromeDriver extends ChromeDriver {

	private static Logger LOGGER = LoggerFactory.getLogger(CustomChromeDriver.class);

//	private ChromeEnvironment chromeEnvironment;

	public CustomChromeDriver(ChromeEnvironment chromeEnvironment, ChromeOptions chromeOptions) {
		super(chromeOptions);
//		this.chromeEnvironment = chromeEnvironment;
		manage().window().maximize();
		LOGGER.debug("Initialized bean CustomChromeDriver");
	}

}
