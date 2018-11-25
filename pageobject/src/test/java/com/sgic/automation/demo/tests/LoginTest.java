package com.sgic.automation.demo.tests;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sgic.automation.demo.pages.DashboardPage;
import com.sgic.automation.demo.pages.LoginPage;
import com.sgic.automation.demo.util.DataStore;

public class LoginTest extends BaseTest{
	
	private static Logger logger = LogManager.getLogger(LoginTest.class);
	@Test
	public void testValidLogin() {
		
		String[] testCaseNo = {"TC_001","TC_002"};
		
		for (int i =0;i<testCaseNo.length;i++) {
			for (int j=0;j<DataStore.testData.get("TestCase").size();j++) {
				if (DataStore.testData.get("TestCase").get(j).equalsIgnoreCase(testCaseNo[i])) {
					logger.log(Level.INFO, "Start executing test case : " + testCaseNo[i]);
					extentTest =extentReport.startTest("Test valid credentials test case #: "+ testCaseNo[i]);
					LoginPage loginPage = new LoginPage(driver);
					String username = DataStore.testData.get("Username").get(j);
					String password = DataStore.testData.get("Password").get(j);
					DashboardPage dashboardPage = loginPage.validLogin(username, password);
					extentTest.log(LogStatus.PASS, "Login with Username: " + username + " and Password: " + password);
					Assert.assertTrue(dashboardPage.isDashboardDisplayed());
					extentTest.log(LogStatus.PASS, "Dashboard is displayed");
					dashboardPage.logout();
					extentTest.log(LogStatus.PASS, "User logged out");
					logger.log(Level.INFO, "End executing test case : " + testCaseNo[i]);
					extentReport.endTest(extentTest);
					
					break;
				}
			}		
		}	
	}
	
	
	@Test
	public void testInvalidCredentials() {
				
		String[] testCaseNo = {"TC_003","TC_004"};
		for (int i =0;i<testCaseNo.length;i++) {
			for (int j=0;j<DataStore.testData.get("TestCase").size();j++) {
				if (DataStore.testData.get("TestCase").get(j).equalsIgnoreCase(testCaseNo[i])) {
					logger.log(Level.INFO, "Start executing test case : " + testCaseNo[i]);
					extentTest =extentReport.startTest("Test invalid credentials test case #: "+ testCaseNo[i]);
					LoginPage loginPage = new LoginPage(driver);
					String username = DataStore.testData.get("Username").get(j);
					String password = DataStore.testData.get("Password").get(j);
					loginPage = loginPage.invalidLogin(username, password);
					extentTest.log(LogStatus.PASS, "Login with Username: " + username + " and Password: " + password);
					Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
					logger.log(Level.INFO, "End executing test case : " + testCaseNo[i]);
					extentReport.endTest(extentTest);
					break;
				}
			}		
		}	
	}
	
	@Test
	public void testEmptyPassword() {
		
		String testCaseNo = "TC_005";
		for (int j=0;j<DataStore.testData.get("TestCase").size();j++) {
			if (DataStore.testData.get("TestCase").get(j).equalsIgnoreCase(testCaseNo)) {
				logger.log(Level.INFO, "Start executing test case : " + testCaseNo);
				extentTest =extentReport.startTest("Test empty password test case #: "+ testCaseNo);
				LoginPage loginPage = new LoginPage(driver);
				String username = DataStore.testData.get("Username").get(j);
				String password = DataStore.testData.get("Password").get(j);
				loginPage = loginPage.invalidLogin(username, password);
				extentTest.log(LogStatus.PASS, "Login with Username: " + username + " and Password: " + password);
				Assert.assertEquals(loginPage.getErrorMessage(), "Password cannot be empty");
				logger.log(Level.INFO, "End executing test case : " + testCaseNo);
				extentReport.endTest(extentTest);
				break;
			}
		}			
	}
}
