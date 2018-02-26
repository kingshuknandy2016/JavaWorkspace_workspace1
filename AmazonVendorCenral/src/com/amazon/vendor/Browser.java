package com.amazon.vendor;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Browser {
	
	static File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	static FirefoxProfile firefoxProfile = new FirefoxProfile();
	private static WebDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	
	public static WebDriver driver() {
	return driver;
	}
	public static void open(String url) {
	driver.get(url);
	}
	public static void close() {
	driver.close();
	}	
	

}
