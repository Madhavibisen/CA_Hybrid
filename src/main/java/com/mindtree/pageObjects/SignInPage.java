package com.mindtree.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mindtree.appUIStrore.SignInPageUI;

public class SignInPage {

	public WebDriver driver;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getEmail() {
		return driver.findElement(SignInPageUI.email);
	}

	public WebElement getPassword() {
		return driver.findElement(SignInPageUI.password);
	}

	public WebElement getSubmit() {
		return driver.findElement(SignInPageUI.Submit);
	}

}
