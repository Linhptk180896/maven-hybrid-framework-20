package pageObject.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.LoginPagePageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToCreateAccountButton() {
		waitForElementClickable(driver, LoginPagePageUI.CREATE_NEW_ACCOUNT_BUTTON);	
		clickToElement(driver,  LoginPagePageUI.CREATE_NEW_ACCOUNT_BUTTON);
		
	}
	public void sendKeysToEmailTextbox(String email) {
		waitForElementVisible(driver, LoginPagePageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPagePageUI.EMAIL_TEXTBOX, email);
	}
	public boolean isConfirmEmailIsDisplayed() {
		
		return isElementDisplayed(driver, LoginPagePageUI.CONFRIM_EMAIL_TEXTBOX);
	}
	public void clickCloseIcon() {
		waitForElementClickable(driver, LoginPagePageUI.CLOSE_SIGNUP_POPUP_ICON);
		clickToElement(driver, LoginPagePageUI.CLOSE_SIGNUP_POPUP_ICON);
		
	}
	public boolean isConfirmEmailUndisplayed() {
		waitForElementInvisible(driver, LoginPagePageUI.CONFRIM_EMAIL_TEXTBOX);
		return isElementUndisplayed(driver, LoginPagePageUI.CONFRIM_EMAIL_TEXTBOX);
	}
	
	
}
