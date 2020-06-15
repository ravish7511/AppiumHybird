package pageObjects;

import org.openqa.selenium.WebElement;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utilities.BasePage;

public class RegistrationPage extends BasePage {

	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement dropDown;

	@AndroidFindBy(xpath = "//*[@text='Belgium']")
	private WebElement countryName;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleRadioButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShop;

	public RegistrationPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	public void clickCountryDropDown() {
        waitUntilElementToClickable(5, dropDown);
		dropDown.click();
	}

	public void selectCountry() {
		countryName.click();
	}

	public void setName(String uname) {
		nameField.sendKeys(uname);
	}

	public void clickFemaleRadioButton() {
		femaleRadioButton.click();
	}

	public void clickLetsShop() {
		letsShop.click();
	}

}
