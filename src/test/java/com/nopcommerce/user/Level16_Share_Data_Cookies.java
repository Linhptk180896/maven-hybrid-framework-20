package com.nopcommerce.user;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Cookies;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;



public class Level16_Share_Data_Cookies extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email;
	String notFoundEmail;
	String invalidEmail;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserRegisterPageObject registerPage;

	String firstName, lastName, password, wrongPassword;
	
	//-----------CASE 03: LOGIN BẰNG COOKIES ----------------
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
		email = "afc" + getRandomNumber() + "@gmail.com";
		
		
		driver.get("https://demo.nopcommerce.com/");
//		basePage = new BasePage(); --> kế thừa BasePage rồi nên không cần khởi tạo
		homePage = PageGeneratorManager.getHomePage(driver);
	
		
		log.info("Login - Step 01: Navigate to open login page");
		loginPage =	homePage.openLoginPage();
		
		log.info("Login - Step 01: Set cookie and Reload Page");
		loginPage.setCookies(driver, Common_01_Register_Cookies.loggedCookies);	
		for (Cookie cookie : Common_01_Register_Cookies.loggedCookies) {
			System.out.println("Cookies = " + cookie);
		}
		//SAU KHI SET COOKIES XONG THÌ NHỚ RELOAD LẠI PAGE
		loginPage.refreshCurrentPage(driver);
		
		log.info("Login - Step 05: Verify My Account Link is displayed");
		verifyFalse(homePage.isMyAccountLinkDisplayed()); 
		
//		log.info("Login - Step 06: Navigate to my account page");
//		customerInfoPage = homePage.openMyAccountPage();
//		
//		log.info("Login - Step 07: Verify CustomerInfoPage is Displayed" );
//		verifyFalse(customerInfoPage.isCustomerInfoPageDisplayed());
		
	  }
			
	
	@Test
	public void User_01_Search_Emty_Data() {
		
	}
	
	@Test
	public void User_02_Search_Data() {
		
	}
	
	@Test
	public void User_03_Search_Non_Existing_Data() {
		
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
