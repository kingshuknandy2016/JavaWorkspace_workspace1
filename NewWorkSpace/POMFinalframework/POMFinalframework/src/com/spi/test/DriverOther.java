package com.spi.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.spi.data.ExcelLibrary;
import com.spi.pages.HomePage;
import com.spi.practicepakage.PageHome;
import com.spi.practicepakage.SearchPage;
import com.spi.screenRecorder.VideoRecord;
import com.spi.testrunner.TestExecutor;

public class DriverOther {
	public static WebDriver driver; 
	
	
	// public static int rowIndex;
		public static String filePath= System.getProperty("user.dir")+"\\Lib\\KSRTC TestCase.xls";
		public static String recording="Yes";
		VideoRecord vr = new VideoRecord();
		@BeforeClass
		public void openApplication() throws Exception{
			//---Give the excel path--------------
			System.out.println(System.getProperty("user.dir"));
			/*if(filePath != null){
				ExcelLibrary el = new ExcelLibrary(filePath);
				String browserType = (String) el.getCellData("Driver Details", 0, 1);
				if(browserType.equalsIgnoreCase("firefox"))
					driver = new FirefoxDriver();
				else if(browserType.equalsIgnoreCase("chrome")){
					String serverFilePath = (String) el.getCellData("Driver Details", 1, 1);
					System.setProperty("webdriver.chrome.driver", serverFilePath);
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
				//Start Video Recording
				if(recording.equalsIgnoreCase("Yes"))
				{
				        vr.startRecording();
				}
			}*/
			}
			
		
//		public static String filePath = "C:\\MAHESH\\KSRTC TestCase.xlsx";
		
		@DataProvider
		public Object[][] getScenarioName(){
			
			List<String> ScenarioNameList = new ArrayList<String>();
			ExcelLibrary el = new ExcelLibrary(filePath);
			int rowCount = el.getRowCount("ScenarioList");
			Object[][] data = new Object[rowCount][1];
			for(int i = 1;i<=rowCount;i++){
				String	dataProviderObject=null;
				String exeStatus = (String) el.getCellData("ScenarioList", i, 1);
				String ScenarioName = (String) el.getCellData("ScenarioList", i, 0);
				if(exeStatus.equalsIgnoreCase("yes")){
				dataProviderObject=ScenarioName+"_yes";
				}else{
				dataProviderObject=ScenarioName+"_no";	
				
				}
				data[i-1][0] = dataProviderObject;
			}
			
			
//			//List<String> scenarioNameList = getScenarioNameList();
//			Object[][] data = new Object[ScenarioNameList.size()][1];
//			for(int i = 0;i<ScenarioNameList.size();i++){
//				data[i][0] = ScenarioNameList.get(i);
//			}
			return data;
		}
		
		
		@Test(dataProvider="getScenarioName")
		public void testMethod(String scenarioNameStatus){
            String[] st=scenarioNameStatus.split("_") ;
            System.out.println(st[0]+"\t"+st[1]);
/*			SearchPage sp=new SearchPage();
			PageHome Ph=new PageHome();
			switch (scenarioName) {
			case "a":sp.a();
				
				break;
				
			case "b":sp.b();
				
				break;
			case "aa":Ph.aa();
				
				break;
				
			case "bb":Ph.bb();
				
				break;

			default:
				break;
			}*/
		}

		
/*		public List<String> getScenarioNameList(){
			List<String> ScenarioNameList = new ArrayList<String>();
			ExcelLibrary el = new ExcelLibrary(filePath);
			int rowCount = el.getRowCount("ScenarioList");
			for(int i = 1;i<=rowCount;i++){
				String exeStatus = (String) el.getCellData("ScenarioList", i, 1);
				if(exeStatus.equalsIgnoreCase("yes")){
					String ScenarioName = (String) el.getCellData("ScenarioList", i, 0);
					//rowIndex=i;
					ScenarioNameList.add(ScenarioName);
				}else{
					el.writeToCell("ScenarioList", i, 2, "Skipped");
					//rowIndex=0;
				}
			}
			return ScenarioNameList;
		}*/
		
		@AfterClass
		public void closeBrowser() throws Exception{
			//Stop Video Recording
		/*	if(recording.equalsIgnoreCase("Yes"))
			{
			        vr.stopRecording();        
			}
			if(filePath != null){
				driver.close();
				driver.quit();
			}
			*/
		System.out.println("Browzer clased");
		}
}
