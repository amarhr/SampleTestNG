package sample.tests;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import core.util.SeleniumCore;

// @Listeners(sample.listener.Listener.class)
public class TestAPHome {
	SeleniumCore core;
	private WebDriver driver;

	@Test(dependsOnMethods = "openBrowser", enabled = false)
	public void TestAPTitle() {
		driver.navigate().to("http://automationpractice.com/index.php");
		// driver.get("http://automationpractice.com/index.php");

		String title = driver.getTitle();
		Assert.assertTrue(title.contains("My Store"));

		WebElement signIn = driver.findElement(By.className("login"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", signIn);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement userName = driver.findElement(By.id("email"));
		js.executeScript("arguments[0].style.border='3px solid red'", userName);

		TakesScreenshot takeSS = (TakesScreenshot) driver;
		File src = takeSS.getScreenshotAs(OutputType.FILE);

		try {
			Random random = new Random();
			int randomNumber = random.nextInt(10000);
			FileUtils.copyFile(src,
					new File(System.getProperty("user.dir") + "//screenshots//BorderUsername" + randomNumber));
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	// @BeforeMethod
	public void setup() {
		core = new SeleniumCore();
		driver = core.getDriver();
	}

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}
}