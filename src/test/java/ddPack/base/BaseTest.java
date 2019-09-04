package ddPack.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static Properties prop = null;
	public static FileInputStream fis;
	public static WebDriver driver=null;
	public static ExtentReports eReport ;
	public static ExtentTest eTest ;
	
	public static void initialize() {
		if (prop == null) {
			prop = new Properties();
			try {
				fis = new FileInputStream("src\\test\\resources\\config.properties");
				prop.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void openBrowser(String browserName) {
		
		eTest.log(LogStatus.INFO, "Opening the browser:"+browserName);
		if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}
		else if(browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		eTest.log(LogStatus.INFO,"Browser "+browserName+ "got opened");
		driver.manage().window().maximize();
		eTest.log(LogStatus.INFO,"browser got maximized");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
}
