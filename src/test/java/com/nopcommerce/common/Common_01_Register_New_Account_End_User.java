package com.nopcommerce.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register_New_Account_End_User extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	//Hai data email và password phải set static để có thể share đc data cho class Level16_Share_Data
	public static String email;
	public static String password;
	String notFoundEmail;
	String invalidEmail;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	String firstName, lastName;
	
	//KHÔNG DÙNG BEFORE SUITE VÌ BEFORE SUITE THÌ SẼ KHÔNG APPLY ĐC PARAMETER
	@Parameters("browser")
	@BeforeTest
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
		
		log.info("Register - Step 01: Navigate to Register Page");
		registerPage = homePage.openRegisterPage();
		
		log.info("Register - Step 02: Enter to First Name Textbox with value = " + firstName);
		registerPage.inputToFirstNameTextbox(firstName);
		
		log.info("Register - Step 03: Enter to Last Name Textbox with value = " + lastName);
		registerPage.inputToLastNameTextbox(lastName);
		
		log.info("Register - Step 04: Enter to Existing Email Textbox with value = " + email);
		registerPage.inputToEmailTextbox(email);
		
		log.info("Register - Step 05: Enter to password Textbox with value = " + password);
		registerPage.inputToPasswordTextbox(password);
		
		log.info("Register - Step 06: Enter to confirm password  Textbox with value = " + password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		log.info("Register - Step 07: Click To Register Button");
		registerPage.clickToRegisterButton();
		
		log.info("Register - Step 08: Verify register success message is displyed");
		verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed...");
		
		log.info("Register - Step 09: Click to logout button");
		registerPage.clickToLogoutLink();
	
		
	}
	
	
	
	  @AfterTest
	  public void afterClass() {
		  driver.quit();
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
