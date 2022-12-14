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

public class Level02_Register_Apply_BasePage_II_Encapsulation {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress;
	BasePage basePage; 
	
	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		emailAddress = "afc" + getRandomNumber() + "@gmail.com";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		basePage = new BasePage();
	  }
	
	
	@Test
	public void TC_01_Register_Emty_Data() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.waitForElementClickable(driver, "//button[@name='register-button']");
		basePage.clickToElement(driver, "//button[@name='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", "123#333.com");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		basePage.clickToElement(driver, "//button[@name='register-button']");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Success() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		basePage.clickToElement(driver, "//button[@name='register-button']");
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
		basePage.clickToElement(driver, "//a[@class='ico-logout']");
	}
	
	@Test
	public void TC_4_Register_Existing_Email() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		basePage.clickToElement(driver, "//button[@name='register-button']");
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='message-error validation-summary-errors']//li"), "The specified email already exists");
	}
	
	@Test
	public void TC_5_Register_Password_Less_Than_6_Chars() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "123");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']//p"), "Password must meet the following rules:");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']//li"), "must have at least 6 characters");

	}
	
	@Test
	public void TC_6_Register_Invalid_Confirm_Password() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "12345");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");

	}	
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	
	  @AfterClass
	  public void afterClass() {
	  }

}


//L??u ?? khi vi???t h??m
//1--Access Modifier: public/protected/default/private
//2--Ki???u d??? li???u tr??? v???: public/void/String/boolean/...Ki???u d??? li???u tr??? v??? s??? li??n quan ?????n ch???c n??ng trong th??n h??m
//3--T??n h??m: ?????t t??n  tu??n theo ch???c n??ng ??ang c???n vi???t.Convention tu??n theo chu???n c???a ng??n ng??? l???p trinh java (camelCase)
//4--Tham s???: C?? tham s??? ho???c kh??ng c?? tham s???, t??y v??o ch???c n??ng c???n vi???t
//5--Return ki???u d??? li???u tr??? v??? cho h??m: N???u c?? return d??? li???u th?? s??? kh???p v???i ki???u d??? li???u ??? ?? 2. V?? return l?? d??ng cu???i c??ng ????? k???t th??c h??m.
//----------------
//Abstract page/Base page: l?? 1 class d??ng chung ???? wrapper l???i functions c???a selenium 
