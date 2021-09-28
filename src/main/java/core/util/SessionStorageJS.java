package core.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;

public class SessionStorageJS {
	WebDriver driver;
	
	public SessionStorageJS(WebDriver driver) {
		this.driver = driver;
	}
	
	public SessionStorage getSessionStorage() {
		WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
		SessionStorage sessionStorage = webStorage.getSessionStorage();
		return sessionStorage;
	}
}
