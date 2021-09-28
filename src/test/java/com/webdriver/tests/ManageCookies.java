package com.webdriver.tests;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import core.util.SeleniumCore;

public class ManageCookies {
	@Test
	public void testCookies() {
		SeleniumCore.setDriverPath();
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/delete_customer.php");
		Set<Cookie> setOfCookies = driver.manage().getCookies();
		int cookiesSizeBeforeDeletion = setOfCookies.size();
		
		driver.manage().deleteAllCookies();
		int cookiesSizeAfterDeletion = driver.manage().getCookies().size();

		Assert.assertTrue(cookiesSizeBeforeDeletion > cookiesSizeAfterDeletion, "Cookie deletion failed");
		
		driver.quit();
	}
}
