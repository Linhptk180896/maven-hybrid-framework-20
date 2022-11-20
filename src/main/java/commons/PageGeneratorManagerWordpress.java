package commons;

import org.openqa.selenium.WebDriver;

import pageObject.wordpress.admin.AdminAddNewPostPageObject;
import pageObject.wordpress.admin.AdminDashboardPageObject;
import pageObject.wordpress.admin.AdminLoginPageObject;
import pageObject.wordpress.admin.AdminSearchPostPageObject;
import pageObject.wordpress.user.UserHomePageObject;
import pageObject.wordpress.user.UserPostDetailPageObject;

public class PageGeneratorManagerWordpress {
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	public static AdminSearchPostPageObject getAdminSearchPostPage(WebDriver driver) {
		return new AdminSearchPostPageObject(driver);
	}

	public static AdminAddNewPostPageObject getAdminAddNewPostPage(
	WebDriver driver) {
		return new AdminAddNewPostPageObject(driver);
	}

	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	public static UserPostDetailPageObject getUserPostDetailPage(WebDriver driver) {
		return new UserPostDetailPageObject(driver);
	}


	
	

}
