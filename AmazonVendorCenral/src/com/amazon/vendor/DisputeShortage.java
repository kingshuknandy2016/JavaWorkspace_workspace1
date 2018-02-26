package com.amazon.vendor;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DisputeShortage {

@FindBy(how = How.XPATH,using="//div[@class='grid-canvas']/div[1]/div[13]")
private WebElement Action1;

@FindBy(how = How.LINK_TEXT,using="Dispute shortage")
private WebElement Dispute;

public DisputeShortage()
{
PageFactory.initElements(Browser.driver(), this);
}
public void ActionButton1(){
Action1.click();
Dispute.click();
}

}


