package com.spring.webflux.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

	private static Logger LOGGER = LoggerFactory.getLogger(Util.class);

	public static boolean checkNullOrBlank(String str) {
		if (str == null) {
			return true;
		}

		if (str.trim().equals("")) {
			return true;
		}

		return false;
	}

	public static void sleep(int seconds) {
		try {
			LOGGER.debug("Sleeping {} seconds", seconds);
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException interruptedException) {
			LOGGER.warn("Sleep attempt interrupted", interruptedException);
			throw new RuntimeException("Sleep attempt interrupted", interruptedException);
		}
	}

	public static void sleepMillis(int milliSeconds) {
		try {
			LOGGER.debug("Sleeping {} milli seconds", milliSeconds);
			Thread.sleep(milliSeconds);
		} catch (InterruptedException interruptedException) {
			LOGGER.warn("Sleep milli attempt interrupted", interruptedException);
			throw new RuntimeException("Sleep milli attempt interrupted", interruptedException);
		}
	}

}
