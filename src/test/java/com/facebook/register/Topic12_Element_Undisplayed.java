package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTestFacebook;
import pageObject.facebook.LoginPageObject;
import pageObject.facebook.PageGeneratorManager;

public class Topic12_Element_Undisplayed  extends BaseTestFacebook{
	WebDriver driver;
	LoginPageObject loginPage;
	String email ="layngo@gmail.com";
	
	@Parameters({"browser","appUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowser(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}
	
	@Test
	public void TC_01_Verify_Element_Displayed() throws InterruptedException {
		loginPage.clickToCreateAccountButton();
		loginPage.sendKeysToEmailTextbox(email);
		Thread.sleep(3000);
		verifyTrue(loginPage.isConfirmEmailIsDisplayed());
	}
	
		
	
	@Test
	public void TC_02_Verify_Element_Undisplayed_In_DOM() {
		loginPage.sendKeysToEmailTextbox("");
		verifyTrue(loginPage.isConfirmEmailUndisplayed());
		
	}
	
	
	@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
		loginPage.clickCloseIcon();
		verifyTrue(loginPage.isConfirmEmailUndisplayed());
		//Khi close cái form register đi thì Confirm Email không còn trong DOM nữa
		//Nên hàm findElement sẽ bị fail do không tìm thấy Element
		//1- Nó sẽ chờ hết timeout của implicit: 30s
		//2- Nó sẽ đánh fail testcase tại đúng step này luôn 
		//3-Không chạy các step còn lại nữa
		//Verify false: Mong đợi confirm email undisplayed (false)
		//isDisplayed: bản chất ko kiểm tra 1 element không có trong DOM được 
	}
	
	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	
}
