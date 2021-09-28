package com.webdriver.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import core.util.SeleniumCore;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAcceptSSL {
	
	/*
	 * 
	 * start-maximized: 	Used to open Chrome in maximize mode
	 * incognito: 	Used to open chrome in incognito mode 
	 * headless: 	Used to open Chrome in headless mode
	 * disable-extensions: 	Used to disable existing extensions on Chrome browser
	 * disable-popup-blocking: 	Used to disable pop-ups displayed on Chrome browser
	 * make-default-browser: 	Used to make Chrome as default browser 
	 * version: 	Used to print chrome browser version
	 * disable-infobars: 	Used to prevent Chrome from displaying the notification.
	 * ignore-certificate-errors: 	ignores the certificate errors
	 * 
	 */
	
	public DesiredCapabilities getDCToAcceptSSLCerts(DesiredCapabilities dc) {
		// Desired capabilities=
		// general chrome profile
		// ch.acceptInsecureCerts();
		dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		return dc;
	}

	@Test
	public void testSSL() {
		// SSl certificates
		// Belows to your local browser
		ChromeOptions options = new ChromeOptions();
		options.merge(getDCToAcceptSSLCerts(DesiredCapabilities.chrome()));

		// System.setProperty("webdriver.chrome.driver", "");
		SeleniumCore.setDriverPath();
		WebDriver driver = new ChromeDriver(options);
		
		driver.get("https://expired.badssl.com/");
		driver.get("https://cacert.org/");
		driver.quit();
	}
	
	@Test
	public void testSSLAtOptionsLevel() throws InterruptedException {
		ChromeOptions c = new ChromeOptions();
		c.addArguments("ignore-certificate-errors");
		
		SeleniumCore.setDriverPath();
		WebDriver driver = new ChromeDriver(c);
		
		driver.get("https://expired.badssl.com/");
		Thread.sleep(5000);
		driver.get("https://cacert.org/");
		Thread.sleep(5000);
		driver.quit();
	}
	
	@Test
	public void testFFAcceptingSSL() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
		options.merge(getDCToAcceptSSLCerts(DesiredCapabilities.firefox()));
		
		WebDriver driver = new FirefoxDriver(options);
		
		driver.navigate().to("https://expired.badssl.com/");
		driver.navigate().to("https://cacert.org/");
		driver.quit();
	}
}
