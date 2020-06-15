package resource;

import org.testng.ITestListener;
import org.testng.ITestResult;

import generic.EcomBaseClass;


public class Listeners implements ITestListener {

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		ITestListener.super.onTestSuccess(result);
		new EcomBaseClass().getScreenShot(result.getName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		
		
		
	}

}
