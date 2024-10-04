package com.ministryoftesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectsPage {

	@FindBy(how = How.CSS, using = ".card-title")
	private WebElement title;

	public ProjectsPage( WebDriver driver) {
		PageFactory.initElements(driver, this);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector(".card-title")));
	}

	public String getTitle() {
		return title.getText();
	}
}
