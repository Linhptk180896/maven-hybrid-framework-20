package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level03_Register_Apply_BasePage_PageObject_PageGenerator extends BasePage{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress;
//	BasePage basePage; //--> kế thừa BasePage rồi nên không cần khai báo
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	String firstName, lastName, password;
	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Driver at Class test: " + driver.toString());
		homePage = new UserHomePageObject(driver);
		
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		emailAddress = "afc" + getRandomNumber() + "@gmail.com";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//Mở URL lên nó qua trang homepage
		
		driver.get("https://demo.nopcommerce.com/");
		UserHomePageObject homepage = new UserHomePageObject(driver);
		
//		basePage = new BasePage(); --> kế thừa BasePage rồi nên không cần khởi tạo
	  }
	
	
	@Test
	public void Register_01_Emty_Data() {
		//viết những hàm giả tương ứng bám theo cái flow của test case
		//Viết hàm để đại diện cho các action tương ứng
		System.out.println("Register_01 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		//Click register link -> nhảy qua trang register
//		registerPage = new RegisterPageObject(driver); --> cho vào hàm homePage.clickToRegisterLink();
		
		System.out.println("Register_01 - Step 02: Click to Register Button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_01 - Step 03: Verify error message displayed");

		Assert.assertEquals(registerPage.getErrorMesssageAtFirstNameTextbox(),"First name is required.");
		Assert.assertEquals(registerPage.getErrorMesssageAtLastNameTextbox(),"Last name is required.");
		Assert.assertEquals(registerPage.getErrorMesssageAtEmailTextbox(),"Email is required.");
		Assert.assertEquals(registerPage.getErrorMesssageAtPasswordTextbox(),"Password is required.");
		Assert.assertEquals(registerPage.getErrorMesssageAtConfirmPasswordTextbox(),"Password is required.");
		
	
	}
	
	@Test
	public void Register_02_Invalid_Email() {
		System.out.println("Register_02 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		//Click register link -> nhảy qua trang register
		UserRegisterPageObject register = new UserRegisterPageObject(driver);
		System.out.println("Register_02 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("123#333.com");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register_02 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_02 - Step 04: Verify error mesage displayed");
		Assert.assertEquals(registerPage.getErrorMesssageAtEmailTextbox(),"Wrong email");
		
	}
	
	@Test
	public void Register_03_Success() {
		System.out.println("Register_03 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		//Click register link -> nhảy qua trang register
//		RegisterPageObject register = new RegisterPageObject(driver);
		
		System.out.println("Register_03 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register_03 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_03 - Step 04: Verify successful mesage displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
		
//		homePage = new HomePageObject(driver);
		 
		System.out.println("Register_03 - Step 05: Click to Logout button");
		registerPage.clickToLogoutLink();
		
	}
	
	@Test
	public void Register_04_Existing_Email() {
		System.out.println("Register_04 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		//Click register link -> nhảy qua trang register
//		 registerPage = new RegisterPageObject(driver);
		System.out.println("Register_04 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register_04 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_04 - Step 04: Verify existing error message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	}
	

	@Test
	public void Register_05_Password_Less_Than_6_Chars() {
		System.out.println("Register_05 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		//Click register link -> nhảy qua trang register
//		registerPage = new RegisterPageObject(driver);
		System.out.println("Register_05 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");
		
		System.out.println("Register_05 - Step 03: Click to Register button");
		Assert.assertEquals( registerPage.getErrorMesssageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
		

	}
	
	@Test
	public void Register_06_Invalid_Confirm_Password() {
		System.out.println("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();
		//Click register link -> nhảy qua trang register
//		registerPage = new RegisterPageObject(driver);
		System.out.println("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		System.out.println("Register Page - Step 03: Input to required fields");
		Assert.assertEquals( registerPage.getErrorMesssageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");

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
