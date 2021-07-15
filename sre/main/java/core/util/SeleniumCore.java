package core.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SeleniumCore {
	private WebDriver driver;
	private Actions actions;
	private JavascriptExecutor js;

	public SeleniumCore() {
		System.setProperty("webdriver.chrome.driver", "D:\\JARS\\chromedriver41.exe");
		driver = new ChromeDriver();
		actions = new Actions(driver);

		driver.manage().window().maximize();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void moveToElement(WebElement element) throws InterruptedException {
		Thread.sleep(5000);
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
	

	public void scroll(int count, String direction) throws InterruptedException {
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
	
	public void quit() {
		driver.quit();
	}
}
