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
		Reporter.log("In onStart(ISuite arg) ===== :" + Listener.class + "\n");
		System.out.println("In onStart(ISuite arg) ===== :" + Listener.class);
		
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
		Reporter.log("In onFinish(ISuite arg) ===== :" + Listener.class + "\n");
		System.out.println("In onFinish(ISuite arg) ===== :" + Listener.class);
		extent.flush();
	}

	@Override
	public void onStart(ITestContext arg) {
		Reporter.log("In onStart(ITestContext arg) ===== :" + Listener.class + "\n");
		System.out.println("In onStart(ITestContext arg) ===== :" + Listener.class);
	}

	@Override
	public void onTestStart(ITestResult arg) {
		testLogger = extent.createTest(arg.getMethod().getMethodName());
		Reporter.log("In onTestStart(ITestResult arg) ===== :" + Listener.class + "\n");
		System.out.println();
		System.out.println();
		System.out.println("*************************ON TEST START***********************************");
		System.out.println("In onTestStart(ITestResult arg) ===== :" + Listener.class);
	}

	@Override
	public void onFinish(ITestContext arg) {
		Reporter.log("In onFinish(ITestContext arg) ===== :" + Listener.class + "\n");
		// System.out.println("In onFinish(ITestContext arg) ===== :" + Listener.class);
		// System.out.println(" FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("In onTestSuccess(ITestResult result) ===== :" + Listener.class + "\n");
		System.out.println("In onTestSuccess(ITestResult result) ===== :" + Listener.class);
		
		testLogger.log(Status.PASS, "Test Case "+ result.getMethod() +" Status is passed");
		testLogger.log(Status.PASS, MarkupHelper.createLabel("Test Case "+ result.getMethod().getMethodName() + " Status is passed", ExtentColor.GREEN));
		System.out.println("*************************ON TEST END*************************************");
		System.out.println();
		System.out.println();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("In onTestFailure(ITestResult result) ===== :" + Listener.class + "\n");
		System.out.print("In onTestFailure(ITestResult result) ===== :" + Listener.class);
		// System.out.println(" TESTFAILURETESTFAILURETESTFAILURETESTFAILURETESTFAILURETESTFAILURE");
		
		testLogger.log(Status.FAIL,
				MarkupHelper.createLabel(result.getMethod().getMethodName() + " - Test Case Failed", ExtentColor.RED));
		testLogger.log(Status.FAIL,
				MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		System.out.println("*************************ON TEST END*************************************");
		System.out.println();
		System.out.println();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("In onTestSkipped(ITestResult result) ===== : " + Listener.class + "\n");
		System.out.println("In onTestSkipped(ITestResult result) ===== : " + Listener.class);
		
		testLogger.log(Status.SKIP,
				MarkupHelper.createLabel(result.getMethod().getMethodName() + " - Test Case Skipped", ExtentColor.ORANGE));
		System.out.println("*************************ON TEST END*************************************");
		System.out.println();
		System.out.println();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg) {
		Reporter.log("In onTestFailedButWithinSuccessPercentage(ITestResult result) ===== : " + Listener.class + "\n");
		// System.out.println(
		//		"In onTestFailedButWithinSuccessPercentage(ITestResult result) ===== : " + Listener.class);
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		Reporter.log("In before method Invocation :" + Listener.class + "\n");
		Reporter.log("Before executing" + returnMethodname(arg0) + "\n");

		// System.out.println("In before method Invocation :" + Listener.class);
		// System.out.println("Before executing : " + returnMethodname(arg0));
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		Reporter.log("In after method Invocation :" + Listener.class + "\n");
		Reporter.log("After executing :" + returnMethodname(arg0) + "\n");

		// System.out.println("In after method Invocation :" + Listener.class);
		// System.out.println("After executing :" + returnMethodname(arg0));
	}

	private String returnMethodname(IInvokedMethod invokedMethod) {
		return invokedMethod.getTestMethod().toString();
	}
}
