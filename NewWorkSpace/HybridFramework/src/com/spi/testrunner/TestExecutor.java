package com.spi.testrunner;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.spi.dataprovider.ExcelLibrary;


public class TestExecutor {

	public static WebDriver driver;
	public static String filePath = "C:\\KRCTCAutomation.xls";
	public static String sheetName="Scenario1"; 
	public static String action=null;
	public static String idMethod=null;
	public static String locator=null;
	public static String data=null;
	public static String status=null;
	public static String scenarioStatus="PASS";

	
	public static void main(String[] args) {
		/*driver = new FirefoxDriver();*/
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
    	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.ksrtc.in/");
        TestExecutor te=new TestExecutor();
        te.run(sheetName);
	}
	
	public void run(String sheetName) {
		
		ExcelLibrary lib=new ExcelLibrary(filePath);
		int rowCount=lib.getRowCount(sheetName);
		for (int i = 1; i <= rowCount; i++) {
			action=(String) lib.getCellData(sheetName, i, 0);

			if(!action.equalsIgnoreCase("click")){
				data=(String) lib.getCellData(sheetName, i, 3);
			}
			
			if(!action.equalsIgnoreCase("verifytitle")){
				idMethod=(String) lib.getCellData(sheetName, i, 1);
				locator=(String) lib.getCellData(sheetName, i, 2);
			}
			
			
			
			
			switch (action.toLowerCase()) {
			case "verifytitle":status = verifyTitle(data);
								break;
			case "type" :status = type(idMethod,locator,data) ; 
				     	break ;
			case  "click" :status = click(idMethod,locator);
						  break;
			case "verifyText":status = verifyText(idMethod,locator,data);
			default:
				break;
			}
			lib.writeToCell(sheetName, i, 4, status);
		}
		rowCount = lib.getRowCount("ScenarioList");
		for(int i = 1;i<=rowCount;i++){
			String scenarioName = (String) lib.getCellData("ScenarioList", i, 0);
			if(scenarioName.equalsIgnoreCase(sheetName)){
				lib.writeToCell("ScenarioList", i, 2, scenarioStatus);
				break;
			}
		}
	}

	private String verifyText(String idMethod, String locator, String data) {
		String status =null;
		try{
			WebElement element = getElement(idMethod, locator);
			String actualText = element.getText();
			if(actualText.contains(data))
				status = "PASS";
			else {
				status = "FAIL";
				scenarioStatus = "FAIL";
			}
			
		}catch (Exception e) {
			status = "FAIL: "+e.getMessage();
			scenarioStatus = "FAIL";
		}
		return status;
	}

	private WebElement getElement(String idMethod, String locator) throws Exception {
		WebElement element = null;
		
		switch (idMethod.toLowerCase()) {
		case "id":element = driver.findElement(By.id(locator));
				  break;
		case "name":element = driver.findElement(By.id(locator));
		          break;
		case "classname":element = driver.findElement(By.id(locator));
		           break;
        case "xpath":element = driver.findElement(By.id(locator));
                   break;		          
		case "linktext":element = driver.findElement(By.id(locator));
		           break;
        case "partiallinktext":element = driver.findElement(By.id(locator));
                    break;
        case "cssselector":element = driver.findElement(By.id(locator));
                   break;
		case "tagname":element = driver.findElement(By.id(locator));
                   break;

		   default:throw new Exception("Invalid ID Method");
			      
		}
		return element;
	}

	private String click(String idMethod, String locator) {
		String status = "PASS";
		try{
			WebElement element = getElement(idMethod, locator);
			element.click();
			
		}catch (Exception e) {
			status = "FAIL: "+e.getMessage();
			scenarioStatus = "FAIL";
		}
		return status;
	}

	private String type(String idMethod, String locator, String data) {
		String status = "PASS";
		try{
			
			
			WebElement element = getElement(idMethod, locator);
			element.sendKeys(data);
			
		}catch (Exception e) {
			status = "FAIL: "+e.getMessage();
			scenarioStatus = "FAIL";
		}
		return status;
	}

	private String verifyTitle(String expectedTitle) {
		String status = null;
		try{
			String actualTitle = driver.getTitle();
			if(actualTitle.contains(expectedTitle)){
				status = "PASS";
			}else {
				status = "FAIL";
				scenarioStatus = "FAIL";
			}
			
		}catch (Exception e) {
			status = "FAIL: "+e.getMessage();
			scenarioStatus = "FAIL";
		}
		return status;
	}

}
