package com.webdriver.tests;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import core.util.SeleniumCore;

public class TestDynamiWebTables {
	@Test
	public void testWebTable() throws ParseException {
		SeleniumCore.setDriverPath();

		WebDriver wd = new ChromeDriver();
		wd.get("http://demo.guru99.com/test/web-table-element.php");

		String max = null;
		double m = 0, r = 0;

		// No. of Columns
		List<WebElement> col = wd.findElements(By.xpath("//*[@id='leftcontainer']//th"));
		System.out.println("Total No of columns are : " + col.size());
		// No.of rows
		List<WebElement> listOfCompanies = wd.findElements(By.xpath("//*[@id='leftcontainer']//td[1]"));
		System.out.println("Total No of rows are : " + listOfCompanies.size());
		for (int i = 1; i < listOfCompanies.size(); i++) {
			max = wd.findElement(By.xpath("//tr[" + (i + 1) + "]/td[3]")).getText();
			NumberFormat f = NumberFormat.getNumberInstance();
			Number num = f.parse(max);
			max = num.toString();
			m = Double.parseDouble(max);
			if (m > r) {
				r = m;
			}
		}

		Map<Float, String> prevClosePriceMap = listOfCompanies.stream()
				.collect(Collectors.toMap(s -> getPrevClose(s), s -> s.getText()));
		Float MAXIMUM = (Float)Collections.max(prevClosePriceMap.keySet(), null);
		System.out.println("Maximum current price is : " + MAXIMUM + " : " + prevClosePriceMap.get(MAXIMUM));

		List<Float> listOfPrevClosePrice = listOfCompanies.stream().map(s -> getPrevClose(s))
				.collect(Collectors.toList());
		System.out.println("Maximum current price is : " + Collections.max(listOfPrevClosePrice, null));
		
		wd.quit();
	}

	public Entry<Float, String> getPrevCloseEntry(WebElement element) {
		String companyName = element.getText();
		Float prevClose = Float.valueOf(element.findElement(By.xpath("../td[3]")).getText());

		return Map.entry(prevClose, companyName);
	}

	public Float getPrevClose(WebElement element) {
		String prevClose = element.findElement(By.xpath("../td[3]")).getText();

		return Float.valueOf(prevClose);
	}
}
