package pageUIs.wordpress.admin;

public class AdminSearchPostPageUI {
	public static final String ADD_NEW_BUTTON = "xpath=//div[@class='wrap']//a[text()='Add New']";
	public static final String COLUMN_BY_NAME = "xpath=//th[@id='%s']";
	public static final String DYNAMIC_VALUE_AT_COLUMN_NAME_AND_ROW_INDEX = "xpath= //tbody[@id='the-list']//tr[%s]/td[%s]//a[@class='row-title']";
	public static final String SEARCH_POST_TEXTBOX= "xpath=//input[@id='post-search-input']";
	public static final String SEARCH_POST_BUTTON= "xpath=//input[@id='search-submit']";
	public static final String DYNAMIC_COLUMN_BY_COLUMN_ID	= "xpath=//thead//tr//th[@id='%s']";
	public static final String CHECKBOX_BY_COLUMN_ID	= "xpath=//thead//tr/td[@id='%s']";
	public static final String DYNAMIC_VALUE_BY_ROW_INDEX_AND_CELLVALUE= "xpath=//tbody[@id='the-list']//tr[%s]//td[%s]//a[text()='%s']";
	public static final String DYNAMIC_SEARCH_RESULT_BY_ROW_INDEX_AND_CELLVALUE= "xpath=//tbody[@id='the-list']//td[%s]//a[text()='%s']";
	public static final String ADMIN_DYNAMIC_COLUMN_INDEX_BY_ID= "xpath=//thead//*[@id='%s']";
	public static final String ADMIN_DYNAMIC_VALUE_BY_ROW_INDEX_AND_CELLVALUE= "xpath=//tbody[@id='the-list']//tr[%s]//td[%s]//a[text()='%s']";
	public static final String DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_CELLVALUE= "xpath=//tbody[@id='the-list']//a[text()='%s']//ancestor::td/preceding-sibling::th/input";
	public static final String BULK_ACTION_DROPDOWN= "xpath=//select[@id='bulk-action-selector-top']";
	public static final String DYNAMIC_ACTION_IN_DROPDOWN= "xpath=//select[@id='bulk-action-selector-top']//option[text()='%s']";
	public static final String APPLY_BUTTON= "xpath=//input[@id='doaction']";
	public static final String POST_MOVED_TRASH_MESSAGE= "xpath=//div[@id='message']/p[contains(text(),'moved to the Trash.')]";
	public static final String NO_POST_FOUND_MESSAGE= "xpath=//tbody[@id='the-list']//td[@class='colspanchange' and text()='No posts found.']";
	
	
	
}
