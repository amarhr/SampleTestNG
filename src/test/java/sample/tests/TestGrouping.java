package sample.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestGrouping {
	@Test(groups = { "Terrestrial", "Mammal" })
	public void dog() {
		System.out.println("I am dog, I live on LAND");
	}

	@Test(groups = { "Terrestrial", "Mammal" })
	public void elephant() {
		System.out.println("I am elephant, I live on LAND");
	}

	@Test(groups = { "Aquatic" })
	public void shark() {
		System.out.println("I am shark, I live in WATER");
	}

	@Test(groups = { "Aquatic" })
	public void dolphin() {
		System.out.println("I am dolphin, I live in WATER");
	}

	@Test(groups = { "Terrestrial", "Aquatic" })
	public void frog() {
		System.out.println("I am frog, I live in both LAND and WATER");
	}

	@Test(groups = { "Terrestrial", "Aquatic", "Reptile"})
	public void croc() {
		System.out.println("I am crocodile, I live in both LAND and WATER");
	}
	
	@Test(groups= {"Reptile", "Terrestrial"})
	public void snake() {
		System.out.println("I am snake, I live on Land");
	}
	
	@Test(groups= { "Aquatic", "Mammal"})
	public void whale() {
		System.out.println("I am whale, I live in WATER");
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}
}