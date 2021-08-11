package com.guru99.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.guru99.pages.GuruHomePage;

import core.util.SeleniumCore;

public class TestActions {
	WebDriver driver;
	SeleniumCore core;
	GuruHomePage guruHomePage;

	public TestActions() {
		SeleniumCore core = new SeleniumCore("Chrome", null);
		driver = core.getDriver();

		driver.get("http://demo.guru99.com/test/guru99home/");

		guruHomePage = PageFactory.initElements(driver, GuruHomePage.class);
	}

	@Test
	public void TestContextClickAction(String[] args) throws InterruptedException {
		TestActions testActions = new TestActions();
		core.rightClickAndOpenInNewTAB(guruHomePage.getEthicalHacking());
	}
}
