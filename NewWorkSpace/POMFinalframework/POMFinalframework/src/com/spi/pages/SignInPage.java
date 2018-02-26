package com.spi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
	WebDriver driver;
	@FindBy(linkText="Show")
	WebElement showLink;
	@FindBy(linkText="Home")
	WebElement homeLink;
	
	public void clickOnHome(){
		homeLink.click();
	}
	public void clickOnShowLink(){
		showLink.click();
	}
	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
