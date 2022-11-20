package pageUIs.jQuery.dataTable;

public class HomePagePageUI {
	public final static String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']//a[text()='%s']";
	public final static String HEADER_TEXTBOX_BY_LABEL = "xpath=//div[text()='%s']/parent::div/following-sibling::input";
	public final static String PAGINATION_PAGE_ACTIVED_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']//a[@class='qgrd-pagination-page-link active' and text()='%s']";
	public final static String TOTAL_PAGINATION = "xpath=//ul[@class='qgrd-pagination-ul']//li[@class='qgrd-pagination-page']";
	public final static String PAGINATION_BY_INDEX = "xpath=//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page'][%s]";
	public final static String 	ALL_ROW_EACH_PAGE = "xpath=//div[@class='qgrd-wrap']//tr";
	public final static String 	ALL_COUNTRY_ROW_EACH_PAGE = "xpath=//div[@class='qgrd-wrap']//tr//td[@data-key='country']";
	public final static String 	COLUMN_INDEX_BY_NAME = "xpath=//td[@class='ui-widget-header' and text()='%s']//preceding-sibling::td";
	public final static String 	TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody//tr[%s]//td[%s]//input";
	public final static String 	DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody//tr[%s]//td[%s]//select";
	public final static String 	CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody//tr[%s]//td[%s]//input[@type='checkbox']";
	public final static String 	ICON_NAME_BY_ROW_INDEX = "xpath=//tbody//tr[%s]//button[@title='%s']";
	public final static String OPTION_IN_DROPDOWN = "xpath=//tbody//tr[%s]//td[%s]//select//option";
	public final static String LOAD_BUTTON = "xpath=//button[@id='btnLoad']";
	
}
