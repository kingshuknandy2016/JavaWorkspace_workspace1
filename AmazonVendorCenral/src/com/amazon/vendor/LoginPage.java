package com.amazon.vendor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import static org.junit.Assert.*;

public class LoginPage extends LoadableComponent <LoginPage> {
	
private	WebElement username;
private WebElement password;
@FindBy (how =How.ID, using="login-button-container")
private WebElement loginbutton;
private String url = "https://vendorcentral.amazon.com";
private static String title = "Vendor Central";

public LoginPage() {
    PageFactory.initElements(Browser.driver(), this);
}

@Override
protected void load() {
	Browser.open(url);	
}

@Override
protected void isLoaded() throws Error {
assertTrue(Browser.driver().getTitle().equals(title));
}

public void logincredential (String Username,String Password){
	   username.sendKeys(Username);
	   password.sendKeys(Password);
	   loginbutton.click();
	}

public void closeapplication(){
    Browser.close();
}

}





