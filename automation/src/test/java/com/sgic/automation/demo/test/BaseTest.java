package com.sgic.automation.demo.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sgic.automation.demo.util.DataStore;
import com.sgic.automation.demo.util.Functions;

public class BaseTest {
	
	  protected static WebDriver driver;
	  private String browser;
	  
	  public static ExtentReports extentReport = new ExtentReports(System.getProperty("user.dir") + "/src/test/resources/reports/ExtentReportResults.html");
	  public static ExtentTest extentTest;
	  
	  public static Properties prop = new Properties();
	  
	  private static String screenShotFolderPath = System.getProperty("user.dir") + "/src/test/resources/screenshots/";
	
	@BeforeClass(alwaysRun = true)
	  public void setUp() throws Exception {
		 
		InputStream input = null;
		input = this.getClass().getClassLoader().getResourceAsStream("config/config.properties");
		prop.load(input);
		  
		browser = System.getProperty("browser");
		if (browser==null||browser.isEmpty()) {
			browser = prop.getProperty("browser");
		}
		
		
		switch (browser) {
		
		case "firefox":
			
			driver = new FirefoxDriver();
			break;
		case "chrome":
			//System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriverpath"));
			driver = new ChromeDriver();
			break;
		default:
			driver = new FirefoxDriver();	
		}
		
	    driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")), TimeUnit.SECONDS);
	    
	    DataStore.readExcelFile("/src/test/resources/data/TestData.xlsx");
	    DataStore.loadData();
	  }
	
	public static String captureScreenShot() {
		
		String filename = Functions.getTimeStamp("yyyy-MM-dd_HH:mm:ss")+".jpg";
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
			FileUtils.copyFile(screenshotFile, new File(screenShotFolderPath + filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return filename;
	}
	
	
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
		  DataStore.clearData();
		  extentReport.flush(); 
	    driver.quit();
	  }

}
