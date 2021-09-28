package com.webdriver.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;

import core.util.SeleniumCore;

public class TestWebstorage {
	public static void main(String[] args) {
		SeleniumCore.setDriverPath();
		WebDriver driver = new ChromeDriver();
		WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
		// using local storage
		LocalStorage localStorage = webStorage.getLocalStorage();
		localStorage.setItem("name", "chercher tech");
		localStorage.getItem("name");
		localStorage.removeItem("name");
		localStorage.size();
		localStorage.keySet();
		localStorage.clear();

		// using session storage
		SessionStorage sessionStorage = webStorage.getSessionStorage();
		sessionStorage.setItem("name", "chercher tech");
		sessionStorage.getItem("name");
		sessionStorage.removeItem("name");
		sessionStorage.size();
		sessionStorage.keySet();
		sessionStorage.clear();
    }
}