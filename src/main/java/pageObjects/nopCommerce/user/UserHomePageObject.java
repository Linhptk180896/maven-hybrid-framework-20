package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserHomePageUI;

// pageObject chứa các actions muốn thực hiện trong 1 page

public class UserHomePageObject extends BasePage {
	private WebDriver driver;
	
	public UserHomePageObject(WebDriver driver) {
		System.out.println("Driver at constructor : " + driver.toString());

		this.driver = driver;
	}
	
	
//	@Step("Navigate to Register page")
	public UserRegisterPageObject openRegisterPage() {
	System.out.println("Driver at Wait element: " + driver.toString());
	waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
	clickToElement(driver, UserHomePageUI.REGISTER_LINK);
//	return new RegisterPageObject(driver); //-> Nếu chỉ có dòng new này mà không return thì sẽ không chạy được nên phải return
	return  PageGeneratorManager.getRegisterPage(driver);
	//vì biến EMAIL_TEXTBOX là static nên có thể truy cập đc từ tên class.tên biến
	//Nếu biến EMAIL_TEXTBOX không là static thì phải khởi tạo new HomePageUI() hoặc extends HomePageUI thì mới truy cập đc
	}

	
//	@Step("Navigate to Login page")

	public UserLoginPageObject openLoginPage() {
		waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK);
		clickToElement(driver, UserHomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}

//	@Step("Navigate to my account page")
	
	

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		 
	}

	
//	@Step("Verify 'MyAccount' link is displayed ")

	public UserCustomerInfoPageObject openMyAccountPage() {
		waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerInfoPage(driver);
	}


	}

	

	


