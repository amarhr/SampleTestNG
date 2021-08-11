package com.grid.tests;

import java.net.MalformedURLException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.core.tests.TestBase;

import core.util.SeleniumCore;

public class TestGrid extends TestBase{
	
	@BeforeTest
	@Parameters({ "browserType", "grid" })
	public void setUp(String browserType, String url) {
		core = new SeleniumCore(browserType, url);
		driver = core.getDriver();
	}
	
	@Test(invocationCount = 2)
	@Parameters({"browserType", "grid"})
	public void testRunInstanceOnGrid(String browserType, String grid) throws MalformedURLException {
		driver.get("http://rediff.com");
		core.hardWait(2);
		core.takeScreenShot("RunInstanceOnGrid");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}