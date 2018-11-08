package com.spring.webflux;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.webflux.selenium.ChromeEnvironment;
import com.spring.webflux.selenium.CustomChromeDriver;

@Configuration
public class ApplicationConfiguration {

	@Value("${chrome.headless:true}")
	private boolean chromeHeadless;

	@Value("${http.proxy.enabled:false}")
	private boolean httpProxyEnabled;

	@Value("${http.proxy.host:localhost}")
	private String httpProxyHost;

	@Value("${http.proxy.port:8888}")
	private int httpProxyPort;

	@Bean
	public CustomChromeDriver customChromeDriver(ChromeEnvironment chromeEnvironment) throws Exception {
		ChromeOptions chromeOptions = new ChromeOptions();
		if (chromeHeadless) {
			chromeOptions.addArguments("--headless");
		}
		if (httpProxyEnabled) {
			chromeOptions.addArguments("--proxy-server=" + httpProxyHost + ":" + httpProxyPort);
		}

		return new CustomChromeDriver(chromeEnvironment, chromeOptions);
	}
}
