package core.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
		this(browserType, "");
	}

	public SeleniumCore(String browserType, String url) {
		try {
			initializeBrowser(browserType, url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}

	public WebDriver getDriver() {
		return driver;
	}

	private void initializeBrowser(String browserType, String url) throws MalformedURLException {
		LOGGER.debug("openBrowser()");
		browserType = browserType.toLowerCase();

		File chromeExe = new File(System.getProperty("user.dir") + "\\JARS\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chromeExe.getAbsolutePath());

		if (browserType.contains("chrome")) {
			switch (browserType.toLowerCase()) {
			case "chrome":
				LOGGER.debug("Instantiating chrome");
				driver = new ChromeDriver();
				break;
			case "chromeheadless":
				ChromeOptions options = new ChromeOptions();
				options.addArguments("headless");
				driver = new ChromeDriver(options);
				break;
			case "chromeremote":
				driver = initiateRemoteDriver("chrome", url);
				break;
			}
		}

		if (browserType.contains("firefox")) {
			switch (browserType.toLowerCase()) {
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "firefoxheadless":
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("headless");
				driver = new FirefoxDriver(options);
				break;
			case "firefoxremote":
				driver = initiateRemoteDriver("firefox", url);
				break;
			}
		}

		switch (browserType.toLowerCase()) {
		case "ieremote":
			driver = initiateRemoteDriver("ie", url);
			break;
		case "ie":
			driver = new InternetExplorerDriver();
			break;
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	}

	public WebDriver initiateRemoteDriver(String browserType, String url) {
		DesiredCapabilities DC = new DesiredCapabilities();
		DC.setBrowserName(browserType);
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(url), DC);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}

	public void moveToElement(WebElement element) {
		hardWait(5);
		actions.moveToElement(element).perform();
	}

	public void scrollIntoView(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		
		hardWait(3);
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

	// Explicit waits
	public WebElement waitForElement(By byObj, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds * 1000);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byObj));
		return element;
	}

	// Fluent waits
	public WebElement waitFluentlyForElement(By byObj, int seconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(seconds))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		/*
		 * WebElement element = wait.until( new Function<WebDriver, WebElement>() {
		 * public WebElement apply(WebDriver driver) { return driver.findElement(byObj);
		 * } });
		 */

		WebElement element = wait.until(d -> d.findElement(byObj));
		/*
		 * new Function<WebDriver, WebElement>() { public WebElement apply(WebDriver
		 * driver) { return driver.findElement(byObj); } });
		 */
		return element;
	}

	public void get(String url) {
		driver.get(url);
	}

	public String takeScreenShot(String fileName) {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File file_Screenshot = takeScreenShot.getScreenshotAs(OutputType.FILE);
		Random random = new Random();
		String screenshotPath = System.getProperty("user.dir") + "\\screenshots\\" + fileName;
		screenshotPath += (random.nextInt(5000) + ".png");

		try {

			FileUtils.copyFile(file_Screenshot, new File(screenshotPath));
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return screenshotPath;
	}

	public void quit() {
		driver.quit();
	}

	public void close() {
		driver.close();
	}
}