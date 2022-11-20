package pageObject.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObject.wordpress.admin.AdminDashboardPageObject;
import pageUIs.wordpress.user.UserPostDetailPageUI;

public class UserPostDetailPageObject extends BasePage{
WebDriver driver;
	public UserPostDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostDetailWithTitleIsDisplayed(String postTitle) {
		waitForElementVisible(driver, UserPostDetailPageUI.DYNAMIC_POST_TITLE, postTitle);
		return isElementDisplayed(driver, UserPostDetailPageUI.DYNAMIC_POST_TITLE, postTitle);
	}
//
//	public void isPostDetailWithDateIsDisplayed() {
//		// TODO Auto-generated method stub
//		
//	}

	public boolean isPostDetailWithBodyIsDisplayed(String postTitle, String postBody) {
		waitForElementVisible(driver, UserPostDetailPageUI.DYNAMIC_POST_BODY,postTitle, postBody);
		return isElementDisplayed(driver, UserPostDetailPageUI.DYNAMIC_POST_BODY,postTitle, postBody);
		
	}

	public boolean isPostDetailWithAuthorIsDisplayed(String postTitle, String postAuthor) {
		waitForElementVisible(driver, UserPostDetailPageUI.DYNAMIC_POST_AUTHOR, postTitle, postAuthor);
		return isElementDisplayed(driver, UserPostDetailPageUI.DYNAMIC_POST_AUTHOR, postTitle,postAuthor);
		
	}

	public String getPostBodyInPostDetail(String postTitle,String postBody) {
		waitForElementVisible(driver, UserPostDetailPageUI.DYNAMIC_POST_BODY,postTitle, postBody);
		String body = getElementText(driver, UserPostDetailPageUI.DYNAMIC_POST_BODY, postTitle,postBody);
		System.out.println("postAuthor = " + body);

		return getElementText(driver, UserPostDetailPageUI.DYNAMIC_POST_BODY,postTitle, postBody);
		
		
	}
	public String getPostAuthorInPostDetail(String postTitle,String postAuthor) {
		waitForElementVisible(driver, UserPostDetailPageUI.DYNAMIC_POST_AUTHOR,postTitle, postAuthor);
		String author =  getElementText(driver, UserPostDetailPageUI.DYNAMIC_POST_AUTHOR,postTitle, postAuthor);
		System.out.println("postAuthor = " + author );

		return getElementText(driver, UserPostDetailPageUI.DYNAMIC_POST_AUTHOR,postTitle, postAuthor);
		
		
	}
	public String getPostTitleInPostDetail(String postTitle) {
		waitForElementVisible(driver, UserPostDetailPageUI.DYNAMIC_POST_TITLE, postTitle);
		String title =  getElementText(driver, UserPostDetailPageUI.DYNAMIC_POST_TITLE, postTitle);
		System.out.println("postTitle = " + title );
		return getElementText(driver, UserPostDetailPageUI.DYNAMIC_POST_TITLE, postTitle);
		
		
	}



}
