package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.UserDataMapper;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level21_DataDrivenModel extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email;
	String notFoundEmail;
	String invalidEmail;
	
//	BasePage basePage; //--> kế thừa BasePage rồi nên không cần khai báo
	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage; 
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;

	private UserDataMapper userData;
	String firstName, lastName, password, wrongPassword, date, month, year, gender, point;
	
	
	@Parameters({"browserName"})
	@BeforeClass
	  public void beforeClass(String browserName) {
		//Chạy vào hàm getBrowser ở baseTest rồi nhưng biến driver của class  Level03_Login_Apply_BasePage_PageObject_MultiBrowser chưa 
		//đc khởi tạo nên sẽ lỗi 
		//Vì thế bên BaseTest phải return driver
		//và phải gán driver của Level03_Login_Apply_BasePage_PageObject_MultiBrowser lại thì mới mở đc browser
		driver = getBrowser(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		userData = UserDataMapper.getUserData();// khởi tạo hàm map với file json lên 
		
//		firstName = userData.getFirstName();
		firstName = userData.getSubjects().get(0).getName();
		
//		lastName = userData.getLastName();
		lastName = userData.getSubjects().get(1).getName();
		password = userData.getPassword();
		email = userData.getEmailAddress() + getRandomNumber() + "@gmail.com";
		invalidEmail = "ggff@gfff@d.com";
		notFoundEmail = "linh" + getRandomNumber() + "@hotmail.com";
		wrongPassword ="32145600";
		date = userData.getDate();
		month = userData.getMonth();
		year = userData.getYear();
		gender = userData.getGender();
		driver.get("https://demo.nopcommerce.com/");
//		basePage = new BasePage(); --> kế thừa BasePage rồi nên không cần khởi tạo
//		homePage = PageGeneratorManager.getHomePage(driver);
		
	
	  }
	
	
	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to Register Page");
		registerPage = homePage.openRegisterPage();
		
		log.info("Register - Step 02: Select Gender with value = " + gender);
		registerPage.checkToRadioButtonByText(driver, gender);
		
		log.info("Register - Step 02: Enter to First Name Textbox with value = " + firstName);
//		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToTextboxByID(driver, firstName, "FirstName");
		
		log.info("Register - Step 03: Enter to Last Name Textbox with value = " + lastName);
//		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToTextboxByID(driver, lastName, "LastName");
		
		log.info("Register - Step 03.1: Select Date of birth with Date value = " + date);
		registerPage.selectItemInDropdownByName(driver, date, "DateOfBirthDay");
		
		log.info("Register - Step 03.2: Select Date of birth with Month value = " + month);
		registerPage.selectItemInDropdownByName(driver, month, "DateOfBirthMonth");
		
		log.info("Register - Step 03.3: Select Date of birth with Year value = " + year);
		registerPage.selectItemInDropdownByName(driver, year, "DateOfBirthYear");
		
		log.info("Register - Step 04: Enter to  Email Textbox with value = " + email);
//		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToTextboxByID(driver, email, "Email");
		
		log.info("Register - Step 05: Select checkbox Newsletter ");
		registerPage.checkToCheckboxByLabel(driver, "Newsletter");
		
		log.info("Register - Step 05: Enter to password Textbox with value = " + password);
		registerPage.inputToTextboxByID(driver, password, "Password");
		
		log.info("Register - Step 06: Enter to confirm password  Textbox with value = " + password);
		registerPage.inputToTextboxByID(driver, password, "ConfirmPassword");
		
		log.info("Register - Step 07: Click To Register Button");
//		registerPage.clickToRegisterButton();
		registerPage.clickToButtonByText(driver, "Register");
		
		log.info("Register - Step 08: Verify register success message is displyed");
		verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
		
		log.info("Register - Step 09: Click to logout link");
		registerPage.clickToLogoutLink();
	
	
		
	}
	
	
	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Navigate to open login page");
		loginPage =	homePage.openLoginPage();
		
		log.info("Login - Step 02: Enter to Email Textbox with value = " + email);
//		loginPage.inputToEmailTextbox(email);
		loginPage.inputToTextboxByID(driver, email, "Email");
		log.info("Login - Step 03: Enter to Password Textbox with value = " + password);
		loginPage.inputToTextboxByID(driver, password, "Password");
		
		log.info("Login - Step 04: Click on Login Button ");
//		homePage= loginPage.clickToLoginButton();
		homePage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		
		log.info("Login - Step 05: Verify My Account Link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed()); 
		
		log.info("Login - Step 06: Navigate to my account page");
		customerInfoPage = homePage.openMyAccountPage();
		
		log.info("Login - Step 07: Verify CustomerInfoPage is Displayed" );
		verifyFalse(customerInfoPage.isCustomerInfoPageDisplayed());
		
	}

	
	@Test
	public void User_03_MyAccount() {
		log.info("My Account - Step 01: Verify FirstName value is correct" );
		Assert.assertEquals(customerInfoPage.getTextboxAttributeValue(driver, "FirstName", "value"), firstName);
		
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
