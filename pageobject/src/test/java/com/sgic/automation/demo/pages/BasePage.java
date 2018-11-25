package com.sgic.automation.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	protected static WebDriver driver;
	
	BasePage (WebDriver driver){
		
		this.driver = driver;
	}

}
