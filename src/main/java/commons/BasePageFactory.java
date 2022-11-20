package commons;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	public static BasePageFactory getBasePageObject() {
		return new BasePageFactory();
	}
	
//--> Base Page:  sẽ bổ trợ cho cả package pageObjects. BasePage là 1 class sẽ chứa các hàm dùng chung cho pagaeObject
	//1--Hàm getUrl: nhiệm vụ mở 1 Url ra
	public void getUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);		
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver ) {
		return driver.getCurrentUrl();
		
	}
	public String getSourceCode(WebDriver driver ) {
		return driver.getPageSource();
		
	}
	public void backToPage(WebDriver driver ) {
		driver.navigate().back();
		
	}
	public void forwardToPage(WebDriver driver ) {
		driver.navigate().forward();
		
	}
	public void refreshToPage(WebDriver driver ) {
		driver.navigate().refresh();
		
	}
	
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
		
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void switchToWindowById(WebDriver driver, String currentWindowId ) {
		//Lấy hết windowid
		Set<String> allWindowsId = driver.getWindowHandles();
		for (String id : allWindowsId) {
			if(!id.equals(currentWindowId)) {
				driver.switchTo().window(id);
			}
		
		}
		
	}
	
	public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindowId = driver.getWindowHandles();
		for (String id : allWindowId) {
				driver.switchTo().window(id);
				//Lấy title của page đó ra
				String actualTitle = driver.getTitle();
				if (actualTitle.equals(expectedTitle)) {
					//Thỏa mãn điều kiện là đúng cái page/tab mình cần thì break vòng lặp
					break;
				}
					
				}
				
			}
	
	public void closeAllWindowWithoutParent(WebDriver driver, String parentId) {
		Set<String> allWindowId = driver.getWindowHandles();
		for (String runningWindowId : allWindowId) {
			if (!runningWindowId.equals(parentId)) {
				driver.switchTo().window(parentId);
				driver.close();}
		}
		driver.switchTo().window(parentId);
}
	
	private By getByXpath(String xpathLocator) {
		return  By.xpath(xpathLocator);
		
	}
	
	private WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	
	private List<WebElement> getListWebElement(WebDriver driver, String xpathLocator){
		return driver.findElements(getByXpath(xpathLocator));
	}
	
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}
	
	public void sendKeyToElement(WebDriver driver, WebElement element, String textValue) {
		element.clear();
		element.sendKeys(textValue);
	}
	
	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByValue(textItem);
		
	}
	
	public String getSelectedItemInDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
		
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
		
	}
	
	public void selectItemInCustomDropdownList(WebDriver driver,String parentLocator, String childLocator, String expectedTextItem) {
		getWebElement(driver, expectedTextItem).click();
		sleepInSecond(5);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		for (WebElement item : allItems) {
			String actualText = item.getText();	
			if (actualText.equals(expectedTextItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				sleepInSecond(2);
				break;
			}
		
		}
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time*100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getElementAttribute(WebDriver driver,String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
		
	}
	
	public String getCssValue(WebDriver driver,String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
		
	}
	
	public String getHexaColorFromRgba(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
				
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator){
		return getListWebElement(driver, xpathLocator).size();
	}
	
	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!isElementDisplayed(driver, xpathLocator)) {
			element.click();	}
}
	
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	
}
	public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	
	}
	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
	return getWebElement(driver, xpathLocator).isSelected();
		
	}
	
	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	public void hoverMouseToElement(WebDriver driver, String xpathLocator ) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}
	

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}


	public void hightlightElement(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathlocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(3);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathlocator));
	}

	public void scrollToElementOnTop(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathlocator));
	}

	public void scrollToElementOnDown(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, xpathlocator));
	}

	

	public void removeAttributeInDOM(WebDriver driver, String xpathlocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathlocator));
	}
	
	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebDriverWait explicitWait;
		explicitWait = new WebDriverWait(driver, longTimeout);
		
		ExpectedCondition<Boolean>jQueryLoad = 	new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				
				return (Boolean) jsExecutor.executeScript("return(window.jQuery!=null)&&(jQuery.active ===0);");
			}					
		};
			ExpectedCondition<Boolean> jsLoad = 	new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}					
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
		
	}
	
	public String getElementValidationMessage(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathlocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, xpathlocator));
		if (status) {
			return true;
		}
		return false;

}
	
	public void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
}		
	
	
	public void waitForElementInvisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathlocator)));
}	
	
	
	public void waitForAllElementVisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathlocator)));
}
	
	public void waitForAllElementInvisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathlocator)));
}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
}
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	private long longTimeout = 30;
	private long shortTimeout = 5;
}