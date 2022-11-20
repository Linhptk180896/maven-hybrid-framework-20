package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage{
	WebDriver driver;
	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
		
	}
	 public boolean isDashboardHeaderIsDisplayed() {
		 waitForAllElementVisible(driver, AdminDashboardPageUI.DASHBOARD_HEADER_TEXT);
		 return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_HEADER_TEXT);
	 }
}
