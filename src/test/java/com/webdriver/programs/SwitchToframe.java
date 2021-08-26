package com.webdriver.programs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.util.SeleniumCore;

public class SwitchToframe {
	public static void main(String[] args) throws NoSuchElementException {
		SeleniumCore core = new SeleniumCore("chrome", "http://demo.guru99.com/test/guru99home/");
		WebDriver driver = core.getDriver();
		// driver.get("http://demo.guru99.com/test/guru99home/");
		driver.manage().window().maximize();
		int size = driver.findElements(By.tagName("iframe")).size();

		printIframeNames(driver);

		for (int i = 0; i < size; i++) {
			driver.switchTo().frame(i);
			int total = driver.findElements(By.tagName("iframes")).size();
			System.out.println("Inner frames in frame " + (i + 1) + " - " + total);
			driver.switchTo().defaultContent();
			// switching back from the iframe
		}

		// Commented the code for finding the index of the element
		driver.switchTo().frame(0); // Switching to the frame
		System.out.println("********We are switched to the iframe*******");
		// driver.findElement(By.xpath("html/body/a/img")).click();

		// Clicking the element in line with Advertisement
		driver.switchTo().parentFrame();
		System.out.println("*********We are done***************");

		driver.switchTo().frame(1); // Switching to the frame
		System.out.println("********We are switched to the iframe*******");
		driver.switchTo().defaultContent();
		System.out.println("*********We are done***************");
	}

	public static void printIframeNames(WebDriver driver) {
		final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		for (WebElement iframe : iframes) {
			System.out.println("Frame: " + iframe.getAttribute("name") + " : " + iframe.getAttribute("id"));
			/*
			 * if (iframe.getAttribute("id").equals(id)) { // TODO your stuff. }
			 */
		}
	}
}
