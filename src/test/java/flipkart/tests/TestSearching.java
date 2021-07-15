package flipkart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sample.tests.NewTest;

public class TestSearching {
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