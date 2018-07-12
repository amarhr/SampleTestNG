package sample.tests;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TestAPHome {
	private WebDriver driver;

	@Test (dependsOnMethods = "openBrowser", enabled=false)
	public void TestAPTitle() {
		driver.navigate().to("http://automationpractice.com/index.php");
		//driver.get("http://automationpractice.com/index.php");
		
		String title = driver.getTitle();				 
		Assert.assertTrue(title.contains("My Store"));
		

		WebElement signIn = driver.findElement(By.className("login"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", signIn);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement userName = driver.findElement(By.id("email"));
		js.executeScript("arguments[0].style.border='3px solid red'", userName);
		
		TakesScreenshot takeSS = (TakesScreenshot)driver;
		File src = takeSS.getScreenshotAs(OutputType.FILE);
		
		try {
			Random random = new Random();
			int randomNumber = random.nextInt(10000);
			FileUtils.copyFile(src, new File("D://screenshots//BorderUsername" + randomNumber));
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods="openBrowser", enabled=false)
	public void highligtLogonButton() {
		driver.get("https://www.flipkart.com/");
		WebElement	logon = driver.findElement(By.xpath("//span[text()='Login']/.."));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='3px solid red';", logon);
		
		int i = 0;
		
		try {
			while(i++ < 3) {
				Thread.sleep(2000);
				
				js.executeScript("history.go(0)");
				
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods="openBrowser", enabled=false)
	public void testFlash() {
		driver.get("https://www.flipkart.com/");
		WebElement	logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
		String bgColor = logon.getCssValue("backgroundColor");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		int i = 0;
		try {
			while(i++ < 3) {
				Thread.sleep(100);
				js.executeScript("arguments[0].style.backgroundColor='Green'", logon);
				Thread.sleep(100);
				js.executeScript("arguments[0].style.backgroundColor='Blue'", logon);
				Thread.sleep(100);
				js.executeScript("arguments[0].style.backgroundColor='"+bgColor+"'", logon);
				Thread.sleep(100);
				js.executeScript("arguments[0].style.backgroundColor='Green'", logon);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(logon.getCssValue("backgroundColor"), "rgba(0, 128, 0, 1)");
		
		String title = js.executeScript("return document.title;").toString();
		System.out.println(driver.getTitle());
		Assert.assertEquals(title, "Online Shopping Site for Mobiles, Fashion, Books, Electronics, Home Appliances and More");
	}
	
	@Test(dependsOnMethods="openBrowser", enabled=false)
	public void testScroll() throws InterruptedException {
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
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		
		String title = js.executeScript("return document.title;").toString();
		System.out.println(driver.getTitle());
		Assert.assertEquals(title, "Online Shopping Site for Mobiles, Fashion, Books, Electronics, Home Appliances and More");
		
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
	}
	
	@Test(dependsOnMethods="openBrowser", enabled=false)
	public void testStaleElementReferenceException() {
		driver.get("https://www.flipkart.com/");
		
		WebElement logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
		
		((JavascriptExecutor)driver).executeScript("history.go(0)");

		try {
			// System.out.println(logon.);
			logon.sendKeys(Keys.ESCAPE);
		} catch(StaleElementReferenceException staleObj) {
			logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
			logon.sendKeys("Am");
			System.out.println("MESSAGE : " + staleObj.getMessage());
			Assert.assertEquals(staleObj.getClass(), StaleElementReferenceException.class);
		}
		
	}
	
	@Test(dependsOnMethods="openBrowser", enabled=true)
	public void testheadless() {
		driver.get("https://www.flipkart.com/");
		
		WebElement logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
		
		((JavascriptExecutor)driver).executeScript("history.go(0)");

		try {
			// System.out.println(logon.);
			logon.sendKeys(Keys.ESCAPE);
		} catch(StaleElementReferenceException staleObj) {
			logon = driver.findElement(By.xpath("//button/span[text()='Login']/.."));
			logon.sendKeys("Am");
			System.out.println("MESSAGE : " + staleObj.getMessage());
			Assert.assertEquals(staleObj.getClass(), StaleElementReferenceException.class);
		}
		
	}

	@Test
	public void openBrowser() {
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
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}