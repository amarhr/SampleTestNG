package com.webdriver.tests;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import core.util.SeleniumCore;

public class TestWindowHandle {

	public static void main(String[] args) throws InterruptedException {
		// SeleniumCore core = new SeleniumCore("chrome", "http://demo.guru99.com/popup.php");
		SeleniumCore.setDriverPath();
		WebDriver driver = new ChromeDriver();

		// Launching the site.
		String MainWindow = driver.getWindowHandle();

		int i = 0;
		while (i++ < 10) {
			driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();
			// core.hardWait(1);
			Thread.sleep(1000);
			driver.switchTo().window(MainWindow); // By default Im on the Main window - Not required.
		}

		// To handle all new opened window.
		Set<String> allWindowNames = driver.getWindowHandles();
		Iterator<String> windowName = allWindowNames.iterator();

		while (windowName.hasNext()) {
			String ChildWindow = windowName.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				// Switching to Child window
				driver.switchTo().window(ChildWindow);
				driver.findElement(By.name("emailid")).sendKeys("amarnathhr86@gmail.com");
				driver.findElement(By.name("btnLogin")).click();

				// Closing the Child Window.
				driver.close();
			}
		}
		// Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow);
		driver.close();
	}
}
