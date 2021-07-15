package sample.guru99;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.guru99.pages.GuruHomePage;

import core.util.SeleniumCore;

public class TestScrolling {
	SeleniumCore core;
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		core = new SeleniumCore();
		driver = core.getDriver();
		driver.get("http://demo.guru99.com/test/guru99home/");
	}

	@Test
	public void TestScrollUsingJS() throws InterruptedException {
		GuruHomePage guruHomePage = PageFactory.initElements(driver, GuruHomePage.class);

		// Launch the application
		// To maximize the window. This code may not work with Selenium 3 jars. If
		// script fails you can remove the line below
		// This will scroll down the page by 1000 pixel vertical
		
		core.scroll(3, "DOWN"); // scrolls three times down

		Thread.sleep(500);
		core.scrollIntoView(guruHomePage.getSeleniumLink()); // Takes you to top

		Thread.sleep(500);
		core.scrollIntoView(guruHomePage.getBooksToRead()); // Takes you to down

		core.scroll(3, "UP"); // scrolls three times UP

		Thread.sleep(3000);
		core.moveToElement(guruHomePage.getBooksToRead());
		core.moveToElement(guruHomePage.getSeleniumLink());
	}

	@Test
	public void TestScrollUsingActions() throws InterruptedException {
		GuruHomePage guruHomePage = PageFactory.initElements(driver, GuruHomePage.class);

		Thread.sleep(3000);
		core.moveToElement(guruHomePage.getBooksToRead());
		core.moveToElement(guruHomePage.getSeleniumLink());
	}
	
	@AfterMethod
	public void tearDown() {
		core.quit();
	}
}
