package testng.tests;

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
	// @Test(dependsOnMethods = "openBrowser", enabled = true)
	@Test
	public void testScroll() throws InterruptedException {
		exTest = exReports.createTest("testScroll");
		driver.get("https://www.flipkart.com/");

		WebElement logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
		logon.sendKeys(Keys.ESCAPE);
		core.hardWait(2);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");

		String title = js.executeScript("return document.title;").toString();
		System.out.println(driver.getTitle());
		Assert.assertEquals(title,
				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");

		core.hardWait(2);

		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");

		core.hardWait(2);

		WebElement searchBox = driver.findElement(By.xpath("//div[contains(text(),'Electronics')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", searchBox);
		core.hardWait(5);

		exTest.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is testScroll", ExtentColor.GREEN));
	}

	// @Test(dependsOnMethods = "openBrowser", enabled = false)
	public void testStaleElementReferenceException() {
		exTest = exReports.createTest("testStaleElementReferenceException");
		driver.get("https://www.flipkart.com/");

		WebElement logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));

		((JavascriptExecutor) driver).executeScript("history.go(0)");

		try {
			// System.out.println(logon.); logon.sendKeys(Keys.ESCAPE); }
		} catch (StaleElementReferenceException staleObj) {
			logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
			logon.sendKeys("Am");
			System.out.println("MESSAGE : " + staleObj.getMessage());
			Assert.assertEquals(staleObj.getClass(), StaleElementReferenceException.class);
		}
		exTest.log(Status.PASS,
				MarkupHelper.createLabel("Test Case Passed is testStaleElementReferenceException", ExtentColor.GREEN));
	}

	// @Test(dependsOnMethods = "openBrowser", enabled = false)
	public void testheadless() {
		exTest = exReports.createTest("testheadless");
		driver.get("https://www.flipkart.com/");

		WebElement logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));

		((JavascriptExecutor) driver).executeScript("history.go(0)");

		try {
			logon.sendKeys(Keys.ESCAPE);
		} catch (StaleElementReferenceException staleObj) {
			logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
			logon.sendKeys("Am");
			System.out.println("MESSAGE : " + staleObj.getMessage());
			Assert.assertEquals(staleObj.getClass(), StaleElementReferenceException.class);
		}
		exTest.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is testheadless", ExtentColor.GREEN));
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			exTest.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			exTest.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			exTest.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		}
	}

	@AfterTest
	public void endReport() {
		driver.quit();
		// exReports.flush();
	}
}