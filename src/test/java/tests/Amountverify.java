package tests;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.EcomBaseClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageObjects.CartPage;
import pageObjects.ProductsPage;
import pageObjects.RegistrationPage;
import resource.TestData;
import utilities.Utilities;

public class Amountverify extends EcomBaseClass {

	@Test(dataProvider = "inputdata",dataProviderClass = TestData.class)
	public void amountValidation(String input) throws MalformedURLException, InterruptedException {
		test=reports.createTest("amountValidation", "valid the amount of products");
		test.info("test started");
		AndroidDriver<AndroidElement> driver = capabilities();
		RegistrationPage rp = new RegistrationPage(driver);
		rp.setName(input);
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
		CartPage cp = new CartPage(driver);
		String price1 = cp.getProductPrice().get(0);
		String price2 = cp.getProductPrice().get(1);

		double amount1 = stringToDouble(price1);
		double amount2 = stringToDouble(price2);
		System.out.println(amount1 + amount2);
		test.info("test ended");

	}

	public double stringToDouble(String price) {
		String amount = price.substring(1);
		return Double.parseDouble(amount);
	}

}
