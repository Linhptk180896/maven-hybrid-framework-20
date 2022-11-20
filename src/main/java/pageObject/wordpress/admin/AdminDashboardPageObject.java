package pageObject.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerWordpress;
import pageUIs.wordpress.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
	WebDriver driver;
	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public AdminSearchPostPageObject clickOnPostMenu() {
		waitForElementClickable(driver, AdminDashboardPageUI.POST_MENU);
		clickToElement(driver, AdminDashboardPageUI.POST_MENU);
		return PageGeneratorManagerWordpress.getAdminSearchPostPage(driver);
		
	}

}
