package com.jquery.datatable;

import org.openqa.selenium.WebDriver;

import pageObjects.jQueryDataTable.HomePageObject;

public class PageGeneratorManager {
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
		
	}

}
