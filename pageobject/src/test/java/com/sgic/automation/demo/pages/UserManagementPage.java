package com.sgic.automation.demo.pages;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserManagementPage extends BasePage{

	private By lblHeader = By.xpath("//h1");
	
	private static Logger logger = LogManager.getLogger(UserManagementPage.class);
	
	public UserManagementPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isUserMgtDisplayed() {
		boolean flag = false;
		
		try {
			logger.log(Level.INFO, "Checking if User Management page displayed");
			driver.findElement(lblHeader).isDisplayed();
			flag = true;
		} catch(Exception e) {
			flag =false;
		}
		
		return flag;
		
	}

}
