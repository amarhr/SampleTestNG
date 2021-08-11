package com.flipkart.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class FlipkartHomePage {
	private WebDriver driver;

	public FlipkartHomePage(WebDriver driver) {
		// driver = getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.CSS, css = "[alt*='Electronics']")
	@Getter
	// @CacheLookup
	WebElement electronicsLink;

	@FindBy(xpath = "//button/span[text()='Login']/..")
	@Getter
	@CacheLookup
	WebElement login;

	@FindBy(how = How.XPATH, xpath = "//*[@id='container']//a[contains(.,'Login & Signup')]")
	@Getter
	@CacheLookup
	WebElement loginAndSignUp;

	public WebElement getLogonElement() {
		WebElement logonElement = null;
		try {
			logonElement = login;
		} catch (NoSuchElementException noEleExcep) {
			logonElement = loginAndSignUp;
		}

		return logonElement;
	}
}
