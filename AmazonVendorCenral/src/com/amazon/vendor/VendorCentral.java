package com.amazon.vendor;

import org.junit.Test;
import com.amazon.vendor.LoginPage;

public class VendorCentral {

LoginPage login = new LoginPage();
PaymentPage pmnt = new PaymentPage();
InvoicePage invc = new InvoicePage();
DisputeShortage pds = new DisputeShortage();
	
@Test
public void logintovendorcentral1()
{
	login.load();
	login.logincredential("siddharth869a@gmail.com", "joyguru5%");
	pmnt.navigatetopaymentpage();
	invc.navigatetodisputepage();
	pds.ActionButton1();
	//login.closeapplication();	
}

}
