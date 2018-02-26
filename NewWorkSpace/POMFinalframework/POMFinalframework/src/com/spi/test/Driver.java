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
import com.spi.screenRecorder.VideoRecord;
import com.spi.testrunner.TestExecutor;

public class Driver {
	
	public static WebDriver driver; 
	
	
    public static int rowIndex;
	public static String filePath= System.getProperty("user.dir")+"\\Lib\\KSRTC TestCase.xls";
	public static String recording="Yes";
	VideoRecord vr = new VideoRecord();
	@BeforeClass
	public void openApplication() throws Exception{
		//---Give the excel path--------------
		//System.out.println(System.getProperty("user.dir"));
		if(filePath != null){
			ExcelLibrary el = new ExcelLibrary(filePath);
			String browserType = (String) el.getCellData("Driver Details", 0, 1);
			if(browserType.equalsIgnoreCase("firefox"))
				driver = new FirefoxDriver();
			else if(browserType.equalsIgnoreCase("chrome")){
				String serverFilePath = (String) el.getCellData("Driver Details", 1, 1);
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
				driver = new ChromeDriver();
			}else if(browserType.equalsIgnoreCase("ie")){
				String serverFilePath = (String) el.getCellData("Driver Details", 1, 1);
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\driver\\IEDriverServer.exe");
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
		}
		}
		
	
//	public static String filePath = "C:\\MAHESH\\KSRTC TestCase.xlsx";
	
	@DataProvider
	public Object[][] getScenarioName(){
		List<String> scenarioNameList = getScenarioNameList();
		Object[][] data = new Object[scenarioNameList.size()][1];
		for(int i = 0;i<scenarioNameList.size();i++){
			data[i][0] = scenarioNameList.get(i);
		}
		return data;
	}
	/*@DataProvider
	public Object[][] getScenarioName(){
	    Object[][] data=new Object[][];
		return  data;
	}*/
	
	@Test(dataProvider="getScenarioName")
	//@Test(priority=0)
	public void testMethod(String scenarioName){
		TestExecutor te = new TestExecutor();
		te.run();
	}

	
	public List<String> getScenarioNameList(){
		List<String> ScenarioNameList = new ArrayList<String>();
		ExcelLibrary el = new ExcelLibrary(filePath);
		int rowCount = el.getRowCount("ScenarioList");
		for(int i = 1;i<=rowCount;i++){
			String exeStatus = (String) el.getCellData("ScenarioList", i, 1);
			if(exeStatus.equalsIgnoreCase("yes")){
				String ScenarioName = (String) el.getCellData("ScenarioList", i, 0);
				rowIndex=i;
				ScenarioNameList.add(ScenarioName);
			}else{
				el.writeToCell("ScenarioList", i, 2, "Skipped");
				rowIndex=0;
			}
		}
		return ScenarioNameList;
	}
	
	@AfterClass
	public void closeBrowser() throws Exception{
		//Stop Video Recording
		if(recording.equalsIgnoreCase("Yes"))
		{
		        vr.stopRecording();        
		}
		if(filePath != null){
			driver.close();
			driver.quit();
		}
		
	}
}
