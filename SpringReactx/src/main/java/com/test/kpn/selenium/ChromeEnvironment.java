package com.test.kpn.selenium;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class ChromeEnvironment {

	@PostConstruct
	public void postConstruct() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
	}

}
