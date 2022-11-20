//package com.nopcommerce.user;
//
//import org.testng.annotations.Test;
//
//
//
//import commons.BaseTest;
//import commons.PageGeneratorManager;
//import pageObjects.nopCommerce.user.UserAddressPageObject;
//import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
//import pageObjects.nopCommerce.user.UserHomePageObject;
//import pageObjects.nopCommerce.user.UserLoginPageObject;
//import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
//import pageObjects.nopCommerce.user.UserRegisterPageObject;
//import pageObjects.nopCommerce.user.UserRewardPointPageObject;
//import reportConfig.ExtentManagerV3;
//import reportConfig.ExtentManagerV3;
//
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//
//import static org.testng.Assert.assertEquals;
//
//import java.lang.reflect.Method;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//
//public class Level15_ReportExtendV3 extends BaseTest{
//	WebDriver driver;
//	String projectPath = System.getProperty("user.dir");
//	String existingEmail;
//	String notFoundEmail;
//	String invalidEmail;
////	BasePage basePage; //--> kế thừa BasePage rồi nên không cần khai báo
//	private UserHomePageObject homePage;
//	private UserRegisterPageObject registerPage;
//	private UserLoginPageObject loginPage;
//	private UserCustomerInfoPageObject customerInfoPage;
//	String firstName, lastName, password, wrongPassword;
//	
//	
//	@Parameters("browser")
//	@BeforeClass
//	  public void beforeClass(String browserName) {
//		//Chạy vào hàm getBrowser ở baseTest rồi nhưng biến driver của class  Level03_Login_Apply_BasePage_PageObject_MultiBrowser chưa 
//		//đc khởi tạo nên sẽ lỗi 
//		//Vì thế bên BaseTest phải return driver
//		//và phải gán driver của Level03_Login_Apply_BasePage_PageObject_MultiBrowser lại thì mới mở đc browser
//		
//		driver = getBrowser(browserName);
//		homePage = PageGeneratorManager.getHomePage(driver);
//		
//		
//		firstName = "Automation";
//		lastName = "FC";
//		password = "123456";
//		existingEmail = "afc" + getRandomNumber() + "@gmail.com";
//		invalidEmail = "ggff@gfff@d.com";
//		notFoundEmail = "linh" + getRandomNumber() + "@hotmail.com";
//		wrongPassword ="32145600";
//		
//		driver.get("https://demo.nopcommerce.com/");
////		basePage = new BasePage(); --> kế thừa BasePage rồi nên không cần khởi tạo
//		homePage = PageGeneratorManager.getHomePage(driver);
//		
//	  }
//	
//	
//	@Test
//	public void User_01_Register(Method method) {
//		//Dùng log của extentReport
//		
//		
//		log.info("Register - Step 01: Navigate to Register Page");
//		registerPage = homePage.openRegisterPage();
//		
//		
//		log.info("Register - Step 02: Enter to First Name Textbox with value = " + firstName);
//		registerPage.inputToFirstNameTextbox(firstName);
//		
//		
////		log.info("Register - Step 03: Enter to Last Name Textbox with value = " + lastName);
//		registerPage.inputToLastNameTextbox(lastName);
//		
//		
//		
////		log.info("Register - Step 04: Enter to Existing Email Textbox with value = " + existingEmail);
//		registerPage.inputToEmailTextbox(existingEmail);
//		
//		
//		
////		log.info("Register - Step 05: Enter to password Textbox with value = " + password);
//		registerPage.inputToPasswordTextbox(password);
//		
//		
//		
////		log.info("Register - Step 06: Enter to confirm password  Textbox with value = " + password);
//		registerPage.inputToConfirmPasswordTextbox(password);
//		
//		
//		
////		log.info("Register - Step 07: Click To Register Button");
//		registerPage.clickToRegisterButton();
//		//Verify thì chụp ảnh vào test report sẽ không đúng. nó sẽ chỉ chụp cái ảnh của step cuối cùng bị fail. còn các step fail khác thì không chụp
//		//Vậy nên muốn chụp ảnh thì dùng assert
//		
//		
//		
////		log.info("Register - Step 08: Verify register success message is displyed");
////		verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed...");
//		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed...");
//		
////		log.info("Register - Step 09: Click to logout button");
////		registerPage.clickToLogoutButton();
////	
//		
//	}
//	
//	
//	@Test
//	public void User_02_Login(Method method) {
//		
////		log.info("Login - Step 01: Navigate to open login page");
//		loginPage =	homePage.openLoginPage();
//		
//		
//		
////		log.info("Login - Step 02: Enter to Email Textbox with value = " + existingEmail);
//		loginPage.inputToEmailTextbox(existingEmail);
//		
//		
//		
//		
////		log.info("Login - Step 03: Enter to Password Textbox with value = " + password);
//		loginPage.inputToPasswordTextbox(password);
//		
//		
//		
//		
////		log.info("Login - Step 04: Click on Login Button ");
//		homePage= loginPage.clickToLoginButton();
//		
//		
//		
////		log.info("Login - Step 05: Verify My Account Link is displayed");
//		Assert.assertFalse(homePage.isMyAccountLinkDisplayed()); 
//		
//		
//		
////		log.info("Login - Step 06: Navigate to my account page");
//		customerInfoPage = homePage.openMyAccountPage();
//		
//		
//		
////		log.info("Login - Step 07: Verify CustomerInfoPage is Displayed" );
//		Assert.assertFalse(customerInfoPage.isCustomerInfoPageDisplayed());
//		
//
//	}
//	  @AfterClass
//	  public void afterClass() {
//	  }
//
//}
//
//
////Lưu ý khi viết hàm
////1--Access Modifier: public/protected/default/private
////2--Kiểu dữ liệu trả về: public/void/String/boolean/...Kiểu dữ liệu trả về sẽ liên quan đến chức năng trong thân hàm
////3--Tên hàm: Đặt tên  tuân theo chức năng đang cần viết.Convention tuân theo chuẩn của ngôn ngữ lập trinh java (camelCase)
////4--Tham số: Có tham số hoặc không có tham số, tùy vào chức năng cần viết
////5--Return kiểu dữ liệu trả về cho hàm: Nếu có return dữ liệu thì sẽ khớp với kiểu dữ liệu ở ý 2. Và return là dòng cuối cùng để kết thúc hàm.
////----------------
////Abstract page/Base page: là 1 class dùng chung đã wrapper lại functions của selenium 
