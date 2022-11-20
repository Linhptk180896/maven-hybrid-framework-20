package com.jquery.uploadFile;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTestUploadFiles;
import pageObjects.jQueryUploadFiles.HomePageUploadFileObject;
import pageObjects.jQueryUploadFiles.PageGeneratorManager;

public class Level_11_Upload_MultiFile extends BaseTestUploadFiles {
	WebDriver driver;
	HomePageUploadFileObject homePage;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {
		driver = getBrowser(browserName, appUrl);
	}
	
	@Test
	public void TC_01_Upload_Multiple_File() {
		String catFileName = "Cat.jpg";
		String cSharpFileName = "cSharp.jpg";
		String rubyName = "Ruby.jpg";
		String[] multiFiles = {catFileName,cSharpFileName,rubyName};
		homePage = PageGeneratorManager.getHomepage(driver);
		//Assert file load từ lên máy lên UI
		homePage.uploadMultipleFiles(driver, multiFiles);
		Assert.assertTrue(homePage.isFileLoadedByName(catFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(cSharpFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(rubyName));
		
		//Assert file đã load và hiện trên UI  thành công
		homePage.clickToStartButton();
		Assert.assertTrue(homePage.isFileLinkUploadedByName(catFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(cSharpFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(rubyName));
		
		//Kiểm tra nó có phải là 1 cái hình hay không?
		Assert.assertTrue(homePage.isFileImageUploadedByName(catFileName));
		Assert.assertTrue(homePage.isFileImageUploadedByName(cSharpFileName));
		Assert.assertTrue(homePage.isFileImageUploadedByName(rubyName));
	}
	
	@Test
	public void TC_02_Upload_One_File() {
		String catFileName = "Cat.jpg";
		String cSharpFileName = "cSharp.jpg";
		String rubyName = "Ruby.jpg";
//		String[] multiFiles = {catFileName,cSharpFileName,rubyName};
		//Step1: Load one file
		homePage = PageGeneratorManager.getHomepage(driver);
		//Assert file load từ lên máy lên UI
		homePage.uploadMultipleFiles(driver, catFileName);
		//Step2: Verify file loaded success
		Assert.assertTrue(homePage.isFileLoadedByName(catFileName));
		
		
		//Assert file đã load và hiện trên UI  thành công
		//Step3: Click Start button
		homePage.clickToStartButton();
		//Step4: Verify upload success
		Assert.assertTrue(homePage.isFileLinkUploadedByName(catFileName));
	
		
		//Step5: Check isImage 
		//Kiểm tra nó có phải là 1 cái hình hay không?
		Assert.assertTrue(homePage.isFileImageUploadedByName(catFileName));
	
	}
		
	}



