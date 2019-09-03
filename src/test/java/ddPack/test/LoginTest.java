package ddPack.test;

import java.util.HashMap;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ddPack.base.BaseTest;
import util.DataUtil;
import util.ExtentManager;
import util.MyXLSReader;

public class LoginTest extends BaseTest {

	MyXLSReader xls=null;
	@BeforeClass
	public void init() {
		initialize();
	}

	@DataProvider
	public Object[][] getData() {
		String filepath = prop.getProperty("zohoXLSXFile");
		Object[][] testData = null;
		try {
			xls = new MyXLSReader(filepath);
			testData = DataUtil.getTestData(xls, "LoginTest", "Data");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testData;
	}

	@Test(dataProvider = "getData")
	public void doLogin(HashMap<String, String> map) {
		ExtentReports eReport = ExtentManager.getInstance();
		ExtentTest eTest = eReport.startTest("LoginTest");
		eTest.log(LogStatus.INFO,"login test started");
		if(!(DataUtil.isRunnable(xls, "LoginTest", "Testcases"))|| map.get("Runmode").equals("N")) {
			eTest.log(LogStatus.SKIP, "Skipping the test as the run mode is set to N");
			throw new SkipException("skipping the test as it is set to N");
		}
	}
}
