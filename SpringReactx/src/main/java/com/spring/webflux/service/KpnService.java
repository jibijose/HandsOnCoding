package com.spring.webflux.service;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.webflux.model.KpnBus;
import com.spring.webflux.model.KpnStatus;

@Service
public class KpnService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KpnService.class);

	@Autowired
	private WebDriver webDriver;

	public Optional<KpnStatus> fetchKpnStatus() {

		try {
			webDriver.get("http://www.kpntravels.in");
			LOGGER.info("Title : {}", webDriver.getTitle());

			webDriver.findElement(By.id("WebContent_ddlFrom")).sendKeys("Ernakulam");
			webDriver.findElement(By.id("WebContent_ddlTo")).sendKeys("Bangalore");
			WebElement dateWebElement = webDriver.findElement(By.id("WebContent_txtTravelDate"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].removeAttribute('readonly','readonly')",
					dateWebElement);
			dateWebElement.sendKeys("Thursday, 08 November - 2018");
			webDriver.findElement(By.xpath("//input[@value='Plan Trip']")).click();
			LOGGER.info("Title : {}", webDriver.getTitle());

			String busTableId = "//table[@id='WebContent_dtgRoutes']";
			List<WebElement> busesWebElements = webDriver.findElements(By.xpath(busTableId + "/tbody/tr"));

			KpnStatus kpnStatus = new KpnStatus();

			for (int i = 1; i < busesWebElements.size(); i++) {
				WebElement busWebElement = busesWebElements.get(i);
				List<WebElement> busDetailsWebElements = busWebElement.findElements(By.xpath("./td"));
				KpnBus kpnBus = new KpnBus(busDetailsWebElements.get(0).getText(),
						busDetailsWebElements.get(1).getText(), busDetailsWebElements.get(2).getText(),
						busDetailsWebElements.get(3).getText(), busDetailsWebElements.get(4).getText(),
						busDetailsWebElements.get(5).getText());
				if (busWebElement.findElements(By.xpath("./td/input[@value='View Seats']")).size() > 0) {
					kpnBus.setStatus("available");
				} else {
					kpnBus.setStatus("full");
				}
				kpnStatus.addKpnBus(kpnBus);
			}

			kpnStatus.setStatus("ok");
			return Optional.of(kpnStatus);
		} catch (Exception exception) {
			LOGGER.error("Error occured while fetching kpn status", exception);
		}

		return Optional.empty();
	}
}
