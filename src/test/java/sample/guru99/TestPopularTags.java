package sample.guru99;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.guru99.pages.GuruHomePage;

import core.util.SeleniumCore;

public class TestPopularTags {
	static WebDriver driver;
	SeleniumCore core;
	GuruHomePage guruHomePage;

	public TestPopularTags() {
		SeleniumCore core = new SeleniumCore("Chrome");
		driver = core.getDriver();
		driver.get("https://www.guru99.com/");
		guruHomePage = PageFactory.initElements(driver, GuruHomePage.class);
	}

	@Test
	public void TestGetPopluarTitles(String[] args) throws InterruptedException {
		List<WebElement> listPopularItems = driver.findElements(
				By.xpath("//h2[text()='Popular Tools']/ancestor::div[4]/following-sibling::div[1]//h4/a"));

		for (WebElement webElement : listPopularItems) {
			System.out.println(webElement.getText());
		}
	}

	/*
	 * public static void main(String[] args) { TestPopularTags obj = new
	 * TestPopularTags();
	 * 
	 * List<WebElement> listPopularItems = driver.findElements( By.
	 * xpath("//h2[text()='Popular Tools']/ancestor::div[4]/following-sibling::div[1]//h4/a"
	 * ));
	 * 
	 * for (WebElement webElement : listPopularItems) {
	 * System.out.println(webElement.getText()); } }
	 */
}
