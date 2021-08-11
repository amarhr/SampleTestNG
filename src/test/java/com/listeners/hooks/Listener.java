package com.listeners.hooks;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.core.tests.TestBase;

// IInvokedMethodListener
public class Listener extends TestBase implements ISuiteListener, ITestListener, IInvokedMethodListener {
	public ExtentReports exReports;
	public ExtentTest exTest;
	ThreadLocal<ExtentTest> threadLocalExtentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onStart(ISuite arg) {
		Reporter.log("In onStart(ISuite arg) ===== :" + Listener.class + "\n");
		System.out.println("In onStart(ISuite arg) :" + Listener.class);

		ExtentHtmlReporter exHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html");
		exReports = new ExtentReports();
		exReports.attachReporter(exHtmlReporter);
		exReports.setSystemInfo("Host", "Samples");
		exReports.setSystemInfo("Author", "Amarnath H R");
		exReports.setSystemInfo("Environment", "Automation Testing");

		exHtmlReporter.config().setDocumentTitle("");
		exHtmlReporter.config().setReportName("Name of the Report Comes here");
		exHtmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		exHtmlReporter.config().setTheme(Theme.STANDARD);

		threadLocalExtentTest.set(exTest);
	}

	@Override
	public void onFinish(ISuite arg) {
		Reporter.log("In onFinish(ISuite arg) ===== :" + Listener.class + "\n");
		System.out.println("In onFinish(ISuite arg) ===== :" + Listener.class);
		exReports.flush();
	}

	@Override
	public void onStart(ITestContext arg) {
		Reporter.log("In onStart(ITestContext arg) ===== :" + Listener.class + "\n");
		System.out.println("In onStart(ITestContext arg) ===== :" + Listener.class);
	}

	@Override
	public void onTestStart(ITestResult arg) {
		exTest = exReports.createTest(arg.getMethod().getMethodName());
		// -----------------------------REFER THIS IMPORTANT
		// STEP---------------------------------------------------
		// HOW TO MAKE EXTENT REPORTS THREAD SAFE IN PARALLEL EXECUTION
		threadLocalExtentTest.set(exTest);

		Reporter.log("In onTestStart(ITestResult arg) ===== :" + Listener.class + "\n");
		System.out.println();
		System.out.println();
		System.out.println("*************************ON TEST START***********************************");
		System.out.println("In onTestStart(ITestResult arg) ===== :" + Listener.class);
	}

	@Override
	public void onFinish(ITestContext arg) {
		Reporter.log("In onFinish(ITestContext arg) ===== :" + Listener.class + "\n");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("In onTestSuccess(ITestResult result) ===== :" + Listener.class + "\n");
		System.out.println("In onTestSuccess(ITestResult result) ===== :" + Listener.class);

		exTest.log(Status.PASS, "Test Case " + result.getMethod() + " Status is passed");
		exTest.log(Status.PASS, MarkupHelper.createLabel(
				"Test Case " + result.getMethod().getMethodName() + " Status is passed", ExtentColor.GREEN));
		System.out.println("*************************ON TEST END*************************************");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("In onTestFailure(ITestResult result) ===== :" + Listener.class + "\n");
		System.out.print("In onTestFailure(ITestResult result) ===== :" + Listener.class);

		exTest.log(Status.FAIL,
				MarkupHelper.createLabel(result.getMethod().getMethodName() + " - Test Case Failed", ExtentColor.RED));
		exTest.log(Status.FAIL,
				MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		System.out.println("*************************ON TEST END*************************************");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("In onTestSkipped(ITestResult result) ===== : " + Listener.class + "\n");
		System.out.println("In onTestSkipped(ITestResult result) ===== : " + Listener.class);

		exTest.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName() + " - Test Case Skipped",
				ExtentColor.ORANGE));
		System.out.println("*************************ON TEST END*************************************");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg) {
		Reporter.log("In onTestFailedButWithinSuccessPercentage(ITestResult result) ===== : " + Listener.class + "\n");
		// System.out.println(
		// "In onTestFailedButWithinSuccessPercentage(ITestResult result) ===== : " +
		// Listener.class);
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		Reporter.log("In before method Invocation :" + Listener.class + "\n");
		Reporter.log("Before executing" + returnMethodname(arg0) + "\n");
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		Reporter.log("In after method Invocation :" + Listener.class + "\n");
		Reporter.log("After executing :" + returnMethodname(arg0) + "\n");
	}

	private String returnMethodname(IInvokedMethod invokedMethod) {
		return invokedMethod.getTestMethod().toString();
	}
}
