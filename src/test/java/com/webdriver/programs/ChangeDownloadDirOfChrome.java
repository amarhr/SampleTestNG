package com.webdriver.programs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import core.util.SeleniumCore;

public class ChangeDownloadDirOfChrome {

	public static void main(String[] args) throws IOException, InterruptedException {

		SeleniumCore core = new SeleniumCore();
		// Setting chrome driver path
		core.setChromeDriverPath();

		// Setting new download directory path
		Map<String, Object> prefs = new HashMap<String, Object>();

		// Use File.separator as it will work on any OS
		prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "externalFiles");
		// prefs.put("incognito", true);

		// Adding cpabilities to ChromeOptions
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("start-maximized");
		options.addArguments("incognito");

		// Printing set download directory
		// .out.println(options.exper);

		// Launching browser with desired capabilities
		ChromeDriver driver = new ChromeDriver(options);

		// URL loading
		driver.get("https://www.seleniumhq.org/download/");

		// Click on download selenium server jar file
		driver.findElement(By.xpath("//a[text()='32 bit Windows IE']")).click();
		
		Thread.sleep(5000);
		driver.quit();
	}
}