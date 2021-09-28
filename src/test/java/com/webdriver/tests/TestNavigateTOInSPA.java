package com.webdriver.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNavigateTOInSPA {
	@Test
	public void testNavigateToInSPA() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get("http://facebook.com");
		Thread.sleep(2000);
		driver.navigate().to("http://facebook.com");
		Thread.sleep(2000);
		driver.navigate().to("http://facebook.com");
	}
}