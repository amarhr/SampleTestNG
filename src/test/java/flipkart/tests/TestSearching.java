package flipkart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.flipkart.pages.FlipkartHomePage;

import core.util.SeleniumCore;
import sample.tests.NewTest;

public class TestSearching {
	SeleniumCore core;
	NewTest newTest = new NewTest();
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		driver = newTest.OpenBrowser("Chrome");
		driver.get("https://www.flipkart.com");
		driver.manage().window().maximize();
		checkAndCloseLogonPopUp();
		
		WebElement search = driver.findElement(By.xpath("//*[@id='container']//input[@name='q' and contains(@title,'Search')]"));
 		search.sendKeys("Pull over for Men");
 		search.sendKeys(Keys.ENTER);
		
 		WebDriverWait wait = new WebDriverWait(driver, 10);
 		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Filters']")));
	}
	
	@Test
	public void testSearch() {
 		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Filters']")).getText(), "Filters");
	}
	
	@Test
	public void testSearchFails() {
 		SoftAssert softAssert = new SoftAssert();
 		softAssert.assertEquals(driver.findElement(By.xpath("//span[text()='Filters']")).getText(), "FiltersFails");
 		
 		softAssert.assertAll();
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
	
	private WebElement checkAndCloseLogonPopUp() {
		String xpathForLogon = "//button/span[text()='Login']/..";
		WebElement logon = null;
		try {
			logon = driver.findElement(By.xpath(xpathForLogon));
			logon.sendKeys(Keys.ESCAPE);
		} catch(NoSuchElementException noEleExcep) {
		}
		
		return logon;
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}