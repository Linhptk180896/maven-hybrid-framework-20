package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class LoginPageObject extends BasePageFactory {
	//Khai báo driver
	WebDriver driver;
	//Tạo constructor để new driver khi gọi hàm trong class LoginPageObject

 @FindBy(xpath = "//input[@id='Email']")
 private WebElement emailTextbox;
 
 @FindBy(xpath = "//input[@id='Password']")
 private WebElement passwordTextbox;
 
 @FindBy(xpath = "//button[text()='Log in']")
 private WebElement loginButton;
 
 @FindBy(xpath = "//span[@id='Email-error']")
 private WebElement emailErrorMessage;
 
@FindBy(xpath = "//div[contains(@class,'message-error')]")
private WebElement emailUnsuccessfulMessage;

public LoginPageObject(WebDriver driver) {
	 this.driver = driver;
	 PageFactory.initElements(driver, this);
	 
}
public void clickToLoginButton() {
	waitForElementClickable(driver, loginButton);
	clickToElement(driver, loginButton);
	
}
public String getErrorMessageAtEmailTextbox() {
	waitForElementVisible(driver, emailErrorMessage);
	return getElementText(driver, emailErrorMessage);
}
public void inputToEmailTextbox(String invalidEmail) {
	waitForElementVisible(driver, emailTextbox);
	sendKeyToElement(driver, emailTextbox, invalidEmail );
	
}
public String getErrorMessageUnsuccessful() {
	waitForElementVisible(driver, emailUnsuccessfulMessage);
	return getElementText(driver, emailUnsuccessfulMessage);
}
public void inputToPasswordTextbox(String wrongPassword) {
	waitForElementVisible(driver, passwordTextbox);
	sendKeyToElement(driver, passwordTextbox, wrongPassword);
	
}


}
