package sample.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.core.tests.TestBase;

public class TestExtentReport extends TestBase {
	/*
	 * private WebDriver driver; ExtentHtmlReporter htmlReporter; private
	 * ExtentReports extent; ExtentTest logger;
	 */

	// @Test(dependsOnMethods = "openBrowser", enabled = true)
	@Test
	public void testScroll() throws InterruptedException {
		logger = extent.createTest("testScroll");
		driver.get("https://www.flipkart.com/");

		WebElement logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
		logon.sendKeys(Keys.ESCAPE);
		try {
			// driver.wait(1000);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");

		String title = js.executeScript("return document.title;").toString();
		System.out.println(driver.getTitle());
		Assert.assertEquals(title,
				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");

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

	@Test(dependsOnMethods = "openBrowser", enabled = false)
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
		logger.log(Status.PASS,
				MarkupHelper.createLabel("Test Case Passed is testStaleElementReferenceException", ExtentColor.GREEN));
	}

	@Test(dependsOnMethods = "openBrowser", enabled = false)
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