package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level03_Login_Apply_BasePage_PageObject extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String existingEmail;
	String notFoundEmail;
	String invalidEmail;
//	BasePage basePage; //--> kế thừa BasePage rồi nên không cần khai báo
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	String firstName, lastName, password, wrongPassword;
	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Driver at Class test: " + driver.toString());
		homePage = new UserHomePageObject(driver);
//		registerPage = new RegisterPageObject(driver);
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		existingEmail = "afc" + getRandomNumber() + "@gmail.com";
		invalidEmail = "ggff@gfff@d.com";
		notFoundEmail = "linh" + getRandomNumber() + "@hotmail.com";
		wrongPassword ="32145600";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
//		basePage = new BasePage(); --> kế thừa BasePage rồi nên không cần khởi tạo
		
		//Pre-Condition
		//Bê testcase Register successfully từ bên Register sang làm pre-conditon
		System.out.println("Pre-Condition - Step 01: Click to Register link");
		homePage.openRegisterPage();
		//Click register link -> nhảy qua trang register
		UserRegisterPageObject registerPage = new UserRegisterPageObject(driver);
		
		
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
		homePage = new UserHomePageObject(driver);
	  }
	
	
	@Test
	public void Login_01_Emty_Data() {
		homePage.openLoginPage();
		//chuyển sang màn Login thì phải new pageObject của Login lên 
		loginPage = new UserLoginPageObject(driver);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
		
	
	}
	
	@Test
	public void Login_02_Invalid_Email() {
		homePage.openLoginPage();
		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");

		
		
	}
	
	@Test
	public void Login_03_Email_Not_Register() {
		homePage.openLoginPage();
		loginPage.inputToEmailTextbox(notFoundEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
		
	}
	
	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		homePage.openLoginPage();
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
		}
	

	@Test
	public void Login_05_Existing_Email_Wrong_Password() {
		homePage.openLoginPage();
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(wrongPassword);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		

	}
	
	@Test
	public void Login_06_Existing_Email_Correct_Password() {
		homePage.openLoginPage();
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		//login thành công thì vào màn homepage
		homePage = new UserHomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
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
