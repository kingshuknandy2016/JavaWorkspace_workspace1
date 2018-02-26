package amazon.practice.me;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FetchingProductDetails {
	
	

	public static void main(String[] args) {
		 String baseUrl;
		WebDriver driver=new FirefoxDriver();
		
        baseUrl = "http://www.amazon.com/s/ref=lp_1055398_nr_n_0?fst=as%3Aoff&rh=n%3A1055398%2Cn%3A!1063498%2Cn%3A3206325011&bbn=1063498&ie=UTF8&qid=1454525422&rnid=1063498";
        driver.get(baseUrl + "/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.MILLISECONDS);
        
        List<WebElement>   prop=driver.findElements(By.xpath("//li[contains(@id,'result_')]"));
        System.out.println(prop.size());
        for (int i = 0; i < prop.size(); i++) {
            String Detail1=prop.get(i).findElement(By.tagName("h2")).getText();
            System.out.println("Detail1=="+Detail1);
		}
       // String Detail1=prop.get(1).findElement(By.tagName("h2")).getText();
       // System.out.println("Detail1=="+Detail1);
        System.out.println("/////////////////////////");

	}


}
