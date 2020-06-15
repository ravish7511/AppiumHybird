package generic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class EcomBaseClass {
	
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentTest test;
	public static ExtentReports reports;
	
	AppiumDriverLocalService service;
	static AndroidDriver<AndroidElement> driver;
	
	@BeforeSuite
	public void setUp()
	{
		htmlreporter=new ExtentHtmlReporter("./reports/r.html");
		reports=new ExtentReports();
		reports.attachReporter(htmlreporter);
	}
	
	@AfterSuite
	public void tearDown()
	{
		reports.flush();
	}
	
	
	public AppiumDriverLocalService startService() {
		 service = AppiumDriverLocalService.buildDefaultService();
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {
			service.start();
		}
		return service;
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	
	public static void startEmulator() throws InterruptedException {
		try {
			Runtime.getRuntime().
			exec("E:\\appium\\Framework\\src\\main\\java\\utilities\\startEmulator.bat");
			Thread.sleep(10000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static AndroidDriver<AndroidElement> capabilities() throws MalformedURLException {
		File f = new File("src");
		File app = new File(f, new FileManager().getApkName());
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, new FileManager().getDeviceName());
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		dc.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
	    driver = new AndroidDriver<>(url, dc);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public String getScreenShot(String testName)
	{
		
		String date = new Date().toString().replace(":", "-");
		 final String path=System.getProperty("user.dir")+"/defects/"+date+testName+".png";
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(path);
		try {
			FileHandler.copy(src, dst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	@BeforeMethod
	public void startServer()
	{
		startService();
	}
	
	@AfterMethod
	public void stopServer(ITestResult res) throws IOException
	
	{
		String testName = "";
		if (ITestResult.SUCCESS == res.getStatus()) {
			testName=res.getName();
			// ScreenShot.getPhoto(driver, testName);

			test.pass("test case passed", MediaEntityBuilder
					.createScreenCaptureFromPath(new EcomBaseClass().getScreenShot(testName)).build());
		}
		test.assignAuthor("ravish");
		test.assignDevice("virtual device");
		test.assignCategory("mobile Automation");
		reports.setSystemInfo("windows", "10");
		service.stop();
	}
}
