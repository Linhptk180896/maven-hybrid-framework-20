package pageUIs.nopCommerce.user;

public class BasePageUI {
	public  static final String ADDRESS_LINK = "Xpath=//div[@class='side-2']//a[text()='Addresses']/parent::li";
	public static final String REWARD_POINT_LINK = "XPATH=//div[@class='side-2']//a[text()='Reward points']/parent::li";
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//div[@class='side-2']//a[text()='My product reviews']/parent::li";
	public static final String CUSTOMER_INFO_LINK = "xpath=//div[@class='side-2']//a[text()='Customer info']/parent::li";
	public static final String DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA = "xpath=//div[@class='side-2']//a[text()='%s']/parent::li";
	public static final String LOGOUT_LINK_AT_USER = "css=a[class='ico-logout']";
	public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";
	public final static String INPUT_UPLOAD_FILE = "xpath=//input[@type='file']";
	
	//Pattern Object
	public final static String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public final static String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[contains(text(),'%s')]";
	public final static String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
	public final static String DYNAMIC_CHILD_LOCATOR_TYPE_BY_NAME = "xpath=//select[@name='%s']//option";
	public final static String DYNAMIC_RADIO_BUTTON_BY_TEXT = "xpath=//label[text()='%s']//preceding-sibling::input";
	public final static String DYNAMIC_CHECKBOX_BY_LABEL = "xpath=//label[contains(text(),'%s')]//following-sibling::input";
	
}
