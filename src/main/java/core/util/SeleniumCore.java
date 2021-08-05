package core.util;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumCore {
	private WebDriver driver;
	private Actions actions;
	private JavascriptExecutor js;
	private static final Logger LOGGER = LogManager.getLogger(SeleniumCore.class.getName());

	public SeleniumCore(String browserType) {
		File chromeExe = new File(System.getProperty("user.dir") + "\\JARS\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chromeExe.getAbsolutePath());
		initializeBrowser(browserType);
		actions = new Actions(driver);

		driver.manage().window().maximize();
	}

	public WebDriver getDriver() {
		return driver;
	}

	private void initializeBrowser(String browserType) {
		LOGGER.debug("openBrowser()");

		DesiredCapabilities caps = null;

		switch (browserType.toLowerCase()) {
		case "firefox":
			caps = DesiredCapabilities.firefox();
			caps.setCapability("ignoreZoomSetting", true);
			driver = new FirefoxDriver();
			break;
		case "chrome":
			LOGGER.debug("Instantiating chrome");
			caps = DesiredCapabilities.chrome();
			caps.setCapability("ignoreZoomSetting", true);
			driver = new ChromeDriver();

			break;
		case "chromeheadless":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
			break;
		case "ie":
			caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver();
			break;
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void moveToElement(WebElement element) throws InterruptedException {
		hardWait(5);
		actions.moveToElement(element).perform();
	}

	public void scrollIntoView(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	public void rightClickAndOpenInNewWindow(WebElement element) {
		actions.contextClick(element).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).build().perform();
	}

	public void rightClickAndOpenInNewTAB(WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
	}

	public void scrollByPage(int count, String direction) throws InterruptedException {
		String script = "window.scrollBy(0, %s1000)";
		js = (JavascriptExecutor) driver;

		if (direction.equals("UP"))
			script = String.format(script, "-");
		else
			script = String.format(script, "");

		for (int i = 0; i < count; i++) {
			Thread.sleep(500);
			js.executeScript(script);
		}
	}

	public void scrollToTheEnd() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	public String getPageTitleUsingJS() {
		return js.executeScript("return document.title;").toString();
	}

	public void hardWait(int seconds) {
		try {
			// driver.wait(seconds * 1000);
			// https://stackoverflow.com/questions/5858743/driver-wait-throws-illegalmonitorstateexception
			synchronized (driver) {
				driver.wait(seconds * 1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public WebElement waitForElement(By byObj, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds * 1000);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byObj));
		return element;
	}

	public WebElement waitFluentlyForElement(By byObj, int seconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(seconds))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(byObj);
			}
		});
		return element;
	}

	public void quit() {
		driver.quit();
	}

	public void close() {
		driver.close();
	}
}
