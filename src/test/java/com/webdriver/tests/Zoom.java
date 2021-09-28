package com.webdriver.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import core.util.SeleniumCore;

public class Zoom {
	public static void main(String[] args) throws InterruptedException {
		SeleniumCore.setDriverPath();

		WebDriver driver = new ChromeDriver();
		// Alert Message handling

		driver.get("http://demo.guru99.com/test/delete_customer.php");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.body.style['-webkit-transform'] = \"scale(2.0)\";");
		Thread.sleep(3000);
		js.executeScript("document.body.style['-webkit-transform'] = \"scale(1.0)\";");
		Thread.sleep(3000);
		
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	// document.body.style['-webkit-transform'] = "scale(2.0)";
	// document.body.style['-webkit-transform'] = "scale(1.0)";
}