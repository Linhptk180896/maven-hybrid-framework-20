package pageObjects.jQueryUploadFiles;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.uploadFiles.HomePageUploadFilePageUI;

public class HomePageUploadFileObject extends BasePage {
	//Tạo constructor để new driver khi gọi hàm trong class LoginPageObject
	WebDriver driver;
	public HomePageUploadFileObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isFileLoadedByName(String fileName) {
		waitForElementVisible(driver, HomePageUploadFilePageUI.FILE_LOADED_LINK, fileName);
		return isElementDisplayed(driver, HomePageUploadFilePageUI.FILE_LOADED_LINK, fileName);
		
	}
	
	
	public void clickToStartButton() {
		List<WebElement> startButtons = getListWebElement(driver, HomePageUploadFilePageUI.START_BUTTON);
		for (WebElement button : startButtons) {
			button.click();
		}
		
	}
	
	
	public boolean isFileLinkUploadedByName(String fileName) {
		waitForElementVisible(driver, HomePageUploadFilePageUI.FILE_UPLOADED_LINK, fileName);
		return isElementDisplayed(driver, HomePageUploadFilePageUI.FILE_UPLOADED_LINK, fileName);
		
		
	}
	public boolean isFileImageUploadedByName(String fileName) {
		waitForElementVisible(driver, HomePageUploadFilePageUI.IMAGE_UPLOADED, fileName);
		return isImageLoaded(driver,HomePageUploadFilePageUI.IMAGE_UPLOADED,  fileName);
	}
	
	
	
	

}
