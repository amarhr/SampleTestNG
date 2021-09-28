package com.webdriver.tests;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.core.tests.TestBase;

import core.util.SeleniumCore;

// @Listeners(com.listeners.hooks.Listener.class)
public class TestWebdriverExceptions extends TestBase {
	static WebDriver driver;

	// @Test
	public void setBrowser() {
		SeleniumCore.setDriverPath();
		driver = new ChromeDriver();
	}
	
	@Test(enabled=false)
	public void setBrowserFunc() {
	}

	@Test
	public void testIllegalStateException() {
		try {
			WebDriver driver = new ChromeDriver();
		} catch (IllegalStateException exception) {
			System.out.println(exception.getMessage());
			Assert.assertTrue(exception.getMessage().contains(
					"The path to the driver executable must be set by the webdriver.chrome.driver system property"));
			// exception.printStackTrace();
		}
	}

	@Test
	public void testWebDriverException() {
		try {
			setBrowser();
			driver.quit();
			driver.get("http://gmail.com");
		} catch (NoSuchSessionException exception) {
			System.out.println(exception.getMessage());
			Assert.assertTrue(
					exception.getMessage().contains("Session ID is null. Using WebDriver after calling quit()"));
			// exception.printStackTrace();
		}
	}

	@Test
	public void testURL() {
		try {
			setBrowser();
			driver.get("gmail.com");
		} catch (InvalidArgumentException exception) {
			System.out.println(exception.getMessage());
			Assert.assertTrue(exception.getMessage().contains("invalid argument"));
			driver.quit();
			// exception.printStackTrace();
		}
	}
}
