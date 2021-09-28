package core.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

public class LocalStorageJS {

	private JavascriptExecutor js;
	private WebDriver driver;

	public LocalStorageJS(WebDriver webDriver) {
		this.driver = webDriver;
		this.js = (JavascriptExecutor) webDriver;
	}

	public void removeItemFromLocalStorage(String item) {
		js.executeScript(String.format("window.localStorage.removeItem('%s');", item));
	}

	public boolean isItemPresentInLocalStorage(String item) {
		return !(js.executeScript(String.format("return window.localStorage.getItem('%s');", item)) == null);
	}

	public String getItemFromLocalStorage(String key) {
		return (String) js.executeScript(String.format("return window.localStorage.getItem('%s');", key));
	}

	public String getKeyFromLocalStorage(int key) {
		return (String) js.executeScript(String.format("return window.localStorage.key('%s');", key));
	}

	public Long getLocalStorageLength() {
		return (Long) js.executeScript("return window.localStorage.length;");
	}

	public void setItemInLocalStorage(String item, String value) {
		js.executeScript(String.format("window.localStorage.setItem('%s','%s');", item, value));
	}

	public void clearLocalStorage() {
		js.executeScript(String.format("window.localStorage.clear();"));
	}

	public LocalStorage getLocalStorage() {
		return ((WebStorage) driver).getLocalStorage();
	}
}
