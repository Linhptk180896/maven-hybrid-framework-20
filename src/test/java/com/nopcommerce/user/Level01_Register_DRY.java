package com.nopcommerce.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level01_Register_DRY {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress;
	
	
	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		emailAddress = "afc" + getRandomNumber() + "@gmail.com";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		
	  }
	
	
	@Test
	public void TC_01_Register_Emty_Data() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.xpath("//button[@name='register-button']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='FirstName-error']")).getText(), "First name is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='LastName-error']")).getText(), "Last name is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(), "Email is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Password-error']")).getText(), "Password is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ConfirmPassword-error']")).getText(), "Password is required.");
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("123#333.com");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@name='register-button']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Success() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@name='register-button']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector("a.ico-logout")).click();
	}
	
	@Test
	public void TC_4_Register_Existing_Email() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@name='register-button']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']//li")).getText(), "The specified email already exists");
	}
	
	@Test
	public void TC_5_Register_Password_Less_Than_6_Chars() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error p")).getText(), "Password must meet the following rules:");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error li ")).getText(), "must have at least 6 characters");

	}
	
	@Test
	public void TC_6_Register_Invalid_Confirm_Password() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("12345");
		Assert.assertEquals(driver.findElement(By.cssSelector(" span#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");

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
