package sample.listener;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

// IInvokedMethodListener
public class Listener implements ISuiteListener, ITestListener, IInvokedMethodListener {

	@Override
	public void onStart(ISuite arg) {
		Reporter.log("I am in onStart(ISuite arg) ===== :" + Listener.class + "\n");
		System.out.println("I am in onStart(ISuite arg) ===== :" + Listener.class);
	}

	@Override
	public void onFinish(ISuite arg) {
		Reporter.log("I am in onFinish(ISuite arg) ===== :" + Listener.class + "\n");
		System.out.println("I am in onFinish(ISuite arg) ===== :" + Listener.class);
	}

	@Override
	public void onStart(ITestContext arg) {
		Reporter.log("I am in onStart(ITestContext arg) ===== :" + Listener.class + "\n");
		System.out.print("I am in onStart(ITestContext arg) ===== :" + Listener.class);
		System.out.println(" ************************************************************");
	}

	@Override
	public void onTestStart(ITestResult arg) {
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
		System.out.println(" TESTSUCCESSTESTSUCCESSTESTSUCCESSTESTSUCCESSTESTSUCCESSTESTSUCCESS");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("I am in onTestFailure(ITestResult result) ===== :" + Listener.class + "\n");
		System.out.print("I am in onTestFailure(ITestResult result) ===== :" + Listener.class);
		System.out.println(" TESTFAILURETESTFAILURETESTFAILURETESTFAILURETESTFAILURETESTFAILURE");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("I am in onTestSkipped(ITestResult result) ===== : " + Listener.class + "\n");
		System.out.println("I am in onTestSkipped(ITestResult result) ===== : " + Listener.class);
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
