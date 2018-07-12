package sample.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDataProvider {

	@DataProvider( name = "Authentication" )
	public static Object[][] credentials() {
		return new Object[][] { {"amar", "BE"}, {"aaru", "UKG"} }; 
	}
	
	@DataProvider( name = "BrowserType" )
	public static Object[]BrowserType() {
		return new Object[] { "Chrome", "IE"}; 
	}
	
	@Test(dataProvider = "Authentication", enabled=false)
	public void qualificationTest(String name, String qualification) {
		System.out.println("NAME AND QUALIFICATION" + name + qualification);
	}
	
	@Test(dataProvider = "BrowserType")
	public void instantiatebrowser(String browserType) {
		System.out.println("BROWSER" + browserType);
		
		WebDriver driver = null;
		// String browserType = "IE";

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
		driver.quit();
	}
}
