package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;

import exception.BrowserNoSupport;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTestUploadFiles {
	private WebDriver driver;
	private 	String projectPath = System.getProperty("user.dir");
//--> BaseTest sẽ bổ trợ cho toàn bộ packages của các source folders
	
		public WebDriver getBrowser(String browserName, String appUrl) {
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
//		driver.get(GlobalConstans.USER_PAGE_URL);
		driver.get(appUrl);
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
}

