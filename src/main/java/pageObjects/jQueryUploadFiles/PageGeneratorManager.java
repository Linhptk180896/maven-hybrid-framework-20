package pageObjects.jQueryUploadFiles;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePageUploadFileObject getHomepage(WebDriver driver) {
		return new HomePageUploadFileObject(driver);
	}
}
