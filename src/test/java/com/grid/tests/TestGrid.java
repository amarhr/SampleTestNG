package com.grid.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestGrid {
	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities DC = new DesiredCapabilities();
		DC.setBrowserName("chrome");
		DC.setPlatform(Platform.WINDOWS);
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.2:4444/wd/hub"), DC);
		driver.get("http://rediff.com");
		driver.quit();
	}
}