package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserCustomerInfoPageUI;
import pageUIs.nopCommerce.user.UserHomePageUI;

// pageObject chứa các actions muốn thực hiện trong 1 page

public class UserCustomerInfoPageObject extends BasePage {
	private WebDriver driver;
	
	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isCustomerInfoPageDisplayed() {
		waitForAllElementVisible(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_TITLE);
		isElementDisplayed(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_TITLE);
		return true;
	}


	
	
}

	

	


