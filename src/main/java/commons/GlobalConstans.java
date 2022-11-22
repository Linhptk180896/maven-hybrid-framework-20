package commons;

import java.io.File;

public class GlobalConstans {
	
//GlobalConstans chứa các hằng số dùng chung cho tất cả class trong framework này
	//DEV
	public static final String ADMIN_DEV_URL ="https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F";
	public static final String USER_PAGE_URL ="https://demo.nopcommerce.com/login?returnUrl=%2F";
	
	//TEST
	public static final String ADMIN_TESTING_URL ="https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F";
	public static final String USER_TESTING_URL ="https://demo.nopcommerce.com/login?returnUrl=%2F";
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	//Trỏ vào thư mục mặc định của User
	//Window: downloads -> khi chạy test case thì thường sẽ xóa hết dữ liệu trong mục này nên sẽ lưu hết vào 1 mục gọi là downloadFiles của project
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles"+ File.separator;
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
	public static final String REPORTING_SCREENSHOT = PROJECT_PATH + "reportNGImages" + File.separator;
	public static final String EXTENT_PATH_V2 = PROJECT_PATH + "extendREportsV2" + File.separator;
	public static final String EXTENT_PATH_V3 = PROJECT_PATH + "extendREportsV3" + File.separator;
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	
	public static final int LONG_TIMEOUT = 30;
	public static final int SHORT_TIMEOUT = 5;
	public static final int RETRY_TEST_FAIL = 3;
	
	public static final String USER_TESTING_URL ="https://demo.nopcommerce.com/login?returnUrl=%2F";

	
	
	
}
