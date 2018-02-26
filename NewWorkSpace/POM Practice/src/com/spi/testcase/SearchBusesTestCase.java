package com.spi.testcase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.spi.pages.HomePage;
import com.spi.pages.SearchResultPage;
import com.spi.pages.SignInPage;

public class SearchBusesTestCase {

	@Test
	public void testMethod(){
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.ksrtc.in/");
		
		HomePage hmp = new HomePage(driver);
		hmp.searchBuses("MYSURU", "MANGALURU", "29", "mang", "mys");
		SearchResultPage srp = new SearchResultPage(driver);
		srp.clickOnHome();
		hmp = PageFactory.initElements(driver, HomePage.class);
		hmp.clickOnMemberLink();
		SignInPage sp = new SignInPage(driver);
		sp.clickOnShowLink();
		driver.close();
	}
	
}
