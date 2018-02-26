package scrape.book.numbers;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class RegExpCollectPhoneNumberRevi {
	private static String Company, PhoneNumber, Website, Country;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String Scraper(String Companyexcel,String Countryexcel){
		System.out.println("*****************inside Scrapper function****************");
		System.out.println("Company:"+Companyexcel+"\t"+"Country:"+Countryexcel);
		//********************************************************************************************//
		System.setProperty("webdriver.chrome.driver","C:/Users/Kingshuk Nandy/workspace/CompanyPhoneNo/Files/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String baseUrl = "http://www.google.com";
		driver.get(baseUrl + "/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     
        

			try {
				String CountrynCompany = Companyexcel + " " + Countryexcel;
				System.out.println(CountrynCompany);
				WebElement element = driver.findElement(By.name("q"));
				element.click();
				element.sendKeys(CountrynCompany);
				element.submit();
				Thread.sleep(2000);

				WebElement properlink = null;
				try {
					properlink = driver.findElement(By.xpath("//cite"));
				} catch (Exception e) {
					System.out.println("error " + e);
				}
				if (properlink != null) {
					WebElement link = driver.findElement(By.className("r")).findElement(By.tagName("a"));
					String wiki = link.getText();
					System.out.println(wiki);
					Thread.sleep(1000);
					if (wiki.contains("Wikipedia")) {
						driver.findElement(By.xpath("//li[2]//h3[@class='r']")).click();
					} else {
						driver.findElement(By.className("r")).findElement(By.tagName("a")).click();
					}

					/***************** Find By anchor tag ****************************************/

					WebElement ContactusCapital = null;

					try {
						ContactusCapital = driver
								.findElement(By
										.xpath("(//*/.[contains(text(),'CONTACT')])[1]"));
					} catch (Exception e) {
						System.out.println("error " + e);
					}
					if (ContactusCapital != null) {
						try {
							ContactusCapital.click();
							// driver.findElement(By.linkText("CONTACT US")).click();
						} catch (Exception e) {
							System.out.println("error " + e);
						}
					}

					WebElement ContactusSmall = null;
					try {
						ContactusSmall = driver
								.findElement(By
										.xpath("(//*/.[contains(text(),'Contact')])[1]"));
					} catch (Exception e) {
						System.out.println("error " + e);
					}
					if (ContactusSmall != null) {
						try {
							ContactusSmall.click();
							// driver.findElement(By.linkText("Contact Us")).click();
						} catch (Exception e) {
							System.out.println("error " + e);
						}

					}

					WebElement ContactusAllSmall = null;

					try {
						ContactusAllSmall = driver
								.findElement(By
										.xpath("(//*/.[contains(text(),'contact')])[1]"));

					} catch (Exception e) {
						System.out.println("error " + e);
					}

					if (ContactusAllSmall != null) {

						try {
							ContactusAllSmall.click();
							// driver.findElement(By.linkText("contact us")).click();
						} catch (Exception e) {
							System.out.println("error " + e);
						}
					}

					/***************** Find By span tag ****************************************/

					/***************** Find Phone String ****************************************/

					WebElement PhoneNumberWeb = driver.findElement(By
							.tagName("html"));

					String phone = PhoneNumberWeb.getText();

					System.out.println(phone);

					// String pattern =
					// "^\\s*(?:\\+?(\\d{1,3}))?(\\d{2}-\\d{3}-\\d{7})?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$/gm";

					Pattern r = Pattern
							.compile("\\d{2}(\\))?(-|.|\\s)?\\d{3}(-|.|\\s)?\\d{7}|((|\\+)?\\d{1,2}?())?(-|.|\\s)?\\d{3}(-|.|\\s)?\\d{3}(-|.|\\s)?\\d{4}|\\d{5}(\\))?(-|.|\\s)?\\d{6}|[(]?\\d{3}[)]?(-|.|\\s)?\\d{3}(-|.|\\s)?\\d{4}|\\d{2}(-|.|\\s)?\\d{4}(-|.|\\s)?\\d{4}|\\d{1}(\\))?(-|.|\\s)?\\d{3}(-)[A-Z]{1,10}|^\\+?[0-9. ()-]{10,25}$");
					// Pattern r = Pattern.compile(pattern);

					// Pattern pattern =
					// Pattern.compile("^(\\(?\\d\\d\\d\\)?)( |-|\\.)?\\d\\d\\d( |-|\\.)?\\d{4,4}(( |-|\\.)?[ext\\.]+ ?\\d+)?$");
					Matcher matcher = r.matcher(phone);
					if (matcher.find()) {

						System.out.println("**********************"+matcher.group(0));
                         
						Company = Companyexcel;
						PhoneNumber = matcher.group(0);
						Website = driver.getCurrentUrl();
						/*resultoutput_pass();
						FileOutputStream fileOut = new FileOutputStream(
								filename);
						hwb.write(fileOut);*/
						System.out.println("////////////////11"+PhoneNumber);
					}else{
						PhoneNumber="Phone No not found";
						System.out.println("////////////////11"+PhoneNumber);
					}
				}

				driver.get(baseUrl + "/");

			} catch (Exception e) {
				System.out.println("error " + e);
			}
	     //}
		//driver.close();
		System.out.println("////////////////22"+PhoneNumber);
		
		
		
		//********************************************************************************************//
	   return PhoneNumber;
		
	}

}
