package com.velloe.automation;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.velloe.automation.config;

public class LaunchApplication
{
	static String baseUrl;
	/*static File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	static FirefoxProfile firefoxProfile = new FirefoxProfile();*/
	//static FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	
public static void applicationLaunch() throws Exception {
	    /*config.driver = new FirefoxDriver();
        baseUrl = "https://sqa.velloe.com";
        config.driver.get(baseUrl + "/");
        config.driver.manage().window().maximize();
        config.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);*/
        
        System.setProperty("webdriver.chrome.driver","C:/chromedriver.exe");
		config.driver = new ChromeDriver();
		baseUrl = "https://sqa.velloe.com";
        config.driver.get(baseUrl + "/");
        config.driver.manage().window().maximize();
        config.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}	
}