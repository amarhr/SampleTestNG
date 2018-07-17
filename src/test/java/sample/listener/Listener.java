package sample.listener;

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

// IInvokedMethodListener
public class Listener implements ISuiteListener, ITestListener, IInvokedMethodListener {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest testLogger;

	@Override
	public void onStart(ISuite arg) {
		Reporter.log("I am in onStart(ISuite arg) ===== :" + Listener.class + "\n");
		System.out.println("I am in onStart(ISuite arg) ===== :" + Listener.class);
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host", "Samples");
		extent.setSystemInfo("Author", "Amarnath H R");
		extent.setSystemInfo("Environment", "Automation Testing");
		
		htmlReporter.config().setDocumentTitle("");
		htmlReporter.config().setReportName("Name of the Report Comes here");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	@Override
	public void onFinish(ISuite arg) {
		Reporter.log("I am in onFinish(ISuite arg) ===== :" + Listener.class + "\n");
		System.out.println("I am in onFinish(ISuite arg) ===== :" + Listener.class);
		extent.flush();
	}

	@Override
	public void onStart(ITestContext arg) {
		Reporter.log("I am in onStart(ITestContext arg) ===== :" + Listener.class + "\n");
		System.out.print("I am in onStart(ITestContext arg) ===== :" + Listener.class);
		System.out.println(" ************************************************************");
	}

	@Override
	public void onTestStart(ITestResult arg) {
		testLogger = extent.createTest(arg.getMethod().getMethodName());
		Reporter.log("I am in onTestStart(ITestResult arg) ===== :" + Listener.class + "\n");
		System.out.println("I am in onTestStart(ITestResult arg) ===== :" + Listener.class);
	}

	@Override
	public void onFinish(ITestContext arg) {
		Reporter.log("I am in onFinish(ITestContext arg) ===== :" + Listener.class + "\n");
		System.out.print("I am in onFinish(ITestContext arg) ===== :" + Listener.class);
		System.out.println(" FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("I am in onTestSuccess(ITestResult result) ===== :" + Listener.class + "\n");
		System.out.print("I am in onTestSuccess(ITestResult result) ===== :" + Listener.class);
		// System.out.println(" TESTSUCCESSTESTSUCCESSTESTSUCCESSTESTSUCCESSTESTSUCCESSTESTSUCCESS");
		
		testLogger.log(Status.PASS, "Test Case "+ result.getMethod() +" Status is passed");
		testLogger.log(Status.PASS, MarkupHelper.createLabel("Test Case "+ result.getMethod().getMethodName() + " Status is passed", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("I am in onTestFailure(ITestResult result) ===== :" + Listener.class + "\n");
		System.out.print("I am in onTestFailure(ITestResult result) ===== :" + Listener.class);
		// System.out.println(" TESTFAILURETESTFAILURETESTFAILURETESTFAILURETESTFAILURETESTFAILURE");
		
		testLogger.log(Status.FAIL,
				MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		testLogger.log(Status.FAIL,
				MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("I am in onTestSkipped(ITestResult result) ===== : " + Listener.class + "\n");
		System.out.println("I am in onTestSkipped(ITestResult result) ===== : " + Listener.class);
		
		testLogger.log(Status.SKIP,
				MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg) {
		Reporter.log("I am in onTestFailedButWithinSuccessPercentage(ITestResult result) ===== : " + Listener.class + "\n");
		System.out.println(
				"I am in onTestFailedButWithinSuccessPercentage(ITestResult result) ===== : " + Listener.class);
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		Reporter.log("I am in before method Invocation :" + Listener.class + "\n");
		Reporter.log("Before executing" + returnMethodname(arg0) + "\n");

		System.out.println("I am in before method Invocation :" + Listener.class);
		System.out.println("Before executing : " + returnMethodname(arg0));
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		Reporter.log("I am in after method Invocation :" + Listener.class + "\n");
		Reporter.log("After executing :" + returnMethodname(arg0) + "\n");

		System.out.println("I am in after method Invocation :" + Listener.class);
		System.out.println("After executing :" + returnMethodname(arg0));
	}

	private String returnMethodname(IInvokedMethod invokedMethod) {
		return invokedMethod.getTestMethod().toString();
	}
}
