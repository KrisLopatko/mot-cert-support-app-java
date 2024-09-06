package com.ministryoftesting;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ministryoftesting.models.user.DataBuilder;
import com.ministryoftesting.models.user.TimesheetCredential;

public class LoginTest {



	@Test
	public void testStandardUserCanLogin() {
		DataBuilder dataBuilder = new DataBuilder();
		TimesheetCredential credentials = dataBuilder.getUserCredentials("user");

		// Set up the WebDriver for Chrome using WebDriverManager.
		WebDriverManager.chromedriver().setup();

		// Initialize a new instance of the ChromeDriver.
		WebDriver driver = new ChromeDriver();

		// Open a web page with the given URL in the Chrome browser.
		driver.get("http://localhost:8080");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.sendEmail(credentials.getEmail());
		loginPage.sendPassword(credentials.getPassword());
		loginPage.submitForm();


	}

}
