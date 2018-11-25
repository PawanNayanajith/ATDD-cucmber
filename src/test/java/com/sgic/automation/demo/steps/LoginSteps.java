package com.sgic.automation.demo.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.sgic.automation.demo.pages.DashBoardPage;
import com.sgic.automation.demo.pages.LoginPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {

	public static WebDriver driver;

	@Before
	public void setupTest() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Pawan\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Given("^I have OrangeHRM application loaded$")
	public void launchApplication() throws Throwable {
		driver.get("https://opensource-demo.orangehrmlive.com");
	}

	@When("^I login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void i_login_with_username_and_password(String username, String password) throws Throwable {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.validLogin(username, password);
	}

	@Then("^I should navigate to the dashboard page$")
	public void i_should_navigate_to_the_dashboard_page() throws Throwable {
		DashBoardPage dashboard = new DashBoardPage(driver);
		Assert.assertTrue(dashboard.isDashboardDisplayed());
	}

	@Then("^I should get error message \"([^\"]*)\"$")
	public void i_should_get_error_message(String expMessage) throws Throwable {
		LoginPage loginpage = new LoginPage(driver);
		Assert.assertEquals(expMessage, loginpage.getErrorMessage());		
	}

}
