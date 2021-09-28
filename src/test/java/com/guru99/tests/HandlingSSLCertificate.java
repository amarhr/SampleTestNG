package com.guru99.tests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import core.util.SeleniumCore;

public class HandlingSSLCertificate {
	public static void main(String[] args) {
		/*
		 * DesiredCapabilities DC = DesiredCapabilities.chrome();
		 * DC.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		 */
		
		// https://www.h2kinfosys.com/blog/chrome-options-desired-capabilities/
		
		/*
		 * start-maximized: Used to open Chrome in maximize mode incognito: Used to open
		 * Chrome in incognito mode headless: Used to open Chrome in headless mode
		 * disable-extensions: Used to disable existing extensions on Chrome browser
		 * disable-popup-blocking: Used to disable pop-ups displayed on Chrome browser
		 * make-default-browser: Used to make Chrome as default browser 
		 * version: Used to print chrome browser version 
		 * disable-infobars: Used to prevent Chrome from
		 * displaying the notification.
		 */

		SeleniumCore core = new SeleniumCore("chrome");
		core.setDriverPath();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("incognito");
		options.addArguments("ignore-certificate-errors");
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://cacert.org/");

		driver.close();

	}
}
