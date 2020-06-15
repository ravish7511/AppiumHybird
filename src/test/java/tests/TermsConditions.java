package tests;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import generic.EcomBaseClass;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import pageObjects.CartPage;
import pageObjects.ProductsPage;
import pageObjects.RegistrationPage;
import utilities.Utilities;

public class TermsConditions extends EcomBaseClass {
	@Test
	public void verifyTermsConditions() throws MalformedURLException, InterruptedException
	{
		
	
		AndroidDriver<AndroidElement> driver = capabilities();
		RegistrationPage rp = new RegistrationPage(driver);
		rp.setName("darling");
		driver.hideKeyboard();
		rp.clickFemaleRadioButton();
		rp.clickCountryDropDown();
		Utilities ut = new Utilities(driver);
		ut.scrollToText("Belgium");
		rp.selectCountry();
		rp.clickLetsShop();
		ProductsPage pp = new ProductsPage(driver);
		pp.clickShoes();
		pp.clickCart();
		Thread.sleep(5000);
		AndroidElement ae = driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction t=new TouchAction(driver);
		t.tap(TapOptions.tapOptions().withElement(ElementOption.element(ae))).perform();
		
		AndroidElement terms = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(terms))
				.withDuration(Duration.ofSeconds(3))).perform();
	
	}

}
