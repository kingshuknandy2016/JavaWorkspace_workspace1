package com.spi.testrunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.spi.data.ExcelLibrary;
import com.spi.pages.HomePage;
import com.spi.pages.SearchResultPage;
import com.spi.pages.SignInPage;
import com.spi.test.Driver;

public class TestExecutor {

	public static String description=null;
	//TestExecutor te=new TestExecutor();
	public void run() {
		String exeStatus=null;
		TestExecutor te=new TestExecutor();
		ExcelLibrary el = new ExcelLibrary(Driver.filePath);
		int rowCount = el.getRowCount("ScenarioList");
		for (int i = 1; i <=rowCount; i++) {
			String ToExecute = (String) el.getCellData("ScenarioList", i, 1);
			if(exeStatus.equalsIgnoreCase("yes")){
				String ScenarioName = (String) el.getCellData("ScenarioList", i, 0);
				switch (ScenarioName.toLowerCase()) {
				case "searchbuses": exeStatus=te.scenarioOne();
									break;
				case "tabsclick":    exeStatus="Not yet developed";
		            				break;
				case "loginpagetest": exeStatus="yet to be developed";
		            				break;
				default:
					break;
				
				}
				
				
				el.writeToCell(ScenarioName, i, 2, exeStatus);
				el.writeToCell(ScenarioName, Driver.rowIndex, 3, description);
				
			}else{
				el.writeToCell("ScenarioList", i, 2, "Skipped");
			
			}
		}
		
		
		
		
		//System.out.println("Test getting executed::"+scenarioName);


	}
	private String scenarioOne() {
		String status="Pass";
		try {
			HomePage hmp = new HomePage(Driver.driver);
			hmp=PageFactory.initElements(Driver.driver, HomePage.class);
			hmp.searchBuses("MYSURU", "MANGALURU", "29", "mang", "mys");
			/*SearchResultPage srp = new SearchResultPage(Driver.driver);
			srp.clickOnHome();
			hmp = PageFactory.initElements(Driver.driver, HomePage.class);
			hmp.clickOnMemberLink();
			SignInPage sp = new SignInPage(Driver.driver);
			sp.clickOnShowLink();*/
			description="Successfully Passed";
		
	} catch (Exception e1) {
		status="Fail";
		description="Error:"+e1;
	}
		
		return status;
	}
	

}

