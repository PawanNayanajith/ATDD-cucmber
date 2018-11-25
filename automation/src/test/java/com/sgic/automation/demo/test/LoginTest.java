package com.sgic.automation.demo.test;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sgic.automation.demo.util.DataStore;
import com.sgic.automation.demo.util.Log;

public class LoginTest extends BaseTest{
	
	private static Logger logger = LogManager.getLogger(LoginTest.class);

	boolean flag =false;
  @Test
  public void testUntitledTestCase() throws Exception {
	
	for (int i=0; i<2;i++) {
		

	extentTest = extentReport.startTest("Admin Login Test" + i);
	extentTest.log(LogStatus.PASS, "Iteration" + i);
	
	Log.startTestCase();
    driver.get(prop.getProperty("baseUrl"));
    extentTest.log(LogStatus.PASS, "Lanch Application" + DataStore.testData.get("Username").get(i));
    logger.log(Level.INFO,"######  Entered username ######");  
    driver.findElement(By.id("txtUsername")).clear();
    driver.findElement(By.id("txtUsername")).sendKeys(DataStore.testData.get("Username").get(i));
    extentTest.log(LogStatus.PASS, "Entered username" + DataStore.testData.get("Username").get(i));
    logger.log(Level.INFO,"######  Entered username ######");
    driver.findElement(By.id("txtPassword")).click();
    driver.findElement(By.id("txtPassword")).clear();
    driver.findElement(By.id("txtPassword")).sendKeys(DataStore.testData.get("Password").get(i));
    extentTest.log(LogStatus.PASS, "entered password" + DataStore.testData.get("Password").get(i));
    logger.log(Level.INFO,"######  Entered password ######");
    driver.findElement(By.id("btnLogin")).click();
    extentTest.log(LogStatus.PASS, "login button clicked");
    logger.log(Level.INFO,"######  login button clicked ######");
  
	if (driver.findElement(By.id("welcome")).getText().equalsIgnoreCase("Welcome Admin")) {		
		flag =true;
		extentTest.log(LogStatus.PASS, "verified welcome message");
	} else {
		String flie = captureScreenShot();
		extentTest.log(LogStatus.FAIL, "Failed To find the message");
		logger.log(Level.ERROR, "Failed To find the message.\n"
				+ "Screenshot file: " +flie);
	}
	
	Assert.assertTrue(flag);
    driver.findElement(By.id("welcome")).click();
    extentTest.log(LogStatus.PASS, "Profile icon clicked");
    logger.log(Level.INFO,"######  Profile icon clicked ######");
    driver.findElement(By.linkText("Logout")).click();
    extentTest.log(LogStatus.PASS, "logout from application");
    logger.log(Level.INFO,"######  logout from application ######");
    Log.endTestCase();
    extentReport.endTest(extentTest);
    
	}
  }

}
