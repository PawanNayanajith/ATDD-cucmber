package com.sgic.automation.demo.tests;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sgic.automation.demo.util.DataStore;
import com.sgic.automation.demo.util.Functions;

public class BaseTest {

	protected static WebDriver driver;
	private static String browser;
	private static String baseUrl;
	public static Properties prop = new Properties();
	public static ExtentReports extentReport = new ExtentReports(
			System.getProperty("user.dir") + "/src/test/resources/reports/ExtentReportResults.html");
	public static ExtentTest extentTest;
	private static String screenShotFolderPath = System.getProperty("user.dir") + "/src/test/resources/screenshots/";

	@BeforeSuite(alwaysRun = true)
	public void setUp() throws Exception {

		InputStream input = null;
		input = this.getClass().getClassLoader().getResourceAsStream("config/config.properties");
		prop.load(input);

		browser = System.getProperty("browser");
		if (browser == null || browser.isEmpty()) {
			browser = prop.getProperty("browser");
		}

		baseUrl = prop.getProperty("baseUrl");

		DataStore.readExcelFile("/src/test/resources/data/TestData.xlsx");
		DataStore.loadData();
	}

	@AfterSuite
	public void cleanUp() {
		extentReport.flush();
		DataStore.clearData();

	}

	@BeforeMethod
	public void testSetUp() {

		switch (browser) {

		case "firefox":
			//driver = new FirefoxDriver();
			break;
		case "chrome":
			driver = new ChromeDriver();
			break;
		default:
			//driver = new FirefoxDriver();
		}

		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")), TimeUnit.SECONDS);

	}

	@AfterMethod(alwaysRun = true)
	public void endTest(ITestResult result) {

		if (!result.isSuccess()) {
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(getFilePath()));
			extentReport.endTest(extentTest);

		}
		driver.quit();
	}

	public static String getFilePath() {

		String filename = Functions.getTimeStamp("yyyy-MM-dd_HH:mm:ss") + ".jpg";
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File file = new File(screenShotFolderPath + filename);
		try {
			FileUtils.copyFile(screenshotFile, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file.getAbsolutePath();
	}
}
