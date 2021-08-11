package testng.parallel.tests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ParallelSuiteTest {
	String testName = "";
	static int beforeClassCount;
	static int afterClassCount;
	
	static int beforeTestCount;
	static int afterTestCount;
	
	static int beforeMethodCount;
	static int afterMethodCount;
	
	/* 1. Create ExtentReports object by passing html path
	 * 2. LoadConfig file for extentReports object
	 * 3. Create ExtentTest object by starting the test from reports - extentReports.startTest()
	 * 4. Log the info or test status using - test.log()
	 * 5. flush the content at the end of the reporting using report - report.flush()
	 * */
	
	
	static ExtentReports report;
	static ExtentTest test;
	
	WebDriver driver;
	
	@BeforeSuite
	public void beforeSuite() {
		String fileName = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
		report = new ExtentReports(System.getProperty("user.dir") + "\\Report" + fileName + ".html");
		// report.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\wb\\utils\\extent-config.xml"));
	}

	@BeforeTest
	@Parameters({ "test-name" })
	public void beforeTest(String testName) {
		beforeTestCount++;
		this.testName = testName;
		long id = Thread.currentThread().getId();
		System.out.println("Before Test " + testName + ". Thread: " + id);
	}

	@BeforeClass
	public void beforeClass() {
		beforeClassCount++;
		long id = Thread.currentThread().getId();
		System.out.println("Before Class " + testName + ". Thread: " + id);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		beforeMethodCount++;
		test = report.startTest(testName, testName);
	}

	@Test
	public void testMethod() throws InterruptedException {
		long id = Thread.currentThread().getId();
		System.out.println("Sample " + testName + ". Thread: " + id);
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\JARS\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.google.com");
		
		Thread.sleep(3000);
		test.log(LogStatus.PASS, testName);
		driver.quit();
	}
	
	@Test
	public void testMethod10() throws InterruptedException {
		long id = Thread.currentThread().getId();
		System.out.println("Sample testMethod10. Thread: " + id);
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\JARS\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.youtube.com");
		
		Thread.sleep(3000);
		test.log(LogStatus.PASS, testName);
		driver.quit();
	}
	
	@AfterClass
	public void afterClass() {
		afterClassCount++;
		long id = Thread.currentThread().getId();
		System.out.println("After Class " + testName + ". Thread: " + id);
	}

	@AfterTest
	public void afterTest() {
		afterTestCount++;
		long id = Thread.currentThread().getId();
		System.out.println("After Test " + testName + ". Thread: " + id);
	}
	
	@AfterMethod
	public void aftermethod() {
		afterMethodCount++;
	}
	
	@AfterSuite
	public void afterSuite() {
		report.flush();
		
		System.out.println();
		System.out.println("beforeTestCount: " + beforeTestCount);
		System.out.println("afterTestCount: " + afterTestCount);
		System.out.println();
		
		System.out.println("beforeClassCount: " + beforeClassCount);
		System.out.println("afterClassCount: " + afterClassCount);
		System.out.println();
		
		System.out.println("beforeMethodCount: " + beforeMethodCount);
		System.out.println("afterMethodCount: " + afterMethodCount);
		System.out.println();
	}
}
