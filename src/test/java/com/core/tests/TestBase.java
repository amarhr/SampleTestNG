package com.core.tests;

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

		core = new SeleniumCore();
		driver = core.getDriver();

		/*
		 * System.setProperty("webdriver.chrome.driver", "D:\\JARS\\chromedriver.exe");
		 * System.setProperty("webdriver.gecko.driver", "D:\\JARS\\geckodriver.exe");
		 * System.setProperty("webdriver.ie.driver", "D:\\JARS\\IEDriverServer.exe");
		 * 
		 * DesiredCapabilities caps = null;
		 * 
		 * switch (browserType) { case "Firefox": caps = DesiredCapabilities.firefox();
		 * caps.setCapability("ignoreZoomSetting", true); driver = new FirefoxDriver();
		 * break; case "Chrome": caps = DesiredCapabilities.chrome();
		 * caps.setCapability("ignoreZoomSetting", true); driver = new ChromeDriver();
		 * break; case "IE": caps = DesiredCapabilities.internetExplorer();
		 * caps.setCapability("ignoreZoomSetting", true); driver = new
		 * InternetExplorerDriver(caps); break; }
		 * 
		 * driver.manage().deleteAllCookies(); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 */
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is openBrowser", ExtentColor.GREEN));
	}
}
