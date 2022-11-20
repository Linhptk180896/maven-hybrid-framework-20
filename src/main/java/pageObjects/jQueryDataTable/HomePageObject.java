package pageObjects.jQueryDataTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.dataTable.HomePagePageUI;

public class HomePageObject extends BasePage{
	WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void openPagingByPageNumber(String pageNumber) {
		waitForElementClickable(driver, HomePagePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePagePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
	}
	
	
	public void enterToHeaderTextboxByLabel(String headerLabel, String value) {
		waitForElementVisible(driver, HomePagePageUI.HEADER_TEXTBOX_BY_LABEL, headerLabel);
		sendKeyToElement(driver, HomePagePageUI.HEADER_TEXTBOX_BY_LABEL, value , headerLabel);
	}
	public boolean isPageNumberActived(String pageNumber) {
		waitForElementVisible(driver, HomePagePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
		return isElementDisplayed(driver, HomePagePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
		
	}
	public List<String> getAllValueOfRowAtAllPages() {
		int totalPages = getElementSize(driver, HomePagePageUI.TOTAL_PAGINATION);
//		Set<String> allRowValueOfAllPages = new HashSet<String>(); --> Dùng Set<String> để lọc trùng
		List<String> allRowValueOfAllPages = new ArrayList<String>();
		//Duyệt qua tất cả pages number đang dùng phân trang 
		//Phải dùng for i vì phải truyền index vào locator 
		for (int index = 1; index <= totalPages; index++) {
			waitForElementClickable(driver, HomePagePageUI.PAGINATION_BY_INDEX, String.valueOf(index));
			clickToElement(driver,HomePagePageUI.PAGINATION_BY_INDEX, String.valueOf(index));
			
			//Get text của all row mỗi page đưa vào array list
//			List<WebElement>  allRowElementEachPage = getListWebElement(driver, HomePagePageUI.ALL_ROW_EACH_PAGE);
			List<WebElement>  allRowElementEachPage = getListWebElement(driver, HomePagePageUI.ALL_COUNTRY_ROW_EACH_PAGE);
			for (WebElement eachRow : allRowElementEachPage) {
				allRowValueOfAllPages.add(eachRow.getText());
			}
			}
		
		for (String eachRowValue : allRowValueOfAllPages) {
			
			System.out.println(eachRowValue);
			
		}
		
		return allRowValueOfAllPages;
		
		}
	public void enterToTextboxByColumnAtRowNumber(String rowIndex, String columnName, String inputValue) {
		// Khi chạy hàm thì cột là dynamic nên phải tìm ra index của cột dưa vào tên cột 
		//Get ra tổng số lượng có bao nhiêu anh trên nó,  rồi + 1 để get ra index của cột đó
		int columnIndexByName = getElementSize(driver, HomePagePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		System.out.println("Size = " + getElementSize(driver, HomePagePageUI.COLUMN_INDEX_BY_NAME, columnName));
		System.out.println(columnIndexByName);
		// Get ra input field: //tbody//tr[1]//td[2]//input --> tr[1] là index hàng | td[2] là index cột 
		waitForElementVisible(driver, HomePagePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndexByName));
		sendKeyToElement(driver, HomePagePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, inputValue, rowIndex, String.valueOf(columnIndexByName));
	}
			
	public void selectDropdownByColumnAtRowNumber(String rowIndex, String columnName, String expectedTextItem) {
		int columnIndexByName = getElementSize(driver, HomePagePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePagePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndexByName));
		selectItemInCustomDropdownList(driver, HomePagePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, HomePagePageUI.OPTION_IN_DROPDOWN, expectedTextItem, rowIndex, String.valueOf(columnIndexByName),rowIndex, String.valueOf(columnIndexByName));
	}
	
	public void clickToLoadButton() {
		waitForElementClickable(driver, HomePagePageUI.LOAD_BUTTON);
		clickToElement(driver, HomePagePageUI.LOAD_BUTTON);
	}
	
	public void checkToCheckboxByColumnNameAtRowNumber(String rowIndex, String columnName) {
		int columnIndexByName = getElementSize(driver, HomePagePageUI.COLUMN_INDEX_BY_NAME, columnName) +1;
		waitForElementClickable(driver, HomePagePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndexByName));
		checkToDefaultCheckboxOrRadio(driver, HomePagePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndexByName));
	}

	public void unCheckToCheckboxByColumnNameAtRowNumber(String rowIndex, String columnName) {
		int columnIndexByName = getElementSize(driver, HomePagePageUI.COLUMN_INDEX_BY_NAME, columnName)+1;
		waitForElementClickable(driver, HomePagePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndexByName));
		unCheckToDefaultCheckbox(driver, HomePagePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(columnIndexByName));
		
	}
	public void clickToIconByRowNumber(String rowIndex, String iconName) {
		waitForElementClickable(driver, HomePagePageUI.ICON_NAME_BY_ROW_INDEX, rowIndex,iconName);
		clickToElement(driver, HomePagePageUI.ICON_NAME_BY_ROW_INDEX,rowIndex,iconName);	
		
	}
	
}
			
		
		 

	
	

