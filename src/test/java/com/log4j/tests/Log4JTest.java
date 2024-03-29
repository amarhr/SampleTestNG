package com.log4j.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JTest {
	private static final Logger LOGGER = LogManager.getLogger(Log4JTest.class.getName());

	public static void main(String[] args) {
		LOGGER.debug("Debug Message Logged !!!");
		LOGGER.info("Info Message Logged !!!");
		LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
	}
}
