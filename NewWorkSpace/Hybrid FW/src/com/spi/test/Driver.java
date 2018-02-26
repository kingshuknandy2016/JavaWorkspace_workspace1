package com.spi.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.spi.data.ExcelLibrary;
import com.spi.testrunner.TestExecutor;

public class Driver {
	
	public static WebDriver driver; 
   @BeforeClass
	public void openApplication(){
		//filePath = JOptionPane.showInputDialog("Enter the File Path");
		if(filePath != null){
			ExcelLibrary el = new ExcelLibrary(filePath);
			String browserType = (String) el.getCellData("Driver Details", 0, 1);
			if(browserType.equalsIgnoreCase("firefox"))
				driver = new FirefoxDriver();
			else if(browserType.equalsIgnoreCase("chrome")){
				//String serverFilePath = (String) el.getCellData("Driver Details", 1, 1);
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
				driver = new ChromeDriver();
			}else if(browserType.equalsIgnoreCase("ie")){
				String serverFilePath = (String) el.getCellData("Driver Details", 1, 1);
				System.setProperty("webdriver.ie.driver", serverFilePath);
				driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String url = (String) el.getCellData("Driver Details", 2, 1);
			driver.get(url);
		}
		}
		
	
	public static String filePath = "G:\\Java Work spaces\\workspace\\NewWorkSpace\\Hybrid FW\\KSRTC TestCase.xlsx";
	//public static String filePath;
	@DataProvider
	public Object[][] getSheetName(){
		List<String> sheetNameList = getSheetNameList();
		Object[][] data = new Object[sheetNameList.size()][1];
		for(int i = 0;i<sheetNameList.size();i++){
			data[i][0] = sheetNameList.get(i);
		}
		return data;
	}
	
	
	@Test(dataProvider="getSheetName")
	public void testMethod(String sheetName){
		TestExecutor te = new TestExecutor();
		te.run(sheetName);
	}

	
	public List<String> getSheetNameList(){
		List<String> sheetNameList = new ArrayList<String>();
		ExcelLibrary el = new ExcelLibrary(filePath);
		int rowCount = el.getRowCount("ScenarioList");
		for(int i = 1;i<=rowCount;i++){
			String exeStatus = (String) el.getCellData("ScenarioList", i, 1);
			if(exeStatus.equalsIgnoreCase("yes")){
				String sheetName = (String) el.getCellData("ScenarioList", i, 0);
				sheetNameList.add(sheetName);
			}else{
				el.writeToCell("ScenarioList", i, 2, "Skipped");
			}
		}
		return sheetNameList;
	}
	
	@AfterClass
	public void closeBrowser(){
		if(filePath != null){
			driver.close();
			driver.quit();
		}
		
	}
}
