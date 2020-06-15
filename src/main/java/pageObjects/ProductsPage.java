package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utilities.BasePage;

public class ProductsPage extends BasePage {

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> shoes;
	

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private AndroidElement cartButton;

	public ProductsPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}
	
	public void clickShoes()
	{
		
		for (WebElement webElement : shoes) {
			waitUntilElementToClickable(5,webElement);
			webElement.click();	
		}
	}

	public void clickCart() 
	{
		cartButton.click();
	}
}
