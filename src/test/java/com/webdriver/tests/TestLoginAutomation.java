package com.webdriver.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import core.util.SeleniumCore;

public class TestLoginAutomation {
	@Test
	public void login() {
		SeleniumCore.setDriverPath();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		driver.get("https://www.browserstack.com/users/sign_in");
		
		//a[contains(text(),'Sign in with Google')]
		WebElement signInWithGoogle = driver.findElement(By.xpath("//a[contains(text(),'Sign in with Google')]"));
		signInWithGoogle.click();
		
		WebElement emailLink = driver.findElement(By.xpath("//div[contains(text(),'amarnathhr86@gmail.com')]"));
		emailLink.click();	
				
		String actualUrl = "https://live.browserstack.com/dashboard";
		String expectedUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl);
	}
}
