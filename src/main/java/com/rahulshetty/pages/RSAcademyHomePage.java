package com.rahulshetty.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import lombok.Getter;

public class RSAcademyHomePage {
	WebDriver driver;

	public RSAcademyHomePage(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "select[id*='Currency']")
	@Getter
	@CacheLookup
	private WebElement currency;

	@FindBy(id = "divpaxinfo")
	@Getter
	@CacheLookup
	private WebElement passengeInfo;
	
	@FindBy(id="hrefDecAdt")
	@Getter
	@CacheLookup
	private WebElement addAdultButton;
	
	@FindBy(id="hrefIncChd")
	@Getter
	@CacheLookup
	private WebElement addChildButton;
	
	@FindBy(id="btnclosepaxoption")
	@Getter
	@CacheLookup
	private WebElement closePassengersDropDown;

	public Select CurrencyDropDown() {
		Select currencyDropDown = new Select(currency);
		return currencyDropDown;
	}

	public void selectCurrencyByIndex(int index) {
		CurrencyDropDown().selectByIndex(index);
	}

	public void selectCurrencyByVisibleText(String text) {
		CurrencyDropDown().selectByVisibleText(text);
	}

	public void selectCurrencyByValue(String value) {
		CurrencyDropDown().selectByValue(value);
	}

	public String getSelectedCurrency() {
		return CurrencyDropDown().getFirstSelectedOption().getText();
	}

	public void verifySelectedCurrencyIs(String expectedCurrency) {
		String selectedOption = getSelectedCurrency();
		Assert.assertEquals(expectedCurrency, selectedOption);
	}

	public void resetCurrencySelected() {
		selectCurrencyByVisibleText("Select");
	}
	
	public void addAdults(int count) {
		getPassengeInfo().click();
		// core.hardWait(2);

		System.out.println(getPassengeInfo().getText());
		for (int i = 1; i < count; i++) {
			driver.findElement(By.id("hrefIncAdt")).click();
		}

		driver.findElement(By.id("btnclosepaxoption")).click();
		Assert.assertEquals(getPassengeInfo().getText(), count + " Adult");
		System.out.println(getPassengeInfo().getText());
	}
	
	public void verifyNumberOfAdults(int numberOfAdults) {
		Assert.assertEquals(getPassengeInfo().getText(), numberOfAdults + " Adult");
		System.out.println(getPassengeInfo().getText());
	}
}