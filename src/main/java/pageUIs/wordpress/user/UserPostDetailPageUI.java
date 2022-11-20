package pageUIs.wordpress.user;

public class UserPostDetailPageUI {
	public static final String DYNAMIC_POST_TITLE= "xpath=//article//h1[@class='entry-title' and text()='%s']";
	public static final String DYNAMIC_POST_BODY= "xpath=//h1[@class='entry-title' and text()='%s']//ancestor::article//div[@class='entry-content']/p[text()='%s']";
	public static final String DYNAMIC_POST_AUTHOR= "xpath=//h1[@class='entry-title' and text()='%s']//ancestor::article//span[@class='author vcard']/a[text()='%s']";


}
