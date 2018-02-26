package com.spi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	
	WebDriver driver;
	@FindBy(id="matchStartPlace")
	WebElement fromTextField;
	
	@FindBy(id="matchEndPlace")
	WebElement toTextField;
	
	@FindBy(id="txtdeptDateRtrip")
	WebElement onDateField;
	
	@FindBy(xpath="//a[contains(@class,'redbtn')]")
	WebElement searchBtn;
	
	@FindBy(partialLinkText="[Sign In]")
	WebElement memberSignInLink;
	
	@FindBy(linkText="[PNR Enquiry]")
	WebElement pnrLink;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchBuses(String fromPlace,String toPlace,String date,String toPlacePrefix,String fromPlacePrefix){
		fromTextField.sendKeys(fromPlacePrefix);
		driver.findElement(By.linkText(fromPlace)).click();
		toTextField.sendKeys(toPlacePrefix);
		driver.findElement(By.linkText(toPlace)).click();
		onDateField.click();
		String xpathForDay = "//div[contains(@class,'group-first')]//a[text()='"+date+"']"; 
		driver.findElement(By.xpath(xpathForDay)).click();
		searchBtn.click();
	}
	
	
	public void clickOnMemberLink(){
		memberSignInLink.click();
	}
	public void clickOnPnrLink(){
		pnrLink.click();
	}
	
	

}
