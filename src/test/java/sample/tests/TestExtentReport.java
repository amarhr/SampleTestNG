package sample.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestExtentReport {
	private WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	private ExtentReports extent;
	ExtentTest logger;

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

	@Test(dependsOnMethods = "openBrowser", enabled = true)
	public void testScroll() throws InterruptedException {
		logger = extent.createTest("testScroll");
		driver.get("https://www.flipkart.com/");

		WebElement logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
		logon.sendKeys(Keys.ESCAPE);
		try {
			// driver.wait(1000);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");

		String title = js.executeScript("return document.title;").toString();
		System.out.println(driver.getTitle());
		Assert.assertEquals(title,
				"Online Shopping Site for Mobiles, Fashion, Books, Electronics, Home Appliances and More");

		try {
			// driver.wait(1000);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");

		try {
			// driver.wait(1000);
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement searchBox = driver.findElement(By.xpath("//a[@title='Electronics']"));
		js.executeScript("arguments[0].scrollIntoView(true);", searchBox);
		Thread.sleep(5000);
		
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is testScroll", ExtentColor.GREEN));
	}

	@Test(dependsOnMethods = "openBrowser", enabled = true)
	public void testStaleElementReferenceException() {
		logger = extent.createTest("testStaleElementReferenceException");
		driver.get("https://www.flipkart.com/");

		WebElement logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));

		((JavascriptExecutor) driver).executeScript("history.go(0)");

		try {
			// System.out.println(logon.);
			logon.sendKeys(Keys.ESCAPE);
		} catch (StaleElementReferenceException staleObj) {
			logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
			logon.sendKeys("Am");
			System.out.println("MESSAGE : " + staleObj.getMessage());
			Assert.assertEquals(staleObj.getClass(), StaleElementReferenceException.class);
		}
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is testStaleElementReferenceException", ExtentColor.GREEN));
	}

	@Test(dependsOnMethods = "openBrowser", enabled = true)
	public void testheadless() {
		logger = extent.createTest("testheadless");
		driver.get("https://www.flipkart.com/");

		WebElement logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));

		((JavascriptExecutor) driver).executeScript("history.go(0)");

		try {
			// System.out.println(logon.);
			logon.sendKeys(Keys.ESCAPE);
		} catch (StaleElementReferenceException staleObj) {
			logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
			logon.sendKeys("Am");
			System.out.println("MESSAGE : " + staleObj.getMessage());
			Assert.assertEquals(staleObj.getClass(), StaleElementReferenceException.class);
		}
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is testheadless", ExtentColor.GREEN));
	}

	@Test
	public void openBrowser() {
		logger = extent.createTest("openBrowser");
		String browserType = "Chrome";

		System.setProperty("webdriver.chrome.driver", "D:\\JARS\\chromedriver239.exe");
		System.setProperty("webdriver.gecko.driver", "D:\\JARS\\geckodriver.exe");
		System.setProperty("webdriver.ie.driver", "D:\\JARS\\IEDriverServer312.exe");

		DesiredCapabilities caps = null;

		switch (browserType) {
		case "Firefox":
			caps = DesiredCapabilities.firefox();
			caps.setCapability("ignoreZoomSetting", true);
			driver = new FirefoxDriver();
			break;
		case "Chrome":
			caps = DesiredCapabilities.chrome();
			caps.setCapability("ignoreZoomSetting", true);
			driver = new ChromeDriver();
			break;
		case "IE":
			caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(caps);
			break;
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is openBrowser", ExtentColor.GREEN));
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			// logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
			// MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			// logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		}
	}

	@AfterTest
	public void endReport() {
		driver.quit();
		extent.flush();
	}
}