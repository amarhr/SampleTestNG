package sample.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestSequencing {
  @Test
  public void testCase1() {
	  System.out.println("This is testCase1");
	  Reporter.log("This is testCase1");
  }
  
  @Test
  public void testCase2() {
	  System.out.println("This is testCase2");
	  Reporter.log("This is testCase2");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("This is BeforeMethod");
	  Reporter.log("This is BeforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("This is AfterMethod");
	  Reporter.log("This is AfterMethod");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("This is BeforeClass");
	  Reporter.log("This is BeforeClass");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("This is AfterClass");
	  Reporter.log("This is AfterClass");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("This is BeforeTest");
	  Reporter.log("This is BeforeTest");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("This is AfterTest");
	  Reporter.log("This is AfterTest");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("This is BeforeSuite");
	  Reporter.log("This is BeforeSuite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("This is AfterSuite");
	  Reporter.log("This is AfterSuite");
  }
}