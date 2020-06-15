package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import utilities.BasePage;

public class CartPage extends BasePage {

	@AndroidBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<AndroidElement> productPrice;

	@AndroidBy(className = "android.widget.TextView")
	private AndroidElement totalPrice;

	@AndroidBy(className = "android.widget.CheckBox")
	private AndroidElement checkbox;

	@AndroidBy(id = "com.androidsample.generalstore:id/termsButton")
	private AndroidElement terms;

	public CartPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	public List<String> getProductPrice() throws InterruptedException {
		Thread.sleep(5000);

		List<String> list = new ArrayList<String>();

		for (AndroidElement ae : productPrice) {

			list.add(ae.getText());
		}
		return list;
	}

	public String getTotalPrice() {
		waitUntilTextisPresent(10, "android.widget.TextView");
		return totalPrice.getText();
	}

	public AndroidElement tapCheckBox() {
		// waitUntilElementToClickable(10, checkbox);
		return checkbox;
	}

	public AndroidElement longPressTerms() {
		return terms;
	}

}
