package com.rahulshetty.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rs.pages.RSAcademyHomePage;

import core.util.SeleniumCore;

public class TestRSAcademy {
	SeleniumCore core;
	WebDriver driver;
	RSAcademyHomePage rsHomePage;

	@BeforeTest
	@Parameters({ "browserType", "grid" })
	public void setUp(String browserType, String grid) {
		core = new SeleniumCore("Chromeheadless", grid);
		driver = core.getDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

		rsHomePage = new RSAcademyHomePage(driver);
	}

	@Test
	public void testCurrencySelection() {
		rsHomePage.selectCurrencyByIndex(3);
		rsHomePage.verifySelectedCurrencyIs("USD");

		core.hardWait(2);
		rsHomePage.selectCurrencyByVisibleText("Select");
		// rsHomePage.getCurrencyDropDown().deselectByIndex(3);
		// java.lang.UnsupportedOperationException: You may only deselect options of a
		// multi-select
		core.hardWait(2);

		rsHomePage.selectCurrencyByVisibleText("INR");
		rsHomePage.verifySelectedCurrencyIs("INR");

		core.hardWait(2);
		rsHomePage.selectCurrencyByVisibleText("Select");
		core.hardWait(2);

		rsHomePage.selectCurrencyByValue("AED");
		rsHomePage.verifySelectedCurrencyIs("AED");

		rsHomePage.selectCurrencyByVisibleText("Select");

		core.hardWait(2);
	}

	@Test
	public void testPassengersSelection() {
		int numberOfAdults = 5;
		rsHomePage.addAdults(numberOfAdults);
		Assert.assertEquals(rsHomePage.getPassengeInfo().getText(), numberOfAdults + " Adult");
		System.out.println(rsHomePage.getPassengeInfo().getText());
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}