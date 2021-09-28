package com.webdriver.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.SessionStorage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import core.util.SeleniumCore;
import core.util.SessionStorageJS;

public class TestSessionStorage {
	@Test
	public void test() {
		SeleniumCore.setDriverPath();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.gmail.com");
		
		SessionStorageJS sessionsStorageJs = new SessionStorageJS(driver);
		SessionStorage sessionStorage = sessionsStorageJs.getSessionStorage();
		
		sessionStorage.setItem("name", "chercher tech");
		String value = sessionStorage.getItem("name");
		
		Assert.assertTrue(value.equals("chercher tech"), "Key value mismatch");
		SoftAssert softAssert = new SoftAssert();
		
		sessionStorage.removeItem("name");
		sessionStorage.size();
		sessionStorage.keySet();
		sessionStorage.clear();
		
		driver.quit();
	};
}
