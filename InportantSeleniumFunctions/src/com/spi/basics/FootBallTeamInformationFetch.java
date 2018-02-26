package com.spi.basics;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FootBallTeamInformationFetch {


	public static void main(String[] args) throws InterruptedException {
		     WebDriver driver=new FirefoxDriver();
			driver.get("https://en.wikipedia.org/wiki/List_of_NCAA_Division_I_FCS_football_programs");
			Thread.sleep(2000); 
			driver.manage().window().maximize() ;
			List<WebElement> ClubNames = driver.findElements(By.xpath("(//table[@class='wikitable sortable jquery-tablesorter']//td)"));
			int i=1;
			Iterator ite1=ClubNames.iterator();
			while(ite1.hasNext()){
				String ClubName=(String) ite1.next();
				
				i=i+5;
			}
			

	}

}
