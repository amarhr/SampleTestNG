package com.webdriver.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.core.tests.TestBase;

import core.util.SeleniumCore;

@Listeners(com.listeners.hooks.Listener.class)
public class TestBrokenLinks extends TestBase{
	private static WebDriver driver = null;
	static String homePage = "https://rahulshettyacademy.com/AutomationPractice"; // "http://www.zlti.com";
	//https://rahulshettyacademy.com/AutomationPractice
	SoftAssert soft = new SoftAssert();	

	@Test
	public void TestLinks() {
		String url = "";
		SeleniumCore.setDriverPath();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(homePage);

		List<WebElement> links = driver.findElements(By.tagName("a"));
		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			verifiBrokenLink(url);
			// TestBrokenLinks.verifyLinkActive(url);
		}
		soft.assertAll();
		driver.quit();
	}

	public void verifyLinkActive(String linkUrl) {
		// The reason, we get above error is that JDK is bundled with a lot of trusted
		// Certificate Authority(CA) certificates into a file called 'cacerts' but this
		// file has no clue of our self-signed certificate. ... To fix the above error,
		// all we need is to import the self-signed certificate into the cacerts file.

		try {
			URL url = new URL(linkUrl);
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(3000);
			httpURLConnect.setRequestMethod("HEAD");
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) { // This check is for 404 code
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {
			System.out.println("Some exception");
		}

	}

	public void verifiBrokenLink(String url) {
		HttpURLConnection huc = null;
		URL urlObject = null;
		int respCode = 200;

		if (url == null || url.isEmpty()) {
			// System.out.println("URL is either not configured for anchor tag or it is
			// empty");
			return;
		}
		if (url.startsWith(homePage)) {
			//System.out.println("URL belongs to another domain, skipping it.");
			//return;
		}
		try {
			urlObject = new URL(url);
			huc = (HttpURLConnection) urlObject.openConnection();
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();
			soft.assertFalse(respCode >= 400, url + " is BROKEN");
			soft.assertTrue(respCode == 200, String.valueOf(respCode));
			/*
			 * } else { System.out.println(url + " is CODE " + respCode); }
			 */
		} catch (MalformedURLException e) {
			System.out.println(url + " is MALFORMED");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassCastException exp) {
			System.out.println(url + " Cast issues is BROKEN");
		}
	}
}