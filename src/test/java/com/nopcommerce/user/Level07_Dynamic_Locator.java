package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level07_Dynamic_Locator extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String existingEmail;
	String notFoundEmail;
	String invalidEmail;
//	BasePage basePage; //--> kế thừa BasePage rồi nên không cần khai báo
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressPageObject addressPage;
	private UserRewardPointPageObject rewardPointPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	String firstName, lastName, password, wrongPassword;
	
	
	@Parameters("browser")
	@BeforeClass
	  public void beforeClass(String browserName) {
		//Chạy vào hàm getBrowser ở baseTest rồi nhưng biến driver của class  Level03_Login_Apply_BasePage_PageObject_MultiBrowser chưa 
		//đc khởi tạo nên sẽ lỗi 
		//Vì thế bên BaseTest phải return driver
		//và phải gán driver của Level03_Login_Apply_BasePage_PageObject_MultiBrowser lại thì mới mở đc browser
		
		driver = getBrowser(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		existingEmail = "afc" + getRandomNumber() + "@gmail.com";
		invalidEmail = "ggff@gfff@d.com";
		notFoundEmail = "linh" + getRandomNumber() + "@hotmail.com";
		wrongPassword ="32145600";
		
		driver.get("https://demo.nopcommerce.com/");
//		basePage = new BasePage(); --> kế thừa BasePage rồi nên không cần khởi tạo
		homePage = PageGeneratorManager.getHomePage(driver);
		
	  }
	
	
	@Test
	public void User_01_Register() {

		registerPage = homePage.openRegisterPage();
		System.out.println("Pre-Condition - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Pre-Condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Pre-Condition- Step 04: Verify successful mesage displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
	
		 
		System.out.println("Pre-Condition - Step 05: Click to Logout button");
		registerPage.clickToLogoutLink();
		//Sau khi click logOutBtn thì business sẽ về HomePageObject vì thế new HomePageObject lên
		//Đoạn new này bị lặp nhưng sẽ tối ưu ở những bài sau
//		homePage = new HomePageObject(driver);
		
	
	}
	
	@Test
	public void User_02_Login() {
		loginPage =	homePage.openLoginPage();
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(password);
		homePage= loginPage.clickToLoginButton();
		//login thành công thì vào màn homepage
//		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		
	}
	
	@Test
	public void User_03_Customer_Page() {
		customerInfoPage = homePage.openMyAccountPage();
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}
	@Test
	public void User_04_Switch_Page() {
		//Knowledge: Nguyên tắc của pageobject, là khởi tạo page object đó lên 
		//Customer Info -> Address
		addressPage = customerInfoPage.openAddressPage(driver);
		
		//Address -> My product review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		//My product review -> Reward Point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		
		//Reward Point -> Address
		addressPage = rewardPointPage.openAddressPage(driver);
		//Address -> Reward Point
		rewardPointPage = addressPage.openRewardPointPage(driver);
		//Reward Point -> My product review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
		
	}
	@Test
	public void User_05_Dynamic_Page_01() {
		//Cach 1 này áp dụng cho trang ÍT page
		//Knowledge: Nguyên tắc của pageobject, là khởi tạo page object đó lên 
		//Customer Info -> Address
		addressPage = (UserAddressPageObject) customerInfoPage.openPagesAtMyAccountByName(driver, "Addresses");	
		//Address -> My product review
		myProductReviewPage = (UserMyProductReviewPageObject) addressPage.openPagesAtMyAccountByName(driver,"My product reviews");
		//My product review -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver,"Reward points");		
		//Reward Point -> Address
		addressPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyAccountByName(driver,"Addresses");
		//Address -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) addressPage.openPagesAtMyAccountByName(driver,"Reward points");
		//Reward Point -> My product review
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPagesAtMyAccountByName(driver,"My product reviews");
		//My product review -> Customer info
		customerInfoPage = (UserCustomerInfoPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Customer info");
	}
	@Test
	public void User_05_Dynamic_Page_02() {
		//cách 2 này áo dụng cho trang NHIỀU page
		//Knowledge: Nguyên tắc của pageobject, là khởi tạo page object đó lên 
		//Customer Info -> Address
		customerInfoPage.openPagesAtMyAccountByPageName(driver, "Addresses");	
		addressPage = PageGeneratorManager.getAddressPage(driver);
		
		//Address -> My product review
		addressPage.openPagesAtMyAccountByPageName(driver,"My product reviews");
		myProductReviewPage = PageGeneratorManager.getMyProductReviewPage(driver);
		//My product review -> Reward Point
		myProductReviewPage.openPagesAtMyAccountByPageName(driver,"Reward points");		
		rewardPointPage = PageGeneratorManager.getRewardPointPage(driver);
		//Reward Point -> Address
		rewardPointPage.openPagesAtMyAccountByPageName(driver,"Addresses");
		addressPage = PageGeneratorManager.getAddressPage(driver);
		//Address -> Reward Point
		addressPage.openPagesAtMyAccountByPageName(driver,"Reward points");
		rewardPointPage = PageGeneratorManager.getRewardPointPage(driver);
		//Reward Point -> My product review
		rewardPointPage.openPagesAtMyAccountByPageName(driver,"My product reviews");
		myProductReviewPage = PageGeneratorManager.getMyProductReviewPage(driver);
		//My product review -> Customer info
		myProductReviewPage.openPagesAtMyAccountByName(driver, "Customer info");
		customerInfoPage = PageGeneratorManager.getCustomerInfoPage(driver);
	}
	
	@Test
	public void User_05_Switch_Role() {
		
		}
	

	
	
	
	
	  @AfterClass
	  public void afterClass() {
	  }

}


//Lưu ý khi viết hàm
//1--Access Modifier: public/protected/default/private
//2--Kiểu dữ liệu trả về: public/void/String/boolean/...Kiểu dữ liệu trả về sẽ liên quan đến chức năng trong thân hàm
//3--Tên hàm: Đặt tên  tuân theo chức năng đang cần viết.Convention tuân theo chuẩn của ngôn ngữ lập trinh java (camelCase)
//4--Tham số: Có tham số hoặc không có tham số, tùy vào chức năng cần viết
//5--Return kiểu dữ liệu trả về cho hàm: Nếu có return dữ liệu thì sẽ khớp với kiểu dữ liệu ở ý 2. Và return là dòng cuối cùng để kết thúc hàm.
//----------------
//Abstract page/Base page: là 1 class dùng chung đã wrapper lại functions của selenium 
