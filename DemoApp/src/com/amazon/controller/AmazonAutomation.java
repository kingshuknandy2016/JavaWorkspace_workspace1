package com.amazon.controller;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class AmazonAutomation {
	
	static File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	static FirefoxProfile firefoxProfile = new FirefoxProfile();
	static FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	
	public static void abc(){
		System.out.println("hiiiiiiiiiii");
		driver.get("http://www.amazon.com/Coupons");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("test");
		
	}

}
