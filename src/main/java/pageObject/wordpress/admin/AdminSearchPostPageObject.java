package pageObject.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerWordpress;
import pageObject.wordpress.user.UserHomePageObject;
import pageUIs.wordpress.admin.AdminSearchPostPageUI;

public class AdminSearchPostPageObject extends BasePage {
	WebDriver driver;

	public AdminSearchPostPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminAddNewPostPageObject clickOnAddNewButton() {
		waitForElementClickable(driver, AdminSearchPostPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminSearchPostPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManagerWordpress.getAdminAddNewPostPage(driver);		
	}

	public boolean isNewPublishedPostIsDisplayed(String rowIndex, String columnName) {
		//1. Get index column depends on columnName
		int columnIndexByName = getElementSize(driver, AdminSearchPostPageUI.COLUMN_BY_NAME, columnName) + 1;
//		getElementText(driver, AdminSearchPostPageUI.DYNAMIC_VALUE_AT_COLUMN_NAME_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndexByName));
		return isElementDisplayed(driver, AdminSearchPostPageUI.DYNAMIC_VALUE_AT_COLUMN_NAME_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndexByName));
	}

	public void inputToSearchTextbox(String searchValue) {
		waitForElementVisible(driver,AdminSearchPostPageUI.SEARCH_POST_TEXTBOX );
		sendKeyToElement(driver, AdminSearchPostPageUI.SEARCH_POST_TEXTBOX, searchValue);
		
	}

	public void clickOnSearchPostButton() {
		waitForElementClickable(driver, AdminSearchPostPageUI.SEARCH_POST_BUTTON);
		clickToElement(driver, AdminSearchPostPageUI.SEARCH_POST_BUTTON);
	}

	public boolean isPostTitleIsDisplayed( String columnIdName,String cellValue) {
		int columnIndexByIdName = getElementSize(driver, AdminSearchPostPageUI.DYNAMIC_COLUMN_BY_COLUMN_ID, columnIdName);
		System.out.println("columnIndexByIdName " + columnIndexByIdName);
		waitForElementVisible(driver, AdminSearchPostPageUI.DYNAMIC_SEARCH_RESULT_BY_ROW_INDEX_AND_CELLVALUE,  String.valueOf(columnIndexByIdName), cellValue );
		return isElementDisplayed(driver, AdminSearchPostPageUI.DYNAMIC_SEARCH_RESULT_BY_ROW_INDEX_AND_CELLVALUE,  String.valueOf(columnIndexByIdName),cellValue);
	}

	public boolean isPostAuthorIsDisplayed( String columnIdName,String cellValue) {
		int columnIndexByIdName = getElementSize(driver, AdminSearchPostPageUI.DYNAMIC_COLUMN_BY_COLUMN_ID, columnIdName)+1;
		waitForElementVisible(driver, AdminSearchPostPageUI.DYNAMIC_SEARCH_RESULT_BY_ROW_INDEX_AND_CELLVALUE, String.valueOf(columnIndexByIdName),cellValue );
		return isElementDisplayed(driver, AdminSearchPostPageUI.DYNAMIC_SEARCH_RESULT_BY_ROW_INDEX_AND_CELLVALUE,  String.valueOf(columnIndexByIdName),cellValue);
	}

	public AdminAddNewPostPageObject clickOnPostTitle(String rowIndex, String coulumnId, String cellValue) {
		int columnIndexByIdName = getElementSize(driver, AdminSearchPostPageUI.ADMIN_DYNAMIC_COLUMN_INDEX_BY_ID, coulumnId);
		waitForElementVisible(driver, AdminSearchPostPageUI.ADMIN_DYNAMIC_VALUE_BY_ROW_INDEX_AND_CELLVALUE, rowIndex, String.valueOf(columnIndexByIdName), cellValue );
		clickToElement(driver, AdminSearchPostPageUI.ADMIN_DYNAMIC_VALUE_BY_ROW_INDEX_AND_CELLVALUE, rowIndex, String.valueOf(columnIndexByIdName), cellValue );
		return PageGeneratorManagerWordpress.getAdminAddNewPostPage(driver);
	}

	public void selectPostCheckbox(String columnId,String cellValue) {
//		int checkboxIndexByColumnName = getElementSize(driver, AdminSearchPostPageUI.CHECKBOX_BY_COLUMN_ID, columnId) + 1;
		waitForElementClickable(driver, AdminSearchPostPageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_CELLVALUE, cellValue );
		checkToDefaultCheckboxOrRadio(driver, AdminSearchPostPageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_CELLVALUE,cellValue );
	
	}

	public void clickOnBulkActionDropdown() {
		waitForElementClickable(driver, AdminSearchPostPageUI.BULK_ACTION_DROPDOWN);
		clickToElement(driver, AdminSearchPostPageUI.BULK_ACTION_DROPDOWN);
	}

	public void clickOnMoveToTrashButton(String actionName) {
		waitForElementClickable(driver, AdminSearchPostPageUI.DYNAMIC_ACTION_IN_DROPDOWN, actionName);
		clickToElement(driver, AdminSearchPostPageUI.DYNAMIC_ACTION_IN_DROPDOWN, actionName);
		
	}

	public void clickOnApplyButton() {
		waitForElementClickable(driver, AdminSearchPostPageUI.APPLY_BUTTON);
		clickToElement(driver, AdminSearchPostPageUI.APPLY_BUTTON);
	}

	public boolean isMovedToTrashMessageIsDisplayed() {
		waitForElementVisible(driver, AdminSearchPostPageUI.POST_MOVED_TRASH_MESSAGE);		
		return isElementDisplayed(driver, AdminSearchPostPageUI.POST_MOVED_TRASH_MESSAGE);		
	}

	public boolean isNoPostFoundMessageIsDisplayed() {
		waitForElementVisible(driver, AdminSearchPostPageUI.NO_POST_FOUND_MESSAGE);		
		return isElementDisplayed(driver, AdminSearchPostPageUI.NO_POST_FOUND_MESSAGE);		
	}


	

}
