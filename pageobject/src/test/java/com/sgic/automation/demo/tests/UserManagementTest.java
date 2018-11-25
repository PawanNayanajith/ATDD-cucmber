package com.sgic.automation.demo.tests;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sgic.automation.demo.pages.DashboardPage;
import com.sgic.automation.demo.pages.LoginPage;
import com.sgic.automation.demo.pages.UserManagementPage;

public class UserManagementTest extends BaseTest{
	
	private static Logger logger = LogManager.getLogger(UserManagementTest.class);
	
	@Test
	public void  verifyUserManagmentAccessForAdmin() {
		logger.log(Level.INFO, "Start executing test case : verifyUserManagmentAccessForAdmin");
		extentTest =extentReport.startTest("Verify User Managment access for Admin");
		LoginPage loginPage = new LoginPage(driver);
		DashboardPage dashboardPage = loginPage.validLogin("Admin", "admin123");
		extentTest.log(LogStatus.PASS, "Login with Username: Admin and Password: admin123");
		UserManagementPage userMgtPage = dashboardPage.navigateToUserManagement();
		Assert.assertTrue(userMgtPage.isUserMgtDisplayed());
		extentTest.log(LogStatus.PASS, "User Management Page Displayed");
		extentReport.endTest(extentTest);
		logger.log(Level.INFO, "End executing test case : verifyUserManagmentAccessForAdmin");
		
	}

}
