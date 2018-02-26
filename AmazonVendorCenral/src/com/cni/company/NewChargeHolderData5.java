package com.cni.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


/*********************************Class**************************************/
public class NewChargeHolderData5 {

private static String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar
        .getInstance().getTime());
private static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
        + "CIN Company Details " + timeStamp + " Summary" + ".xls";

private static String  rowcolumnvalue1;
private static int i,j;


private static String dest = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\OutPut\\output.xls";
private static HSSFWorkbook myWorkBook = new HSSFWorkbook();
private static HSSFSheet mySheet = myWorkBook.getSheet("CHARGEHOLDER");


/*********************************Main Method**************************************/
/*****************************************************************************************/

public static void main(String[] args) throws IOException {


File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
FirefoxProfile firefoxProfile = new FirefoxProfile();
FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	
String baseUrl = "http://www.mca.gov.in/DCAPortalWeb/dca/MyMCALogin.do?method=setDefaultProperty&mode=32";
driver.get(baseUrl + "/");
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

 
FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData.xls");
HSSFWorkbook workbook = new HSSFWorkbook(file);
HSSFSheet  sheet1 = workbook.getSheet("MASTERDATA");
		

for(int row1=1; row1 <sheet1.getPhysicalNumberOfRows();row1++) ///first for loop
{
try
{	
	  
String CINId = sheet1.getRow(row1).getCell(1).getStringCellValue(); 
System.out.println(CINId);
driver.findElement(By.id("companyCIN")).clear();
driver.findElement(By.id("companyCIN")).sendKeys(CINId);
driver.findElement(By.id("Default")).click();
Thread.sleep(100);

WebElement chargelist = null;
try
{
chargelist=  driver.findElement(By.xpath("//tr[contains(@id,'rowlist')]"));
}
catch (Exception e)
{
System.out.println("error "+e);
}
if (chargelist != null)
{
	
looplbl: for(int t=0; t<99;t++)
{
	
/*******************************Get Total Row***************************************/
List<WebElement> TotalTableRows = driver.findElements(By.xpath("//tr[contains(@id,'rowlist')]"));
int rowSize = TotalTableRows.size();
System.out.println(rowSize);

  for (i=0; i<rowSize; i++) 
  {
	  WebElement webRow = TotalTableRows.get(i);
            
/***********************Get all cell values in each row******************************/
	  
      List<WebElement> allCells = webRow.findElements(By.tagName("td"));
      
          for (j=0; j<allCells.size(); j++)
          {
 	  
               WebElement webCell = allCells.get(j);
               rowcolumnvalue1 = webCell.getText();
               System.out.println(rowcolumnvalue1);
                            
               System.out.println(i+""+j);
              // WriteExcel(i,j,rowcolumnvalue1);
               
               
           	HSSFRow myRow = mySheet.getRow(i+1);

    	    if (myRow == null)
    	        myRow = mySheet.createRow(i);

    	    HSSFCell myCell = myRow.createCell(j);
    	    myCell.setCellValue(rowcolumnvalue1);
      	
    	    
    	    
    	    try {
    	        FileOutputStream out = new FileOutputStream(dest);
    	        myWorkBook.write(out);
    	        out.close();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
                         
                   
      }
  } 

WebElement pgnext = null;
try
{
pgnext=  driver.findElement(By.xpath("//a[@id='nextlist1']"));
}
catch (Exception e)
{
System.out.println("error "+e);
}
if (pgnext != null)
{
pgnext.click();
Thread.sleep(1000);
}
else if (pgnext == null)
{
driver.findElement(By.id("Default")).click();
Thread.sleep(1000);
break looplbl;
}
}







}
}catch (Exception e){e.printStackTrace();}
}
driver.close();
}






/********************************* Method to Write Excel *************************************/

public static  void WriteExcel(int row, int col, String value) throws IOException {
	try{	
	
		
		HSSFRow myRow = mySheet.getRow(row);

	    if (myRow == null)
	        myRow = mySheet.createRow(row);

	    HSSFCell myCell = myRow.createCell(col);
	    myCell.setCellValue(value);
	
					      
		}catch (Exception e)
	    {e.printStackTrace();}	             
		}




}

