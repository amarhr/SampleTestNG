package com.guru99.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.core.tests.TestBase;
import com.guru99.pages.GuruHomePage;

import core.util.SeleniumCore;

public class TestScrolling extends TestBase{
	public static final Logger LOGGER = LogManager.getLogger(TestScrolling.class.getName());

	@BeforeMethod
	public void setUp() {
		String browserName = props.getProperty("browserName");
		core = new SeleniumCore(browserName, null);
		driver = core.getDriver();
		driver.get("http://demo.guru99.com/test/guru99home/");
	}

	@Test
	public void TestScrollUsingJS() throws InterruptedException {
		GuruHomePage guruHomePage = new GuruHomePage(core);

		// Launch the application
		// To maximize the window. This code may not work with Selenium 3 jars. If
		// script fails you can remove the line below
		// This will scroll down the page by 1000 pixel vertical
		
		core.scrollByPage(3, "DOWN"); // scrolls page down three times

		core.scrollIntoView(guruHomePage.getSeleniumLink()); // Takes you to top

		core.hardWait(3);;
		core.scrollIntoView(guruHomePage.getBooksToRead()); // Takes you to down

		core.scrollByPage(3, "UP"); // scrolls three times UP

		core.hardWait(3);		
		guruHomePage.mouseHoverOnBooksToRead();
		guruHomePage.mouseHoverOnSeleniumLink();
	}

	@Test
	public void TestScrollUsingActions(){
		GuruHomePage guruHomePage = new GuruHomePage(core);
		core.hardWait(3);		
		guruHomePage.mouseHoverOnBooksToRead();
		guruHomePage.mouseHoverOnSeleniumLink();
	}
	
	@AfterMethod
	public void tearDown() {
		LOGGER.debug("tear down");
		core.quit();
	}
}
