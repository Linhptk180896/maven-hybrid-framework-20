package com.wordpress.posts;

import org.testng.annotations.Test;


import commons.BaseTest;
import commons.BaseTestWordPress;
import commons.PageGeneratorManager;
import commons.PageGeneratorManagerWordpress;
import pageObject.wordpress.admin.AdminAddNewPostPageObject;
import pageObject.wordpress.admin.AdminDashboardPageObject;
import pageObject.wordpress.admin.AdminLoginPageObject;
import pageObject.wordpress.admin.AdminSearchPostPageObject;
import pageObject.wordpress.user.UserHomePageObject;
import pageObject.wordpress.user.UserPostDetailPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Post_01_Create_Read_Update_Delete_Search extends BaseTestWordPress{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String adminUsername = "linhptk" ;
	String adminPassword = "Linh1808/";
	String adminSearchPostUrl;
	int randomNumber = randomNumber();
	String postTitle = "Post title " + randomNumber;
	String postBody = "Post body " + randomNumber;
	String userUrl, adminUrl;
	AdminDashboardPageObject adminDashboardPage;
	AdminSearchPostPageObject adminSearchPostPage;
	AdminLoginPageObject adminLoginPageObject;
	AdminAddNewPostPageObject adminAddNewPostPage;
	UserHomePageObject userHomePage;
	UserPostDetailPageObject userPostDetailPage;
	String editPostTitle = "Post title edit " + randomNumber;
	String editPostBody= "Post body edit " + randomNumber;
	String currentDay;
	String authorName = "Linhptk";
	
	
	
	@Parameters({"browserName","adminUrl","userUrl"})
	@BeforeClass
	  public void beforeClass(String browserName, String adminUrl, String userUrl) {
		
		log.info("Pre-Condition - Step 01: Open browser and navigate to Admin login page ");
		driver = getBrowser(browserName, adminUrl);
		adminLoginPageObject= PageGeneratorManagerWordpress.getAdminLoginPage(driver);
		this.adminUrl = adminUrl;
		this.userUrl = userUrl;
		log.info("Pre-Condition - Step 02: Enter Email " + adminUsername);
		adminLoginPageObject.inputToEmailTextbox(adminUsername);
		
		log.info("Pre-Condition - Step 03: Enter Email " + adminPassword);
		adminLoginPageObject.inputToPasswordTextbox(adminPassword);
		log.info("Pre-Conditon - Step 04: Click on btn Login and navigate to Admin Dashboard Page");
		adminDashboardPage = adminLoginPageObject.clickOnLoginButton();
		
	  }
	
	
	@Test
	public void Post_01_Create_New_Post() {
		log.info("Step: 01: click on Post menu and navigate to Admin Search Post Page ");
		adminSearchPostPage = adminDashboardPage.clickOnPostMenu();
		
		log.info("Step 02: Get Admin Search Post Url");
		adminSearchPostUrl = adminSearchPostPage.getPageUrl(driver);
		
		log.info("Step 02: click on Add new  button and navigate to Admin New Post Page ");
		adminAddNewPostPage = adminSearchPostPage.clickOnAddNewButton();
		
		log.info("Step 05: Send key to Post title");
		adminAddNewPostPage.inputToPostTitle(postTitle);
		
		log.info("Step 06: Send key to Post body");
		adminAddNewPostPage.inputToPostBody(postBody);
		
		
		log.info("Step 07: Click on Pre-Publish button");
		adminAddNewPostPage.clickOnPrePublishButton("Publish");
		
		log.info("Step 08: Click on Publish button");
		adminAddNewPostPage.clickOnPublishButton();
		
		log.info("Step 09: Verify Post Published message is displayed");
		verifyTrue(adminAddNewPostPage.isPostPublishedMessageDisplayed("published"));
		
	}
	
	
	@Test
	public void Post_02_Search_And_View_Post() {
		
		log.info("Post01 - Step 01: Open Admin Search Post Page ");
		adminSearchPostPage = adminAddNewPostPage.openSearchPostPageUrl(adminSearchPostUrl);
		
//		log.info("Step 02: Verify New published post is displayed ");
//		adminSearchPostPage.isNewPublishedPostIsDisplayed("1","Title");	
		
		log.info("Post02 - Step 02: Enter to Search textbox");
		adminSearchPostPage.inputToSearchTextbox(postTitle);
		
		log.info("Post02 - Step 03: Click on Search Posts Button");
		adminSearchPostPage.clickOnSearchPostButton();
		
		log.info("Post02 - Step 04: Verify search table contains Title =  " + postTitle + " is displayed");
		verifyTrue(adminSearchPostPage.isPostTitleIsDisplayed( "title", postTitle));
		
		log.info("Post02 - Step 05: Verify search table contains Author =  " + adminUsername + " is displayed");
		verifyTrue(adminSearchPostPage.isPostAuthorIsDisplayed( "author", adminUsername));
		
		log.info("Post02 - Step 07: Open EndUser Page");
		userHomePage = adminSearchPostPage.openUserHomePage(driver, this.userUrl);
		
		log.info("Post02 - Step 08: Verify post info display at homepage");
		userHomePage.isPostWithTitleIsDisplayed(postTitle);
//		userHomePage.isPostWithDateIsDisplayed();
		userHomePage.isPostWithBodyIsDisplayed(postTitle,postBody);
		userHomePage.isPostWithAuthorIsDisplayed(postTitle,adminUsername);
		
		
		
		log.info("Post02 - Step 09: Click to post title");
		userPostDetailPage = userHomePage.clickOnPostTitle(postTitle);
		 
		
		
		log.info("Post02 - Step 09: Verify post info display at post detail page");
		userPostDetailPage.isPostDetailWithTitleIsDisplayed(postTitle);
		verifyEquals(userPostDetailPage.getPostTitleInPostDetail(postTitle), postTitle);
//		userPostDetailPage.isPostDetailWithDateIsDisplayed();
		userPostDetailPage.isPostDetailWithBodyIsDisplayed(postTitle, postBody);
		verifyEquals(userPostDetailPage.getPostBodyInPostDetail(postTitle,postBody), postBody);

		userPostDetailPage.isPostDetailWithAuthorIsDisplayed(postTitle,adminUsername);
		verifyEquals(userPostDetailPage.getPostAuthorInPostDetail(postTitle, adminUsername), authorName);

		
		
	}
	
	@Test
	public void Post_03_Edit_Post() {
		log.info("Post03 - Step 01: Open Admin Url");
		adminDashboardPage = userPostDetailPage.openAdminDashboardPage(driver, adminUrl);
		
		log.info("Post03 - Step 02: Click on Post Menu");
		adminSearchPostPage = adminDashboardPage.clickOnPostMenu();
		
		log.info("Post03 - Step 04: Click on Post title");
		adminAddNewPostPage = adminSearchPostPage.clickOnPostTitle("1","title",postTitle);
		
		log.info("Post03 - Step 05: Edit Post body");
		adminAddNewPostPage.enterToAddNewPostTitle(editPostTitle);
		
		log.info("Post03 - Step 05: Edit Post body");
		adminAddNewPostPage.enterToAddNewPostBody(editPostBody);
		
		log.info("Post03 - Step 06: Click on Update button");
		adminAddNewPostPage.clickOnUpdateButton("Update");
		
		log.info("Post03 - Step 06: Verify post updated message displayed");
		adminAddNewPostPage.isUpdatedPostMessageIsDisplayed("updated");
		
		
		//---
		log.info("Post01 - Step 01: Open Admin Search Post Page ");
		adminSearchPostPage = adminAddNewPostPage.openSearchPostPageUrl(adminSearchPostUrl);
		
//		log.info("Step 02: Verify New published post is displayed ");
//		adminSearchPostPage.isNewPublishedPostIsDisplayed("1","Title");	
		
		log.info("Post02 - Step 02: Enter to Search textbox");
		adminSearchPostPage.inputToSearchTextbox(editPostTitle);
		
		log.info("Post02 - Step 03: Click on Search Posts Button");
		adminSearchPostPage.clickOnSearchPostButton();
		
		log.info("Post02 - Step 04: Verify search table contains Title =  " + editPostTitle + " is displayed");
		verifyTrue(adminSearchPostPage.isPostTitleIsDisplayed("title",editPostTitle));
		
		log.info("Post02 - Step 05: Verify search table contains Author =  " + adminUsername + " is displayed");
		verifyTrue(adminSearchPostPage.isPostAuthorIsDisplayed( "author", adminUsername));
		
		log.info("Post02 - Step 07: Open EndUser Page");
		userHomePage = adminSearchPostPage.openUserHomePage(driver, this.userUrl);
		
		log.info("Post02 - Step 08: Verify post info display at homepage");
		userHomePage.isPostWithTitleIsDisplayed(editPostTitle);
//		userHomePage.isPostWithDateIsDisplayed();
		userHomePage.isPostWithBodyIsDisplayed(editPostTitle,editPostBody);
		userHomePage.isPostWithAuthorIsDisplayed(editPostTitle,adminUsername);
		
		
		
		log.info("Post02 - Step 09: Click to post title");
		userPostDetailPage = userHomePage.clickOnPostTitle(editPostTitle);
		 
		
		
		log.info("Post02 - Step 09: Verify post info display at post detail page");
		userPostDetailPage.isPostDetailWithTitleIsDisplayed(editPostTitle);
		verifyEquals(userPostDetailPage.getPostTitleInPostDetail(editPostTitle), editPostTitle);
//		userPostDetailPage.isPostDetailWithDateIsDisplayed();
		userPostDetailPage.isPostDetailWithBodyIsDisplayed(editPostTitle, editPostBody);
		verifyEquals(userPostDetailPage.getPostBodyInPostDetail(editPostTitle, editPostBody), editPostBody);

		userPostDetailPage.isPostDetailWithAuthorIsDisplayed(editPostTitle,adminUsername);
		verifyEquals(userPostDetailPage.getPostAuthorInPostDetail(editPostTitle, adminUsername), authorName);
		//------------
		
		
	}
	
	@Test
	public void Post_04_Delete_Post() {
		//cmt ,v
		log.info("Post03 - Step 01: Open Admin Url");
		adminDashboardPage = userPostDetailPage.openAdminDashboardPage(driver, adminUrl);
		
		log.info("Post03 - Step 02: Click on Post Menu");
		adminSearchPostPage = adminDashboardPage.clickOnPostMenu();
	
		log.info("Post03 - Step 03: Click on Post checkbox");
		adminSearchPostPage.selectPostCheckbox( "cb", editPostTitle);
		
		log.info("Post03 - Step 03: Click on Bulk actions dropdown");
		adminSearchPostPage.clickOnBulkActionDropdown();
		
		log.info("Post03 - Step 03: Click on Move to trash action");
		adminSearchPostPage.clickOnMoveToTrashButton("Move to Trash");
		
		log.info("Post03 - Step 03: Click on Apply button");
		adminSearchPostPage.clickOnApplyButton();
		
		log.info("Post03 - Step 03: Verify message '1 post moved to the Trash' is displayed");
		adminSearchPostPage.isMovedToTrashMessageIsDisplayed();
		
		log.info("Post02 - Step 02: Enter to Search textbox");
		adminSearchPostPage.inputToSearchTextbox(editPostTitle);
		
		log.info("Post02 - Step 03: Click on Search Posts Button");
		adminSearchPostPage.clickOnSearchPostButton();
		
		log.info("Post02 - Step 03: Verify 'No posts found.' is displayed");
		adminSearchPostPage.isNoPostFoundMessageIsDisplayed();
		
		log.info("Post02 - Step 07: Open EndUser Page");
		userHomePage = adminSearchPostPage.openUserHomePage(driver, this.userUrl);
		
		log.info("Post02 - Step 07: Enter to User search textbox");
		userHomePage.enterToUserSearchPostTexbox(editPostTitle);
		
		log.info("Post02 - Step 07: Click on Search button");
		userHomePage.clickOnSearchButton();
		
		log.info("Post02 - Step 07: Verify message 'Nothing Found'is displayed");
		userHomePage.isNothingFoundMessageIsDisplayed();
		
		
		
		
		
	}
	
	  @AfterClass 
	  public void afterClass() {
		
	  }

}


//L??u ?? khi vi???t h??m
//1--Access Modifier: public/protected/default/private
//2--Ki???u d??? li???u tr??? v???: public/void/String/boolean/...Ki???u d??? li???u tr??? v??? s??? li??n quan ?????n ch???c n??ng trong th??n h??m
//3--T??n h??m: ?????t t??n  tu??n theo ch???c n??ng ??ang c???n vi???t.Convention tu??n theo chu???n c???a ng??n ng??? l???p trinh java (camelCase)
//4--Tham s???: C?? tham s??? ho???c kh??ng c?? tham s???, t??y v??o ch???c n??ng c???n vi???t
//5--Return ki???u d??? li???u tr??? v??? cho h??m: N???u c?? return d??? li???u th?? s??? kh???p v???i ki???u d??? li???u ??? ?? 2. V?? return l?? d??ng cu???i c??ng ????? k???t th??c h??m.
//----------------
//Abstract page/Base page: l?? 1 class d??ng chung ???? wrapper l???i functions c???a selenium 
