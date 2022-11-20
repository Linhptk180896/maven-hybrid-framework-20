package pageObject.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerWordpress;
import pageUIs.wordpress.admin.AdminAddPostPageUI;

public class AdminAddNewPostPageObject  extends BasePage{
	WebDriver driver;

	public AdminAddNewPostPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToPostTitle(String postTitle) {
		waitForElementVisible(driver, AdminAddPostPageUI.POST_TITLE);
		sendKeyToElement(driver, AdminAddPostPageUI.POST_TITLE, postTitle);
		
	}

	public void inputToPostBody(String postBody) {
		//CLICK
		waitForElementClickable(driver, AdminAddPostPageUI.POST_BODY_BEFORE_CLICK);
		clickToElement(driver, AdminAddPostPageUI.POST_BODY_BEFORE_CLICK);
		//SENDKEY
		waitForElementVisible(driver, AdminAddPostPageUI.POST_BODY_AFRER_CLICK);
		sendKeyToElement(driver, AdminAddPostPageUI.POST_BODY_AFRER_CLICK, postBody);		
	}

	public void clickOnPrePublishButton(String buttonName) {
		waitForElementClickable(driver, AdminAddPostPageUI.PRE_PUBLISH_OR_UPDATE_BUTTON, buttonName);
		clickToElement(driver, AdminAddPostPageUI.PRE_PUBLISH_OR_UPDATE_BUTTON, buttonName);
	}

	public void clickOnPublishButton() {
		waitForElementClickable(driver, AdminAddPostPageUI.PUBLISH_BUTTON);
		clickToElement(driver, AdminAddPostPageUI.PUBLISH_BUTTON);		
	}

	public boolean isPostPublishedMessageDisplayed(String actionText) {
		waitForElementVisible(driver, AdminAddPostPageUI.POST_PUBLISHED_OR_UPDTED_MESSAGE,actionText);
		return isElementDisplayed(driver, AdminAddPostPageUI.POST_PUBLISHED_OR_UPDTED_MESSAGE,actionText);
	}
	
	public AdminSearchPostPageObject openSearchPostPageUrl(String adminSearchPostUrl) {
		getUrl(driver, adminSearchPostUrl);
		return PageGeneratorManagerWordpress.getAdminSearchPostPage(driver);
	}

	public void enterToAddNewPostTitle(String editPostTitle) {
		waitForElementVisible(driver, AdminAddPostPageUI.POST_TITLE);
		sendKeyToElement(driver, AdminAddPostPageUI.POST_TITLE, editPostTitle);
	}

	public void enterToAddNewPostBody(String editPostBody) {
		waitForElementVisible(driver, AdminAddPostPageUI.POST_BODY_AFRER_CLICK);
	clearValueInTextboxByPressKey(driver, AdminAddPostPageUI.POST_BODY_AFRER_CLICK, editPostBody);
		sendKeyToElement(driver, AdminAddPostPageUI.POST_BODY_AFRER_CLICK, editPostBody);
	}

	public void clickOnUpdateButton(String buttonName) {
		waitForElementClickable(driver, AdminAddPostPageUI.PRE_PUBLISH_OR_UPDATE_BUTTON, buttonName);
		clickToElement(driver, AdminAddPostPageUI.PRE_PUBLISH_OR_UPDATE_BUTTON, buttonName);
	}

	public void isUpdatedPostMessageIsDisplayed(String actionText) {
		waitForElementVisible(driver, AdminAddPostPageUI.POST_PUBLISHED_OR_UPDTED_MESSAGE, actionText);
		waitForElementVisible(driver, AdminAddPostPageUI.POST_PUBLISHED_OR_UPDTED_MESSAGE, actionText);
	}


}
