package com.amazon.vendor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class InvoicePage {
	
@FindBy (how = How.LINK_TEXT,using ="Review/dispute shortages")	
private WebElement disputebuton;

public InvoicePage(){
PageFactory.initElements(Browser.driver(), this);
}
public void navigatetodisputepage()
{
	disputebuton.click();
}

public DisputeShortage Actionbutton() {
	DisputeShortage search = new DisputeShortage();
return search;
}

}
