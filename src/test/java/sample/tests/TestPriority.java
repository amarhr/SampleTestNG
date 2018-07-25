package sample.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners(sample.listener.Listener.class)
public class TestPriority {
	WebDriver driver;

	@Parameters({ "browserType" })
	@BeforeClass
	public void OpenBrowser(String browserType) {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\JARS\\chromedriver239.exe");
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\JARS\\geckodriver.exe");
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\JARS\\IEDriverServer312.exe");
		
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
	}

	@Test(priority = 0)
	public void One() {
		System.out.println("Executing test case One");
	}

	@Test(priority = 1, enabled = true)
	public void Two() {
		System.out.println("Executing test case Two");
	}

	@Test(priority = 2, enabled = true)
	public void Three() {
		System.out.println("Executing test case Three");
	}

	@Test(priority = 3, enabled = true)
	public void Fourth() {
		System.out.println("Executing test case Fourth");
	}

	@Test(priority = 3, enabled = true)
	public void Fourth1() {
		System.out.println("Executing test case Fourth1");
	}

	@AfterMethod
	public void quit() {
		System.out.println("Exiting the driver after method");
		driver.quit();
	}
}
