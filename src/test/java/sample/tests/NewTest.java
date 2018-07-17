package sample.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(sample.listener.Listener.class)
public class NewTest {
	private WebDriver driver;
	
	@Test(dependsOnMethods="xbeforeTestOpenBrowser")
	public void TestGuruTitle() {
		driver.get("http://demo.guru99.com/");  
		String title = driver.getTitle();				 
		Assert.assertTrue(title.contains("Guru99 Bank Home Page"));
	}

	@Test
	public void xbeforeTestOpenBrowser() {
		String browserType = "FFWinHeadless";
		FirefoxOptions ffOptions = null;
		FirefoxBinary firefoxBinary = null;

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\JARS\\chromedriver239.exe");
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\JARS\\IEDriverServer312.exe");
		
		DesiredCapabilities caps = null;
		
		switch (browserType) {
			case "FFWin":
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\JARS\\geckodriver.exe");
				caps = DesiredCapabilities.firefox();
				caps.setCapability("ignoreZoomSetting", true);
				driver = new FirefoxDriver(caps);
				break;
			case "FFWinHeadless":
				firefoxBinary = new FirefoxBinary();
			    firefoxBinary.addCommandLineOptions("--headless");
			    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\JARS\\geckodriver.exe");
			    ffOptions = new FirefoxOptions();
			    ffOptions.setBinary(firefoxBinary);			
				
				driver = new FirefoxDriver(ffOptions);
				break;
			case "FFLinux":
				System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
				ffOptions = new FirefoxOptions();
				ffOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
				
				driver = new FirefoxDriver(ffOptions);
				break;
			case "FFLinuxHeadless":
				firefoxBinary = new FirefoxBinary();
			    firefoxBinary.addCommandLineOptions("--headless");
			    
				System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
				
				ffOptions = new FirefoxOptions();
				// ffOptions.addArguments("--headless");
				ffOptions.setBinary(firefoxBinary);
				ffOptions.addArguments("window-size=1024,768", "--no-sandbox");
				driver = new FirefoxDriver(ffOptions);
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
				driver.manage().window().maximize();
				break;
		}

		driver.manage().deleteAllCookies();
		Assert.assertTrue(true);
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
