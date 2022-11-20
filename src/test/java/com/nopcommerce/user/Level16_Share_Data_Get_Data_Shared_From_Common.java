package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_New_Account_End_User;

import commons.BaseTest;
import commons.PageGeneratorManager;

import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;


public class Level16_Share_Data_Get_Data_Shared_From_Common extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email;
	String notFoundEmail;
	String invalidEmail;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	String firstName, lastName, password, wrongPassword;
	
	//-----------CASE 01: TẠO 1 CLASS RIÊNG ĐĂNG KÝ SẴN DATA TEST CHUNG -> RỒI LẤY DATA TEST ĐÓ APPLY CHO CÁC TESTCASE KHÁC ----------------
	@Parameters("browser")
	@BeforeClass
	  public void beforeClass(String browserName) {
		//Chạy vào hàm getBrowser ở baseTest rồi nhưng biến driver của class  Level03_Login_Apply_BasePage_PageObject_MultiBrowser chưa 
		//đc khởi tạo nên sẽ lỗi 
		//Vì thế bên BaseTest phải return driver
		//và phải gán driver của Level03_Login_Apply_BasePage_PageObject_MultiBrowser lại thì mới mở đc browser
		
		driver = getBrowser(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		//Lấy data email - password từ class Common_01_Register_New_Account
		email = Common_01_Register_New_Account_End_User.email;
		password = Common_01_Register_New_Account_End_User.password;
		
		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getHomePage(driver);
		log.info("Login - Step 01: Navigate to open login page");
		loginPage =	homePage.openLoginPage();
		
		log.info("Login - Step 02: Enter to Email Textbox with value = " + email);
		loginPage.inputToEmailTextbox(email);
		
		log.info("Login - Step 03: Enter to Password Textbox with value = " + password);
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Login - Step 04: Click on Login Button ");
		homePage= loginPage.clickToLoginButton();
		
		log.info("Login - Step 05: Verify My Account Link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed()); 
		
		log.info("Login - Step 06: Navigate to my account page");
		customerInfoPage = homePage.openMyAccountPage();
		
		log.info("Login - Step 07: Verify CustomerInfoPage is Displayed" );
		verifyTrue(customerInfoPage.isCustomerInfoPageDisplayed());
		
		
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
