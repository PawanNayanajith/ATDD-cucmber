package com.sgic.automation.demo.pages;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage{
	
	private By lblHeader = By.xpath("//h1");
	private By lnkAdminMenu = By.id("menu_admin_viewAdminModule");
	private By divWelcomeMenu = By.id("welcome");
	private By lnkLogout = By.linkText("Logout");
	
	private static Logger logger = LogManager.getLogger(DashboardPage.class);

	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isDashboardDisplayed() {
		boolean flag = false;
		
		try {
			logger.log(Level.INFO, "Checking if dashboard displayed");
			driver.findElement(lblHeader).isDisplayed();
			flag = true;
		} catch(Exception e) {
			flag =false;
		}
		
		return flag;
		
	}
	
	public UserManagementPage navigateToUserManagement() {
		
		driver.findElement(lnkAdminMenu).click();
		logger.log(Level.INFO, "Clicked Admin menu");
		return new UserManagementPage(driver);
		
	}
	
	public LoginPage logout() {
		
		driver.findElement(divWelcomeMenu).click();
		logger.log(Level.INFO, "Clicked Welcome menu");
		driver.findElement(lnkLogout).click();
		logger.log(Level.INFO, "Clicked logout link");
		return new LoginPage(driver);
		
	}
	

}
