package com.webdriver.programs;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import core.util.SeleniumCore;

public class AlertHandlingDemo {
	/*
	 * public static void main(String[] args) throws NoAlertPresentException,
	 * InterruptedException { SeleniumCore core = new SeleniumCore();
	 * 
	 * core.setChromeDriverPath(); // System.setProperty("webdriver.chrome.driver",
	 * "Path_of_Chrome_Driver"); // mention dummy path or variable that // stores
	 * chrome driver path WebDriver driver = new ChromeDriver();
	 * driver.get("https://www.browserstack.com/users/sign_up");
	 * 
	 * driver.findElement(By.id("user_full_name")).sendKeys("username");
	 * driver.findElement(By.id("input-lg text user_email_ajax")).sendKeys(
	 * "username.xyz.net");
	 * driver.findElement(By.id("user_password")).sendKeys("Your_Password");
	 * driver.findElement(By.id("user_submit")).click(); }
	 */

	public static void main(String[] args) throws NoAlertPresentException, InterruptedException {
		SeleniumCore core = new SeleniumCore("chrome", "http://demo.guru99.com/test/delete_customer.php");
		// core.setChromeDriverPath();

		WebDriver driver = core.getDriver();
		// Alert Message handling

		driver.get("http://demo.guru99.com/test/delete_customer.php");

		driver.findElement(By.name("cusid")).sendKeys("53920");
		driver.findElement(By.name("submit")).submit();

		// Switching to Alert
		Alert alert = driver.switchTo().alert();

		// Capturing alert message.
		String alertMessage = driver.switchTo().alert().getText();

		// Displaying alert message
		System.out.println(alertMessage);
		Thread.sleep(5000);

		// Accepting alert
		alert.accept();
		
		core.hardWait(3);
		driver.findElement(By.name("submit")).submit();
		core.hardWait(1);
		alert.dismiss();
	}
}
