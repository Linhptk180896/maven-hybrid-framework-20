package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level02_Register_Apply_BasePage_III_Extends extends BasePage{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress;
//	BasePage basePage; //--> kế thừa BasePage rồi nên không cần khai báo
	
	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		emailAddress = "afc" + getRandomNumber() + "@gmail.com";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
//		basePage = new BasePage(); --> kế thừa BasePage rồi nên không cần khởi tạo
	  }
	
	
	@Test
	public void TC_01_Register_Emty_Data() {
		 waitForElementClickable(driver, "//a[@class='ico-register']");
		 clickToElement(driver, "//a[@class='ico-register']");
		 waitForElementClickable(driver, "//button[@name='register-button']");
		 clickToElement(driver, "//button[@name='register-button']");

		Assert.assertEquals( getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals( getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals( getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals( getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals( getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		 waitForElementClickable(driver, "//a[@class='ico-register']");
		 clickToElement(driver, "//a[@class='ico-register']");
		 sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		 sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		 sendKeyToElement(driver, "//input[@id='Email']", "123#333.com");
		 sendKeyToElement(driver, "//input[@id='Password']", "123456");
		 sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		 clickToElement(driver, "//button[@name='register-button']");
		Assert.assertEquals( getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Success() {
		 waitForElementClickable(driver, "//a[@class='ico-register']");
		 clickToElement(driver, "//a[@class='ico-register']");
		 sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		 sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		 sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		 sendKeyToElement(driver, "//input[@id='Password']", "123456");
		 sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		 clickToElement(driver, "//button[@name='register-button']");
		Assert.assertEquals( getElementText(driver, "//div[@class='result']"), "Your registration completed");
		 clickToElement(driver, "//a[@class='ico-logout']");
	}
	
	@Test
	public void TC_4_Register_Existing_Email() {
		 waitForElementClickable(driver, "//a[@class='ico-register']");
		 clickToElement(driver, "//a[@class='ico-register']");
		 sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		 sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		 sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		 sendKeyToElement(driver, "//input[@id='Password']", "123456");
		 sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		 clickToElement(driver, "//button[@name='register-button']");
		Assert.assertEquals( getElementText(driver, "//div[@class='message-error validation-summary-errors']//li"), "The specified email already exists");
	}
	
	@Test
	public void TC_5_Register_Password_Less_Than_6_Chars() {
		 waitForElementClickable(driver, "//a[@class='ico-register']");
		 clickToElement(driver, "//a[@class='ico-register']");
		 sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		 sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		 sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		 sendKeyToElement(driver, "//input[@id='Password']", "123");
		 sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
		Assert.assertEquals( getElementText(driver, "//span[@id='Password-error']//p"), "Password must meet the following rules:");
		Assert.assertEquals( getElementText(driver, "//span[@id='Password-error']//li"), "must have at least 6 characters");

	}
	
	@Test
	public void TC_6_Register_Invalid_Confirm_Password() {
		 waitForElementClickable(driver, "//a[@class='ico-register']");
		 clickToElement(driver, "//a[@class='ico-register']");
		 sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		 sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		 sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		 sendKeyToElement(driver, "//input[@id='Password']", "123456");
		 sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "12345");
		Assert.assertEquals( getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");

	}	
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
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
