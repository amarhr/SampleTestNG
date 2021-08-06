package com.grid.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestGrid {
	
	@Test
	@Parameters({"browserType", "grid"})
	public void testRunInstanceOnGrid(String browserType, String grid) throws MalformedURLException {
		DesiredCapabilities DC = new DesiredCapabilities();
		DC.setBrowserName(browserType);
		//DC.setPlatform(Platform.LINUX);
		WebDriver driver = new RemoteWebDriver(new URL(grid), DC);
		driver.get("http://rediff.com");
		driver.quit();
	}
}