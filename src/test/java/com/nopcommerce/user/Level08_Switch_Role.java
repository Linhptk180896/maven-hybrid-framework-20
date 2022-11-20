package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstans;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level08_Switch_Role extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String existingEmail;
	String notFoundEmail;
	String userEmailAddress;
//	BasePage basePage; //--> kế thừa BasePage rồi nên không cần khai báo
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	
	String firstName, lastName, userPassword, wrongPassword, adminEmailAddress, adminPassword;
	
	
	@Parameters("browser")
	@BeforeClass
	  public void beforeClass(String browserName) {
		//Chạy vào hàm getBrowser ở baseTest rồi nhưng biến driver của class  Level03_Login_Apply_BasePage_PageObject_MultiBrowser chưa 
		//đc khởi tạo nên sẽ lỗi 
		//Vì thế bên BaseTest phải return driver
		//và phải gán driver của Level03_Login_Apply_BasePage_PageObject_MultiBrowser lại thì mới mở đc browser
		
		driver = getBrowser(browserName);
		userHomePage = PageGeneratorManager.getHomePage(driver);
		
		
		firstName = "Automation";
		lastName = "FC";
		userPassword = "123456";
		existingEmail = "afc" + getRandomNumber() + "@gmail.com";
		userEmailAddress = "yangyang@gmail.com";
		notFoundEmail = "linh" + getRandomNumber() + "@hotmail.com";
		wrongPassword ="32145600";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword ="admin";
		
		
//		basePage = new BasePage(); --> kế thừa BasePage rồi nên không cần khởi tạo
		userHomePage = PageGeneratorManager.getHomePage(driver);
		
	  }
	
	
	
	@Test
	public void Role_01_User_To_Admin() {
		userLoginPage =	userHomePage.openLoginPage();
		//login as user role
		userLoginPage.LoginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		
		//homepage -> cus info
		userCustomerInfoPage = userHomePage.openMyAccountPage();
		
		// cus info -> logout
		userHomePage = userCustomerInfoPage.clickToLogOutLinkAtUserPage(driver);
		
		//user home page -> open admin url -> login page (admin)
		userHomePage.getUrl(driver, GlobalConstans.ADMIN_DEV_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPageObject(driver);
		
		//login as admin role
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderIsDisplayed());
		
		//dashboard -> log out -> login (admin)
		adminLoginPage = adminDashboardPage.clickToLogOutLinkAtAdminPage(driver);
		
	}
	
	@Test
	public void Role_02_Admin_To_User() {
		//login page( admin)  -> open url user -> 
		adminLoginPage.getUrl(driver, GlobalConstans.USER_TESTING_URL);
		userHomePage = PageGeneratorManager.getHomePage(driver);
		userLoginPage = userHomePage.openLoginPage();
		userHomePage = userLoginPage.LoginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		
		
		
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
