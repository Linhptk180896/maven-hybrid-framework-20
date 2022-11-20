package pageObject.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerWordpress;
import pageUIs.wordpress.user.UserHomePageUI;

public class UserHomePageObject extends BasePage{
	WebDriver driver;
	

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}


	public boolean isPostWithTitleIsDisplayed(String postTitle) {
		waitForElementVisible(driver, UserHomePageUI.DYNAMIC_POST_TITLE,postTitle);
		return isElementDisplayed(driver, UserHomePageUI.DYNAMIC_POST_TITLE,postTitle);
	}


//	public boolean isPostWithDateIsDisplayed() {
//		
//	}


	public boolean isPostWithBodyIsDisplayed(String postTitle, String postBody) {
		waitForElementVisible(driver, UserHomePageUI.DYNAMIC_POST_BODY,postTitle,postBody);
		return isElementDisplayed(driver, UserHomePageUI.DYNAMIC_POST_BODY,postTitle, postBody);	
	}


	public boolean isPostWithAuthorIsDisplayed(String postTitle,String adminUserName) {
		waitForElementVisible(driver, UserHomePageUI.DYNAMIC_POST_AUTHOR,postTitle, adminUserName);
		return isElementDisplayed(driver, UserHomePageUI.DYNAMIC_POST_AUTHOR,postTitle, adminUserName);	
	}


	public UserPostDetailPageObject clickOnPostTitle(String postTitle) {
		waitForElementVisible(driver, UserHomePageUI.DYNAMIC_POST_TITLE, postTitle);
		clickToElement(driver, UserHomePageUI.DYNAMIC_POST_TITLE, postTitle);
		return PageGeneratorManagerWordpress.getUserPostDetailPage(driver);
		
	}


	public void enterToUserSearchPostTexbox(String searchValue) {
		waitForElementVisible(driver, UserHomePageUI.SEARCH_TEXTBOX);
		sendKeyToElement(driver, UserHomePageUI.SEARCH_TEXTBOX,searchValue);
	}


	public void clickOnSearchButton() {
		waitForElementClickable(driver, UserHomePageUI.SEARCH_BUTTON);
		clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);
	}


	public boolean isNothingFoundMessageIsDisplayed() {
		waitForElementVisible(driver, UserHomePageUI.NOTHING_FOUND_MESSAGE);
		return isElementDisplayed(driver, UserHomePageUI.NOTHING_FOUND_MESSAGE);
	}


	
}
