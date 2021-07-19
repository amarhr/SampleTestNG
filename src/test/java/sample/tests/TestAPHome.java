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

import com.flipkart.pages.FlipkartHomePage;

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

	@Test(dependsOnMethods = "openBrowser", enabled = false)
	public void highligtLogonButton() {
		driver.get("https://www.flipkart.com/");
		WebElement logon = driver.findElement(By.xpath("//span[text()='Login']/.."));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red';", logon);

		int i = 0;

		try {
			while (i++ < 3) {
				Thread.sleep(2000);

				js.executeScript("history.go(0)");

				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = "openBrowser", enabled = false)
	public void testFlash() {
		driver.get("https://www.flipkart.com/");
		WebElement logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
		String bgColor = logon.getCssValue("backgroundColor");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		try {
			while (i++ < 3) {
				Thread.sleep(100);
				js.executeScript("arguments[0].style.backgroundColor='Green'", logon);
				Thread.sleep(100);
				js.executeScript("arguments[0].style.backgroundColor='Blue'", logon);
				Thread.sleep(100);
				js.executeScript("arguments[0].style.backgroundColor='" + bgColor + "'", logon);
				Thread.sleep(100);
				js.executeScript("arguments[0].style.backgroundColor='Green'", logon);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(logon.getCssValue("backgroundColor"), "rgba(0, 128, 0, 1)");

		String title = js.executeScript("return document.title;").toString();
		System.out.println(driver.getTitle());
		Assert.assertEquals(title,
				"Online Shopping Site for Mobiles, Fashion, Books, Electronics, Home Appliances and More");
	}

	@Test(enabled = true)
	public void testScroll() throws InterruptedException {
		core = new SeleniumCore();
		driver = core.getDriver();
		driver.get("https://www.flipkart.com/");
		FlipkartHomePage flipKartHomePage = new FlipkartHomePage(driver); // PageFactory.initElements(driver, FlipkartHomePage.class);
		flipKartHomePage.getLogin().sendKeys(Keys.ESCAPE);
		try {
			// driver.wait(1000);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement electronicsLink = flipKartHomePage.getElectronicsLink();
		// flipKartHomePage.scrollIntoView(electronicsLink);
		core.scrollIntoView(electronicsLink);
		core.scrollToTheEnd();

		String title = core.getPageTitleUsingJS();
		System.out.println(driver.getTitle());
		Assert.assertEquals(title,
				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");

		// driver.wait(1000);

		core.scrollToTheEnd();
		core.hardWait(3);

		electronicsLink = flipKartHomePage.getElectronicsLink();
		// flipKartHomePage.scrollIntoView(electronicsLink);
		core.scrollIntoView(electronicsLink);
		// Thread.sleep(5000);
	}

	@Test(dependsOnMethods = "openBrowser", enabled = false)
	public void testStaleElementReferenceException() {
		driver.get("https://www.flipkart.com/");
		FlipkartHomePage flipKartHomePage = PageFactory.initElements(driver, FlipkartHomePage.class);
		WebElement logon = flipKartHomePage.getLogonElement();

		((JavascriptExecutor) driver).executeScript("history.go(0)");

		try {
			logon.sendKeys(Keys.ESCAPE);
		} catch (StaleElementReferenceException staleObj) {
			logon = flipKartHomePage.getLogonElement();
			logon.sendKeys("Am");
			System.out.println("MESSAGE : " + staleObj.getMessage());
			Assert.assertEquals(staleObj.getClass(), StaleElementReferenceException.class);
		}
	}

	@Test(dependsOnMethods = "openBrowser", enabled = false)
	public void testheadless() {
		driver.get("https://www.flipkart.com/");
		FlipkartHomePage flipKartHomePage = PageFactory.initElements(driver, FlipkartHomePage.class);
		WebElement logon = flipKartHomePage.getLogonElement();

		((JavascriptExecutor) driver).executeScript("history.go(0)");

		try {
			// System.out.println(logon.);
			logon.sendKeys(Keys.ESCAPE);
		} catch (StaleElementReferenceException staleObj) {
			logon = flipKartHomePage.getLogonElement();
			logon.sendKeys("Am");
			System.out.println("MESSAGE : " + staleObj.getMessage());
			Assert.assertEquals(staleObj.getClass(), StaleElementReferenceException.class);
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