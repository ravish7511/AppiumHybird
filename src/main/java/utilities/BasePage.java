package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {

	public AndroidDriver<AndroidElement> driver;

	public BasePage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void waitUntilElementToClickable(int seconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			System.out.println("unable to click");
		}

	}

	public void waitUntilVisibilityOfElement(int seconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch (Exception e) {
			System.out.println("element not visible");
		}
	}
	
	public void waitUntilTextisPresent(int seconds,String text) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(text)));
		}
		catch (Exception e) {
			System.out.println("text not visible");
		}
	}

}
