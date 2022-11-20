package commons;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import exception.BrowserNoSupport;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {
	// Driver của Base test đang là private nên phải viết 1 hàm return ra driver của baseTest để test reportNG dùng
	private WebDriver driver;
	//Public log này là final để không được gán lai
	protected final Log log;
	private 	String projectPath = System.getProperty("user.dir"); 
//--> BaseTest sẽ bổ trợ cho toàn bộ packages của các source folders
	
	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllureReport();
	}
	
	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	//// Driver của Base test đang là private nên phải viết 1 hàm return ra driver của baseTest để test reportNG dùng
	public WebDriver getInstanceDriver() {
		return this.driver;
	}
	
		public WebDriver getBrowser(String browserName) {
			BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		if (browserList == BrowserList.CHROME) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
			
		}else if (browserList== BrowserList.H_CHROME) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
			
		}
		else if (browserList== BrowserList.H_FIREFOX) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		}
		
		else if (browserList == BrowserList.FIREFOX) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browserList == BrowserList.EDGE) {
//			System.setProperty("webdriver.msedgedriver.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if (browserList == BrowserList.H_COCCOC) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().driverVersion("102.0.5005.61").setup();
			ChromeOptions options = new ChromeOptions();
			//Cốc cốc phải setBinary 
			//Chrome - 5 hoặc 6 version thì ra version của cốc cốc
			options.setBinary("C:\\Users\\linhptk\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		}
		else if (browserList==BrowserList.COCCOC) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().driverVersion("102.0.5005.61").setup();
			ChromeOptions options = new ChromeOptions();
			//Cốc cốc phải setBinary 
			//Chrome - 5 hoặc 6 version thì ra version của cốc cốc
			if (GlobalConstans.OS_NAME.startsWith("Windows")) {
				options.setBinary("C:\\Users\\linhptk\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");				
			}
			else {
				options.setBinary("C:\\Users\\linhptk\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");

			}
			driver = new ChromeDriver(options);
		}
		else if (browserList == BrowserList.OPERA) {
//			System.setProperty("webdriver.opera.driver", projectPath + "\\browserDrivers\\operadriver.exe");
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			
		}
		else {
			throw new BrowserNoSupport(browserName);
		}
		driver.get(GlobalConstans.USER_PAGE_URL);
		driver.manage().timeouts().implicitlyWait(GlobalConstans.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
		
	}
		
	
	
		
	protected String getEnvironmentURl(String environmentName) {
			String url = null;
			switch (environmentName) {
			case "DEV": {
				
				url = GlobalConstans.ADMIN_DEV_URL;
				break;
			}
			case "TEST":{
				url = GlobalConstans.ADMIN_TESTING_URL;
				break;
			}
			
			}
			return url;}
			
	protected String getEnvironmentURlEnum(String environmentName) {
		
		String url= null;
		EnvironmentList environmentList  = EnvironmentList.valueOf(environmentName.toUpperCase());
		switch (environmentList) {
		case DEV: {			
			url = GlobalConstans.ADMIN_DEV_URL;
			break;
		}
		case TESTING:{
			url = GlobalConstans.ADMIN_TESTING_URL;
			break;
		}
		case STAGING:{
			url = GlobalConstans.ADMIN_TESTING_URL;
			break;
		}
		case PRODUCTION:{
			url = GlobalConstans.ADMIN_TESTING_URL;
			break;
		}
		
		}
		return url;
		
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	
	//-------------------------------------------
	//Copy từ github: https://gist.github.com/daominhdam/61f6a7a816959216a4f9accda2337dad
	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {		
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			log.info(" -------------------------- PASSED -------------------------- ");
			Assert.assertFalse(condition);}
		
			catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	
	public void deleteAllureReport() {
		try {
			
			String pathFolderDownload = GlobalConstans.PROJECT_PATH + "/allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

}
	
	public void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


	
