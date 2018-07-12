package sample.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(sample.listener.Listener.class)
public class TestGrouping {
	@Test(groups = { "Terrestrial" })
	public void dog() {
		System.out.println("I am dog, I live on land");
	}

	@Test(groups = { "Terrestrial" })
	public void elephant() {
		System.out.println("I am elephant, I live on land");
	}

	@Test(groups = { "Aquatic" })
	public void shark() {
		System.out.println("I am shark, I live in water");
	}

	@Test(groups = { "Aquatic" })
	public void dolphin() {
		System.out.println("I am dolphin, I live in water");
	}

	@Test(groups = { "Amphibian", "Terrestrial", "Aquatic" })
	public void frog() {
		System.out.println("I am frog, I live in both land and water");
	}

	@Test(groups = { "Amphibian", "Terrestrial", "Aquatic" })
	public void croc() {
		System.out.println("I am crocodile, I live in both land and water");
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

}
