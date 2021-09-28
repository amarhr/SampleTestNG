package com.webdriver.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import core.util.SeleniumCore;

public class TestAccessMultipleWebsites {
	@Test
	public void testMultipleAddress() {
		// Run this command 
		// cd C:\Program Files (x86)\Google\Chrome\Application
		// chrome.exe --remote-debugging-port=9988 --user-data-dir=D:\work\data
		
		SeleniumCore.setDriverPath();
		WebDriver driver = new ChromeDriver();
		
		String script = "location.reload();";
		JavascriptExecutor js = (JavascriptExecutor)driver;
				
		driver.get("http://demo.guru99.com/");
		// js.executeScript(script);
		driver.navigate().back();
		driver.get("http://demo.guru99.com/");
		
		driver.get("http://facebook.com/");
		// js.executeScript(script);
		driver.navigate().back();
		driver.get("http://facebook.com/");
		
		driver.get("http://gmail.com/");
		// js.executeScript(script);
		driver.navigate().back();
		
		driver.get("http://demo.guru99.com/");
		driver.get("http://demo.guru99.com/");
		driver.get("http://demo.guru99.com/");
		driver.get("http://demo.guru99.com/");
		
		// driver.navigate().to("/intl/en-GB/gmail/about/");
		
		driver.manage().window().maximize();
		
		driver.navigate().to("http://demo.guru99.com/");
		driver.navigate().to("http://demo.guru99.com/");
		driver.navigate().to("http://demo.guru99.com/");
		driver.navigate().to("http://demo.guru99.com/");
	}
	
	@Test
	public void testNavigation() {

		// Run this command 
		// cd C:\Program Files (x86)\Google\Chrome\Application
		// chrome.exe --remote-debugging-port=9988 --user-data-dir=D:\work\data
		
		SeleniumCore.setDriverPath();
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://demo.guru99.com/");
		driver.navigate().back();
		driver.get("http://demo.guru99.com/");
		
		driver.get("http://facebook.com/");
		driver.navigate().back();
		driver.get("http://facebook.com/");
		
		driver.get("http://gmail.com/");
		driver.navigate().back();
		
		driver.get("http://demo.guru99.com/");
		driver.get("http://demo.guru99.com/");
		driver.get("http://demo.guru99.com/");
		driver.get("http://demo.guru99.com/");
		
		// driver.navigate().to("/intl/en-GB/gmail/about/");
		
		driver.manage().window().maximize();
		
		driver.navigate().to("http://demo.guru99.com/");
		driver.navigate().to("http://demo.guru99.com/");
		driver.navigate().to("http://demo.guru99.com/");
		driver.navigate().to("http://demo.guru99.com/");
	
	}
}
