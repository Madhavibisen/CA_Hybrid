package com.mindtree.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mindtree.appUIStrore.HomePageUI;
import com.mindtree.appUIStrore.SignInPageUI;

public class HomePage {

	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getSignIn() {
		return driver.findElement(HomePageUI.SignIn);
	}

}
