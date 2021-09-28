package com.webdriver.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import core.util.SeleniumCore;

public class TestOnAlreadyOpenedBrowser {
	@Test
	public void testDebuggingAddress() {
		// Run this command 
		// cd C:\Program Files (x86)\Google\Chrome\Application
		// chrome.exe --remote-debugging-port=9988 --user-data-dir=D:\work\data
		
		SeleniumCore.setDriverPath();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "localhost:9988");
		WebDriver driver = new ChromeDriver(options);

		driver.get("http://demo.guru99.com/");
		driver.get("http://facebook.com/");
		driver.get("http://gmail.com/");
		driver.manage().window().maximize();
	}
}
