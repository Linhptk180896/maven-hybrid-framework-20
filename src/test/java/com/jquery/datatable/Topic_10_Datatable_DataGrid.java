package com.jquery.datatable;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTestDataTable;
import pageObjects.jQueryDataTable.HomePageObject;

public class Topic_10_Datatable_DataGrid  extends BaseTestDataTable{
	
	WebDriver driver;
	HomePageObject homePage;
	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;
	@Parameters({"browser","url"})
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {
		driver = getBrowser(browserName, appUrl);
		
	
	}
	
	public void Topic_01_Paging() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.openPagingByPageNumber("10");
		Assert.assertTrue(homePage.isPageNumberActived("10"));
		homePage.openPagingByPageNumber("1");
		Assert.assertTrue(homePage.isPageNumberActived("1"));
		homePage.openPagingByPageNumber("5");
		Assert.assertTrue(homePage.isPageNumberActived("5"));
		homePage.openPagingByPageNumber("8");
		Assert.assertTrue(homePage.isPageNumberActived("8"));

	}
	
	
	
	
	public void Topic_02_EnterToHeader() {
		homePage.refreshCurrentPage(driver);
		homePage.enterToHeaderTextboxByLabel("Females", "384187");
		homePage.enterToHeaderTextboxByLabel("Country", "Seychelles");
		homePage.enterToHeaderTextboxByLabel("Males", "651");
		homePage.enterToHeaderTextboxByLabel("Total", "1270");
		
	}
	
	
	public void Topic_03_GetAllRowAllPages() {
		homePage = PageGeneratorManager.getHomePage(driver);
		//Đọc dữ liệu của file country.txt ra
		//Lưu vào 1 List<String> = expectedAllCountryValues
		//Actual Value
		actualAllCountryValues = homePage.getAllValueOfRowAtAllPages();
		Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
	}
	
	
	public void Topic_04_EnterDataAtAnyRow() {
		homePage = PageGeneratorManager.getHomePage(driver);
		//Viết 1 hàm truyền tham số: Muốn nhập giá trị tại dòng thứ 1 - Cột tên là x - Giá trị là "Linh"
		homePage.enterToTextboxByColumnAtRowNumber("1","Album","a1");
		homePage.enterToTextboxByColumnAtRowNumber("1","Artist","a2");
		homePage.enterToTextboxByColumnAtRowNumber("1","Year","a3");
		homePage.enterToTextboxByColumnAtRowNumber("1","Price","a4");
		
	}
	

	@Test
	public void Topic_04_SelectDropdown() {
		
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLoadButton();
//		//Viết 1 hàm truyền tham số: Muốn nhập giá trị tại dòng thứ 1 - Cột tên là x - Giá trị là "Linh"
//		homePage.selectDropdownByColumnAtRowNumber("1","Origin","Hong Kong");
//		homePage.selectDropdownByColumnAtRowNumber("2","Origin","Korea");
//		homePage.selectDropdownByColumnAtRowNumber("3","Origin","Japan");
//		homePage.selectDropdownByColumnAtRowNumber("4","Origin","Japan");
//
//		homePage.enterToTextboxByColumnAtRowNumber("1","Album","a1");
//		homePage.enterToTextboxByColumnAtRowNumber("1","Artist","a2");
//		homePage.enterToTextboxByColumnAtRowNumber("1","Year","a3");
//		homePage.enterToTextboxByColumnAtRowNumber("1","Price","a4");
//		
//		
//		homePage.enterToTextboxByColumnAtRowNumber("2","Album","Noo");
//		homePage.enterToTextboxByColumnAtRowNumber("2","Artist","Noo phước thịnh");
//		homePage.enterToTextboxByColumnAtRowNumber("2","Year","4");
//		homePage.enterToTextboxByColumnAtRowNumber("2","Price","200.00");
//		
//		
//		homePage.checkToCheckboxByColumnNameAtRowNumber("3", "With Poster?");
//		homePage.checkToCheckboxByColumnNameAtRowNumber("5","With Poster?");
//		
//		
//		homePage.unCheckToCheckboxByColumnNameAtRowNumber("1","With Poster?");
//		homePage.unCheckToCheckboxByColumnNameAtRowNumber("2","With Poster?");
//		homePage.unCheckToCheckboxByColumnNameAtRowNumber("4","With Poster?");
		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.clickToIconByRowNumber("1", "Move Up");
		homePage.clickToIconByRowNumber("1", "Move Down");
		homePage.clickToIconByRowNumber("1", "Insert Row Above");
	}
}
