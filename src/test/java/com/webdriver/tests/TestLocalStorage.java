package com.webdriver.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.testng.annotations.Test;

import core.util.LocalStorageJS;
import core.util.SeleniumCore;

public class TestLocalStorage {
	@Test
	public static void testLocalStorage() {
		SeleniumCore.setDriverPath();
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		
		
		LocalStorageJS localStorageJS = new LocalStorageJS(driver);
		localStorageJS.setItemInLocalStorage("a", "aarav");
		// local.clear();
		LocalStorage localStorage = localStorageJS.getLocalStorage();
		localStorage.setItem("hr", "amar");
		
		for (String key : localStorage.keySet()) {
			System.out.println("Key :" + key + " = " + localStorage.getItem(key));
		}
		
		System.out.println("Storage length using JS: " + localStorageJS.getLocalStorageLength());
		System.out.println("Storage length using driver: " + localStorage.keySet().size());
		
		localStorage.removeItem("hr");
		localStorageJS.removeItemFromLocalStorage("a");
		
		for (String key : localStorage.keySet()) {
			System.out.println("Key :" + key + " = " + localStorage.getItem(key));
		}
		
		System.out.println("Storage length using JS: " + localStorageJS.getLocalStorageLength());
		System.out.println("Storage length using driver: " + localStorage.keySet().size());
		driver.quit();
	}
}
