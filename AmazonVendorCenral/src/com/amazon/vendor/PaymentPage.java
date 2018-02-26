package com.amazon.vendor;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
	
private WebElement accounting_text;

@FindBy (how = How.ID, using ="invoice-management-santana_text")
private WebElement invoice;

public PaymentPage(){
PageFactory.initElements(Browser.driver(), this);
}
public void navigatetopaymentpage()

{
	accounting_text.click();
	invoice.click();
}
}
