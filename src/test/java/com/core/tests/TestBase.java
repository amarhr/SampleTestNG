package com.core.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import core.util.SeleniumCore;

public class TestBase {
	protected WebDriver driver;
	protected ExtentHtmlReporter htmlReporter;
	protected ExtentReports extent;
	protected ExtentTest logger;
	protected SeleniumCore core;
	private static final Logger LOGGER = LogManager.getLogger(SeleniumCore.class.getName());

	@BeforeSuite
	public void beforeSuite() {
		ExtentHtmlReporter htmlReporter;
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Amarnath H R");

		htmlReporter.config().setDocumentTitle("Title of the Report Comes here");
		htmlReporter.config().setReportName("Name of the Report Comes here");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	@BeforeMethod
	public void openBrowser() {

		logger = extent.createTest("openBrowser");
		String browserType = "Chrome";

		core = new SeleniumCore(browserType);
		driver = core.getDriver();

		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is openBrowser", ExtentColor.GREEN));
	}
}
