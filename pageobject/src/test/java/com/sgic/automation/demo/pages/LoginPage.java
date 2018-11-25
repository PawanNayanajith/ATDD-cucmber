package com.sgic.automation.demo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends BasePage {
	
	private By txtUsername = By.id("txtUsername");
	private By txtPassword = By.id("txtPassword");
	private By btnLogin = By.id("btnLogin");
	private By spanMessage = By.id("spanMessage"); 
	
	private static Logger logger = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public DashboardPage validLogin(String username, String password) {
		
		driver.findElement(txtUsername).sendKeys(username);
		logger.log(Level.INFO, "Entered username : "+ username);
		driver.findElement(txtPassword).sendKeys(password);
		logger.log(Level.INFO, "Entered password : "+ password);
		driver.findElement(btnLogin).click();
		logger.log(Level.INFO, " Clicked login button");
		
		return new DashboardPage(driver);
		
	}
	
	
	public LoginPage invalidLogin(String username, String password) {
		
		driver.findElement(txtUsername).sendKeys(username);
		logger.log(Level.INFO, "Entered username : "+ username);
		driver.findElement(txtPassword).sendKeys(password);
		logger.log(Level.INFO, "Entered password : "+ password);
		driver.findElement(btnLogin).click();
		logger.log(Level.INFO, " Clicked login button");
		
		return new LoginPage(driver);
		
	}
	
	public String getErrorMessage() {
		
		String strMessage;
		try {
			strMessage = driver.findElement(spanMessage).getText();
			
		} catch (Exception e) {
			strMessage = "";
			logger.log(Level.ERROR, " Error in reading message");
		}
		return strMessage;
		
	}

}
