package com.core.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import core.util.SeleniumCore;

@Listeners(com.listeners.hooks.Listener.class)
public class TestBase {
	protected static WebDriver driver;

	protected ExtentHtmlReporter htmlReporter;
	protected ExtentReports exReports;
	protected ExtentTest exTest;

	protected SeleniumCore core;
	public static final Logger LOGGER = LogManager.getLogger(TestBase.class.getName());

	public Properties props;
	
	@BeforeSuite
	public void beforeSuite() {

		ExtentHtmlReporter htmlReporter;
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-extent/STMExtentReport.html");
		
		exReports = new ExtentReports();		
		exReports.attachReporter(htmlReporter);
		exReports.setSystemInfo("Host Name", "SoftwareTestingMaterial");
		exReports.setSystemInfo("Environment", "Automation Testing");
		exReports.setSystemInfo("User Name", "Amarnath H R");

		htmlReporter.config().setDocumentTitle("Title of the Report Comes here");
		htmlReporter.config().setReportName("Name of the Report Comes here");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);

		props = new Properties();
		
		FileInputStream fip;
		try {
			fip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\" + "data.properties");
			props.load(fip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @BeforeMethod public void openBrowser() { String browserType = "Chrome"; core
	 * = new SeleniumCore(browserType); driver = core.getDriver();
	 * 
	 * // exTest.log(Status.PASS,
	 * MarkupHelper.createLabel("Test Case Passed is openBrowser",
	 * ExtentColor.GREEN)); }
	 */
	
	@AfterSuite
	public void tearDown() {
		/*
		 * try { driver.quit(); } catch (WebDriverException e) {
		 * LOGGER.debug("Exception in quitting the browser");
		 * LOGGER.debug(e.getMessage()); }
		 */
		
	}
}