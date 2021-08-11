package com.guru99.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import core.util.SeleniumCore;
import lombok.Getter;

public class GuruHomePage {
	SeleniumCore core;

	public GuruHomePage(SeleniumCore core) {
		this.core = core;
		PageFactory.initElements(core.getDriver(), this);
	}

	@FindBy(how = How.XPATH, xpath = "//*[@id='navbar-brand-centered']/ul/li[1]/a")
	@CacheLookup
	@Getter
	private WebElement seleniumLink;

	@FindBy(how = How.LINK_TEXT, linkText = "Ethical Hacking")
	@CacheLookup
	@Getter
	private WebElement ethicalHacking;

	@FindBy(how = How.LINK_TEXT, linkText = "Books to Read")
	@CacheLookup
	@Getter
	private WebElement booksToRead;

	public void mouseHoverOnBooksToRead() {
		core.moveToElement(getBooksToRead());
	}

	public void mouseHoverOnSeleniumLink() {
		core.moveToElement(getBooksToRead());
	}
}