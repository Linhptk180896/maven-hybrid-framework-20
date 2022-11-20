package pageUIs.wordpress.user;

public class UserHomePageUI {
	public static final String DYNAMIC_POST_TITLE= "xpath=//article//h2[@class='entry-title']/a[text()='%s']";
	public static final String DYNAMIC_POST_BODY= "xpath=//article//h2[@class='entry-title']/a[text()='%s']/ancestor::header[@class='entry-header']//following-sibling::div[@class='entry-content']/p[text()='%s']";
	public static final String DYNAMIC_POST_AUTHOR= "xpath=//article//h2[@class='entry-title']/a[text()='%s']//ancestor::header//div[@class='entry-meta']//span[@class='author vcard']/a[text()='%s']";
	public static final String SEARCH_TEXTBOX= "xpath=//section[@class='widget widget_block widget_search']//input[@type='search']";
	public static final String SEARCH_BUTTON= "xpath=//section[@class='widget widget_block widget_search']//button[@type='submit']";
	public static final String NOTHING_FOUND_MESSAGE= "xpath=//h1[@class='page-title' and text()='Nothing Found']";


}
