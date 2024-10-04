package com.ministryoftesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ministryoftesting.models.user.DataBuilder;
import com.ministryoftesting.models.user.TimesheetCredential;

public class LoginTest {



	@Test
	@DisplayName("Test admin logging in")
	@ExtendWith(Screenshotter.class)
	public void testAdminUserCanLogin() {
		DataBuilder dataBuilder = new DataBuilder();
		TimesheetCredential credentials = dataBuilder.getUserCredentials("admin");
		System.out.println(credentials.getEmail());
		System.out.println(credentials.getPassword());
		System.out.println(credentials.getRole());

		// Set up the WebDriver for Chrome using WebDriverManager.
		WebDriverManager.chromedriver().setup();


		// Initialize a new instance of the ChromeDriver.
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		Screenshotter.setDriver(driver);

		// Open a web page with the given URL in the Chrome browser.
		driver.get("http://localhost:8080");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.sendEmail(credentials.getEmail());
		loginPage.sendPassword(credentials.getPassword());
		loginPage.submitForm();

		ProjectsPage projectsPage = new ProjectsPage(driver);
		String title = projectsPage.getTitle();

		assertEquals("Projects", title);

		driver.close();
		driver.quit();



	}
	}
