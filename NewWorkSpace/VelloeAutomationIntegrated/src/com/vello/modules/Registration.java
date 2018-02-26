package com.vello.modules;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.velloe.repository.OR;
import com.velloe.repository.Parameter;
import com.velloe.repository.config;

public class Registration {
	
	static String ModuleName,TestId,Expected,Actual,Name,Email,PhoneNo,Password,ConfirmPassword,SecurityAnwser;
		
	public void RegistrationValidity() throws IOException, InterruptedException{
		
		
		config.driver.findElement(By.xpath("//a[@Class='banner-image']")).click();
		Thread.sleep(1000);
		config.driver.findElement(OR.GetLocator("Registration", "RegisterButton")).click();
		Thread.sleep(1000);
		
		//test case---TC001_ProfileType_default_Business---------------------

		ModuleName = "Registration";
		TestId = "TC001_ProfileType_default_Business";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement BusinessAccountDefault=null;
		
		
		try {
			
			BusinessAccountDefault=config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness"));
			String Defaultpage=BusinessAccountDefault.getText();
			if(Defaultpage.equalsIgnoreCase(Expected)){
				Actual ="By Default '"+ Defaultpage +"' Profile is displayed";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			}else{
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		Thread.sleep(4000);
		
		
		//test case---TC002_Validate_Name Field_for_Customer ProfileType------------------------------
		
		ModuleName = "Registration";
		TestId = "TC002_Validate_Name Field_for_Customer ProfileType";
		Expected= Parameter.GetInput(ModuleName, TestId, 2);
		
		WebElement NameField = null;
		try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		//Thread.sleep(8000);
		NameField=config.driver.findElement(OR.GetLocator("Registration", "NameFeild"));
		if(NameField.isDisplayed()){
			//String NamefieldText = config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).findElement(By.className("gfield_description")).getText();
			Actual="Name text-field is displayed";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
		}else{
			Actual="Name text-field is not-displayed";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			
		}
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		Thread.sleep(4000);
		
		
		
		
		
		//test case---TC003_Validate_Mobile Number Field_for_CustomerProfiletype ------------------------------
		ModuleName = "Registration";
		TestId = "TC003_Validate_Mobile Number Field_for_CustomerProfiletype ";
		Expected= Parameter.GetInput(ModuleName, TestId, 2);
		WebElement MobileNoFeild = null;
		try{
			MobileNoFeild=config.driver.findElement(OR.GetLocator("Registration", "MobileNo"));
			if(MobileNoFeild.isEnabled()){
				Actual="Mobile Number text feild is Displayed";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			}else{
				Actual="Mobile Number text feild is not Displayed";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
			
		}catch(Exception e){
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
	
	//-----test case---TC004_Enter different passwords for “Enter password” and “Confirm Password” text-field
	//ModuleName,TestId,Expected,Actual,Name,Email,PhoneNo,Password,ConfirmPassword,SecurityAnwser
	ModuleName = "Registration";
	TestId = "TC004_Enter_different_passwords_for_Enterpassword_and_ConfirmPassword_textfield";
	Expected= Parameter.GetInput(ModuleName, TestId, 2);
	Name=Parameter.GetInput(ModuleName, TestId, 5);
	Email=Parameter.GetInput(ModuleName, TestId, 6);
	PhoneNo=Parameter.GetInput(ModuleName, TestId, 7);
	Password=Parameter.GetInput(ModuleName, TestId, 8);
	ConfirmPassword=Parameter.GetInput(ModuleName, TestId, 9);
	SecurityAnwser=Parameter.GetInput(ModuleName, TestId, 11);
	WebElement ErrorMsgFeild=null;
	try {
		//config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		//config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).sendKeys(Name);
		config.driver.findElement(OR.GetLocator("Registration", "PrimaryEmail")).sendKeys(Email);
		config.driver.findElement(OR.GetLocator("Registration", "MobileNo")).sendKeys(PhoneNo);
		config.driver.findElement(OR.GetLocator("Registration", "EnterPassword")).sendKeys(Password);
		config.driver.findElement(OR.GetLocator("Registration", "ConfirmPassword")).sendKeys(ConfirmPassword);
		
		WebElement dropdown= config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']"));
		dropdown.click();
		config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']//div[@class='chosen-drop']/ul[@class='chosen-results']//li[2]")).click();
		
		config.driver.findElement(OR.GetLocator("Registration", "SecurityAnwser")).sendKeys(SecurityAnwser);
		config.driver.findElement(By.xpath("//input[@id='choice_3_7_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='choice_3_13_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='gform_submit_button_3']")).click();
		ErrorMsgFeild=config.driver.findElement(By.xpath("//div[contains(text(),'Your passwords do not match.')]"));
		Actual=ErrorMsgFeild.getText();
		if(Expected.equalsIgnoreCase(Actual)){
			
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			
		}else {
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
	} catch (Exception e){
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}

	
	
	
	//-----test case---TC005_Enter passwords for “Enter password” text -field and leave “Confirm Password” text-field empty
	config.driver.navigate().refresh();
	ModuleName = "Registration";
	TestId = "TC005_Enter_passwords_EnterPassword_textfield_and_leave_ConfirmPassword_textfield_empty";
	Expected= Parameter.GetInput(ModuleName, TestId, 2);
	Name=Parameter.GetInput(ModuleName, TestId, 5);
	Email=Parameter.GetInput(ModuleName, TestId, 6);
	PhoneNo=Parameter.GetInput(ModuleName, TestId, 7);
	Password=Parameter.GetInput(ModuleName, TestId, 8);
	ConfirmPassword=Parameter.GetInput(ModuleName, TestId, 9);
	SecurityAnwser=Parameter.GetInput(ModuleName, TestId, 11);
	WebElement ErrorMsgFeild1=null;
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).sendKeys(Name);
		config.driver.findElement(OR.GetLocator("Registration", "PrimaryEmail")).sendKeys(Email);
		config.driver.findElement(OR.GetLocator("Registration", "MobileNo")).sendKeys(PhoneNo);
		config.driver.findElement(OR.GetLocator("Registration", "EnterPassword")).sendKeys(Password);
		config.driver.findElement(OR.GetLocator("Registration", "ConfirmPassword")).sendKeys(ConfirmPassword);
		
		
		WebElement dropdown= config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']"));
		dropdown.click();
		config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']//div[@class='chosen-drop']/ul[@class='chosen-results']//li[2]")).click();
		//config.driver.findElement(By.xpath("//div[@class='chosen-container chosen-container-single']//span[contains(text(),'Choose a security question ...')]")).click();
		//config.driver.findElement(By.xpath("//select[@id='input_3_5']//option[2]")).click();
		//Thread.sleep(4000);
		config.driver.findElement(OR.GetLocator("Registration", "SecurityAnwser")).sendKeys(SecurityAnwser);
		config.driver.findElement(By.xpath("//input[@id='choice_3_7_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='choice_3_13_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='gform_submit_button_3']")).click();
		ErrorMsgFeild1=config.driver.findElement(By.xpath("//div[contains(text(),'Your passwords do not match.')]"));
		Actual=ErrorMsgFeild1.getText();
		if(Expected.equalsIgnoreCase(Actual)){
			
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			
		}else {
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
	} catch (Exception e){
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	
	Thread.sleep(4000);
	//-----test case---TC006_Leave “Enter password”  text -field empty and enter “Confirm Password” 
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC006_Leave_Enterpassword_textfield_empty_and_enter_ConfirmPassword";
	Expected= Parameter.GetInput(ModuleName, TestId, 2);
	Name=Parameter.GetInput(ModuleName, TestId, 5);
	Email=Parameter.GetInput(ModuleName, TestId, 6);
	PhoneNo=Parameter.GetInput(ModuleName, TestId, 7);
	Password=Parameter.GetInput(ModuleName, TestId, 8);
	ConfirmPassword=Parameter.GetInput(ModuleName, TestId, 9);
	SecurityAnwser=Parameter.GetInput(ModuleName, TestId, 11);
	WebElement ErrorMsgFeild2=null;
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).sendKeys(Name);
		config.driver.findElement(OR.GetLocator("Registration", "PrimaryEmail")).sendKeys(Email);
		config.driver.findElement(OR.GetLocator("Registration", "MobileNo")).sendKeys(PhoneNo);
		config.driver.findElement(OR.GetLocator("Registration", "EnterPassword")).sendKeys(Password);
		config.driver.findElement(OR.GetLocator("Registration", "ConfirmPassword")).sendKeys(ConfirmPassword);
		
		
		WebElement dropdown= config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']"));
		dropdown.click();
		config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']//div[@class='chosen-drop']/ul[@class='chosen-results']//li[2]")).click();
		//config.driver.findElement(By.xpath("//div[@class='chosen-container chosen-container-single']//span[contains(text(),'Choose a security question ...')]")).click();
		//config.driver.findElement(By.xpath("//select[@id='input_3_5']//option[2]")).click();
		//Thread.sleep(4000);
		config.driver.findElement(OR.GetLocator("Registration", "SecurityAnwser")).sendKeys(SecurityAnwser);
		config.driver.findElement(By.xpath("//input[@id='choice_3_7_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='choice_3_13_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='gform_submit_button_3']")).click();
		ErrorMsgFeild2=config.driver.findElement(By.xpath("//div[contains(text(),'Your passwords do not match.')]"));
		Actual=ErrorMsgFeild2.getText();
		if(Expected.equalsIgnoreCase(Actual)){
			
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			
		}else {
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
	} catch (Exception e){
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	
	Thread.sleep(4000);
	

	//-----test case---TC007_Leave_ both _Enter password_& _Confirm Password 
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC007_Leave_ both _Enter password_& _Confirm Password";
	Expected= Parameter.GetInput(ModuleName, TestId, 2);
	Name=Parameter.GetInput(ModuleName, TestId, 5);
	Email=Parameter.GetInput(ModuleName, TestId, 6);
	PhoneNo=Parameter.GetInput(ModuleName, TestId, 7);
	Password=Parameter.GetInput(ModuleName, TestId, 8);
	ConfirmPassword=Parameter.GetInput(ModuleName, TestId, 9);
	SecurityAnwser=Parameter.GetInput(ModuleName, TestId, 11);
	WebElement ErrorMsgFeild3=null;
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).sendKeys(Name);
		config.driver.findElement(OR.GetLocator("Registration", "PrimaryEmail")).sendKeys(Email);
		config.driver.findElement(OR.GetLocator("Registration", "MobileNo")).sendKeys(PhoneNo);
		config.driver.findElement(OR.GetLocator("Registration", "EnterPassword")).sendKeys(Password);
		config.driver.findElement(OR.GetLocator("Registration", "ConfirmPassword")).sendKeys(ConfirmPassword);
		
		
		WebElement dropdown= config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']"));
		dropdown.click();
		config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']//div[@class='chosen-drop']/ul[@class='chosen-results']//li[2]")).click();
		//config.driver.findElement(By.xpath("//div[@class='chosen-container chosen-container-single']//span[contains(text(),'Choose a security question ...')]")).click();
		//config.driver.findElement(By.xpath("//select[@id='input_3_5']//option[2]")).click();
		//Thread.sleep(4000);
		config.driver.findElement(OR.GetLocator("Registration", "SecurityAnwser")).sendKeys(SecurityAnwser);
		config.driver.findElement(By.xpath("//input[@id='choice_3_7_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='choice_3_13_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='gform_submit_button_3']")).click();
		ErrorMsgFeild3=config.driver.findElement(By.xpath("//div[contains(text(),'This field is required.')]"));
		Actual=ErrorMsgFeild3.getText();
		if(Expected.equalsIgnoreCase(Actual)){
			
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			
		}else {
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
	} catch (Exception e){
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	
	Thread.sleep(4000);


	//-----test case---TC008_NameFeild_Empty
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC008_NameFeild_Empty";
	Expected= Parameter.GetInput(ModuleName, TestId, 2);
	Name=Parameter.GetInput(ModuleName, TestId, 5);
	Email=Parameter.GetInput(ModuleName, TestId, 6);
	PhoneNo=Parameter.GetInput(ModuleName, TestId, 7);
	Password=Parameter.GetInput(ModuleName, TestId, 8);
	ConfirmPassword=Parameter.GetInput(ModuleName, TestId, 9);
	SecurityAnwser=Parameter.GetInput(ModuleName, TestId, 11);
	WebElement NameFeildEmpty=null;
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).sendKeys(Name);
		config.driver.findElement(OR.GetLocator("Registration", "PrimaryEmail")).sendKeys(Email);
		config.driver.findElement(OR.GetLocator("Registration", "MobileNo")).sendKeys(PhoneNo);
		config.driver.findElement(OR.GetLocator("Registration", "EnterPassword")).sendKeys(Password);
		config.driver.findElement(OR.GetLocator("Registration", "ConfirmPassword")).sendKeys(ConfirmPassword);
		
		
		WebElement dropdown= config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']"));
		dropdown.click();
		config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']//div[@class='chosen-drop']/ul[@class='chosen-results']//li[2]")).click();
		//config.driver.findElement(By.xpath("//div[@class='chosen-container chosen-container-single']//span[contains(text(),'Choose a security question ...')]")).click();
		//config.driver.findElement(By.xpath("//select[@id='input_3_5']//option[2]")).click();
		//Thread.sleep(4000);
		config.driver.findElement(OR.GetLocator("Registration", "SecurityAnwser")).sendKeys(SecurityAnwser);
		config.driver.findElement(By.xpath("//input[@id='choice_3_7_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='choice_3_13_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='gform_submit_button_3']")).click();
		NameFeildEmpty=config.driver.findElement(By.xpath("//div[contains(text(),'This field is required.')]"));
		Actual=NameFeildEmpty.getText();
		if(Expected.equalsIgnoreCase(Actual)){
			
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			
		}else {
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
	} catch (Exception e){
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	
	Thread.sleep(4000);
	
	//-----test case---TC009_EmailAddress_Empty
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC009_EmailAddress_Empty";
	Expected= Parameter.GetInput(ModuleName, TestId, 2);
	Name=Parameter.GetInput(ModuleName, TestId, 5);
	Email=Parameter.GetInput(ModuleName, TestId, 6);
	PhoneNo=Parameter.GetInput(ModuleName, TestId, 7);
	Password=Parameter.GetInput(ModuleName, TestId, 8);
	ConfirmPassword=Parameter.GetInput(ModuleName, TestId, 9);
	SecurityAnwser=Parameter.GetInput(ModuleName, TestId, 11);
	WebElement EmailFeildEmpty=null;
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).sendKeys(Name);
		config.driver.findElement(OR.GetLocator("Registration", "PrimaryEmail")).sendKeys(Email);
		config.driver.findElement(OR.GetLocator("Registration", "MobileNo")).sendKeys(PhoneNo);
		config.driver.findElement(OR.GetLocator("Registration", "EnterPassword")).sendKeys(Password);
		config.driver.findElement(OR.GetLocator("Registration", "ConfirmPassword")).sendKeys(ConfirmPassword);
		
		
		WebElement dropdown= config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']"));
		dropdown.click();
		config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']//div[@class='chosen-drop']/ul[@class='chosen-results']//li[2]")).click();
		//config.driver.findElement(By.xpath("//div[@class='chosen-container chosen-container-single']//span[contains(text(),'Choose a security question ...')]")).click();
		//config.driver.findElement(By.xpath("//select[@id='input_3_5']//option[2]")).click();
		//Thread.sleep(4000);
		config.driver.findElement(OR.GetLocator("Registration", "SecurityAnwser")).sendKeys(SecurityAnwser);
		config.driver.findElement(By.xpath("//input[@id='choice_3_7_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='choice_3_13_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='gform_submit_button_3']")).click();
		EmailFeildEmpty=config.driver.findElement(By.xpath("//div[contains(text(),'This field is required.')] "));
		Actual=EmailFeildEmpty.getText();
		if(Expected.equalsIgnoreCase(Actual)){
			
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			
		}else {
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
	} catch (Exception e){
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	
	Thread.sleep(4000);

	
	//-----test case---TC010_Mobile Number Field_Empty
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC010_Mobile Number Field_Empty";
	Expected= Parameter.GetInput(ModuleName, TestId, 2);
	Name=Parameter.GetInput(ModuleName, TestId, 5);
	Email=Parameter.GetInput(ModuleName, TestId, 6);
	PhoneNo=Parameter.GetInput(ModuleName, TestId, 7);
	Password=Parameter.GetInput(ModuleName, TestId, 8);
	ConfirmPassword=Parameter.GetInput(ModuleName, TestId, 9);
	SecurityAnwser=Parameter.GetInput(ModuleName, TestId, 11);
	WebElement MobileFeildEmpty=null;
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).sendKeys(Name);
		config.driver.findElement(OR.GetLocator("Registration", "PrimaryEmail")).sendKeys(Email);
		config.driver.findElement(OR.GetLocator("Registration", "MobileNo")).sendKeys(PhoneNo);
		config.driver.findElement(OR.GetLocator("Registration", "EnterPassword")).sendKeys(Password);
		config.driver.findElement(OR.GetLocator("Registration", "ConfirmPassword")).sendKeys(ConfirmPassword);
		
		
		WebElement dropdown= config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']"));
		dropdown.click();
		config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']//div[@class='chosen-drop']/ul[@class='chosen-results']//li[2]")).click();
		//config.driver.findElement(By.xpath("//div[@class='chosen-container chosen-container-single']//span[contains(text(),'Choose a security question ...')]")).click();
		//config.driver.findElement(By.xpath("//select[@id='input_3_5']//option[2]")).click();
		//Thread.sleep(4000);
		config.driver.findElement(OR.GetLocator("Registration", "SecurityAnwser")).sendKeys(SecurityAnwser);
		config.driver.findElement(By.xpath("//input[@id='choice_3_7_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='choice_3_13_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='gform_submit_button_3']")).click();
		MobileFeildEmpty=config.driver.findElement(By.xpath("//div[contains(text(),'This field is required.')]"));
		Actual=MobileFeildEmpty.getText();
		if(Expected.equalsIgnoreCase(Actual)){
			
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			
		}else {
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
	} catch (Exception e){
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	
	Thread.sleep(4000);

	
	//-----test case---TC011_Security_Question_not_chosen
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC011_Security_Question_not_chosen";
	Expected= Parameter.GetInput(ModuleName, TestId, 2);
	Name=Parameter.GetInput(ModuleName, TestId, 5);
	Email=Parameter.GetInput(ModuleName, TestId, 6);
	PhoneNo=Parameter.GetInput(ModuleName, TestId, 7);
	Password=Parameter.GetInput(ModuleName, TestId, 8);
	ConfirmPassword=Parameter.GetInput(ModuleName, TestId, 9);
	SecurityAnwser=Parameter.GetInput(ModuleName, TestId, 11);
	WebElement SecQuestionNotSelected=null;
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).sendKeys(Name);
		config.driver.findElement(OR.GetLocator("Registration", "PrimaryEmail")).sendKeys(Email);
		config.driver.findElement(OR.GetLocator("Registration", "MobileNo")).sendKeys(PhoneNo);
		config.driver.findElement(OR.GetLocator("Registration", "EnterPassword")).sendKeys(Password);
		config.driver.findElement(OR.GetLocator("Registration", "ConfirmPassword")).sendKeys(ConfirmPassword);
		
		
		//WebElement dropdown= config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']"));
		//dropdown.click();
		//config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']//div[@class='chosen-drop']/ul[@class='chosen-results']//li[2]")).click();
		
		config.driver.findElement(OR.GetLocator("Registration", "SecurityAnwser")).sendKeys(SecurityAnwser);
		config.driver.findElement(By.xpath("//input[@id='choice_3_7_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='choice_3_13_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='gform_submit_button_3']")).click();
		SecQuestionNotSelected=config.driver.findElement(By.xpath("//li[@id='field_3_5']//div[contains(text(),'This field is required.')]"));
		Actual=SecQuestionNotSelected.getText();
		if(Expected.equalsIgnoreCase(Actual)){
			
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			
		}else {
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
	} catch (Exception e){
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	
	Thread.sleep(4000);
	
	//-----test case---TC012_Security_Anwser_Not given
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC012_Security_Anwser_Not given";
	Expected= Parameter.GetInput(ModuleName, TestId, 2);
	Name=Parameter.GetInput(ModuleName, TestId, 5);
	Email=Parameter.GetInput(ModuleName, TestId, 6);
	PhoneNo=Parameter.GetInput(ModuleName, TestId, 7);
	Password=Parameter.GetInput(ModuleName, TestId, 8);
	ConfirmPassword=Parameter.GetInput(ModuleName, TestId, 9);
	SecurityAnwser=Parameter.GetInput(ModuleName, TestId, 11);
	WebElement SecAnswserNotProvided=null;
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).sendKeys(Name);
		config.driver.findElement(OR.GetLocator("Registration", "PrimaryEmail")).sendKeys(Email);
		config.driver.findElement(OR.GetLocator("Registration", "MobileNo")).sendKeys(PhoneNo);
		config.driver.findElement(OR.GetLocator("Registration", "EnterPassword")).sendKeys(Password);
		config.driver.findElement(OR.GetLocator("Registration", "ConfirmPassword")).sendKeys(ConfirmPassword);
		
		
		WebElement dropdown= config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']"));
		dropdown.click();
		config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']//div[@class='chosen-drop']/ul[@class='chosen-results']//li[2]")).click();
		config.driver.findElement(OR.GetLocator("Registration", "SecurityAnwser")).clear();
		config.driver.findElement(By.xpath("//input[@id='choice_3_7_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='choice_3_13_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='gform_submit_button_3']")).click();
		SecAnswserNotProvided=config.driver.findElement(By.xpath("//div[contains(text(),'This field is required.')]"));
		Actual=SecAnswserNotProvided.getText();
		if(Expected.equalsIgnoreCase(Actual)){
			
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			
		}else {
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
	} catch (Exception e){
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	
	//-----test case---TC013_Validate_CharityVelloIdFeild_for_BusinessProfileType

	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC013_Validate_CharityVelloIdFeild_for_BusinessProfileType";
	Expected= Parameter.GetInput(ModuleName, TestId, 2);
	
	WebElement CharityVelloId = null;
	try {
		//config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
	//config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
	
	CharityVelloId=config.driver.findElement(OR.GetLocator("Registration", "CharityVelloIdFeild"));
	if(CharityVelloId.isDisplayed()){
		//String NamefieldText = config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).findElement(By.className("gfield_description")).getText();
		Actual="Charity Vello Id text-feild is displayed";
		Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
	}else{
		Actual="Charity Vello Id text-feild is not displayed";
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		
	}
	} catch (Exception e) {
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}


	
	
	
	
	//-----test case---TC014_Validate_No Name Field_for_BusinessProfileType
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC014_Validate_No Name Field_for_BusinessProfileType";
	Expected = Parameter.GetInput(ModuleName, TestId, 2);
	WebElement NameField1=null;
	
	
	try {
		
		//BusinessAccountDefault1=config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness"));
		//String Defaultpage=BusinessAccountDefault.getText();
		NameField1=config.driver.findElement(OR.GetLocator("Registration", "NameFeild"));
		if(NameField1.isDisplayed()){
			Actual ="Name text-field is present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");	
		}else{
			Actual="Name text-field is not be present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
		}
	} catch (Exception e) {
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	Thread.sleep(4000);

	//-----test case---TC015_Validate_No Name Field_for_CharityProfileType
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC015_Validate_No Name Field_for_CharityProfileType";
	Expected = Parameter.GetInput(ModuleName, TestId, 2);
	WebElement NameField2=null;
	
	
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCharity")).click();
		
		//BusinessAccountDefault1=config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness"));
		//String Defaultpage=BusinessAccountDefault.getText();
		NameField2=config.driver.findElement(OR.GetLocator("Registration", "NameFeild"));
		if(NameField2.isDisplayed()){
			Actual ="Name text-field is present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");	
		}else{
			Actual="Name text-field is not be present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
		}
	} catch (Exception e) {
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	Thread.sleep(4000);
	
	//-----test case---TC016_Validate_No Mobile Number Field_for_BusinessProfileType
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC016_Validate_No Mobile Number Field_for_BusinessProfileType";
	Expected = Parameter.GetInput(ModuleName, TestId, 2);
	WebElement MobileNumberField=null;
	
	
	try {
		//config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		//config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCharity")).click();
		
		
		MobileNumberField=config.driver.findElement(OR.GetLocator("Registration", "MobileNo"));
		if(MobileNumberField.isDisplayed()){
			Actual ="Mobile Number Feild is present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");	
		}else{
			Actual="Mobile Number Feild is not present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
		}
	} catch (Exception e) {
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	Thread.sleep(4000);

	//-----test case---TC017_Validate_No Mobile Number Field_for_CharityProfileType
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC017_Validate_No Mobile Number Field_for_CharityProfileType";
	Expected = Parameter.GetInput(ModuleName, TestId, 2);
	WebElement MobileNumberField1=null;
	
	
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCharity")).click();
		
		
		MobileNumberField1=config.driver.findElement(OR.GetLocator("Registration", "MobileNo"));
		if(MobileNumberField1.isDisplayed()){
			Actual ="Mobile Number Feild is present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");	
		}else{
			Actual="Mobile Number Feild is not present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
		}
	} catch (Exception e) {
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	Thread.sleep(4000);

	//-----test case---TC018_Validate_No Charity Vello Id Feild_for_CustomerProfileType
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC018_Validate_No Charity Vello Id Feild_for_CustomerProfileType";
	Expected = Parameter.GetInput(ModuleName, TestId, 2);
	WebElement CharityVelloIdField1=null;
	
	
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		
		
		CharityVelloIdField1=config.driver.findElement(OR.GetLocator("Registration", "CharityVelloIdFeild"));
		if(CharityVelloIdField1.isDisplayed()){
			Actual ="Charity Vello Id text-feild is present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");	
		}else{
			Actual="Charity Vello Id text-feild is not be present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
		}
	} catch (Exception e) {
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	Thread.sleep(4000);

	//-----test case---TC019_Validate_No Charity Vello Id Feild_for_CharityProfileType
	config.driver.navigate().refresh();
	Thread.sleep(2000);
	ModuleName = "Registration";
	TestId = "TC019_Validate_No Charity Vello Id Feild_for_CharityProfileType";
	Expected = Parameter.GetInput(ModuleName, TestId, 2);
	WebElement CharityVelloIdField2=null;
	
	
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCharity")).click();
		
		
		CharityVelloIdField2=config.driver.findElement(OR.GetLocator("Registration", "CharityVelloIdFeild"));
		if(CharityVelloIdField2.isDisplayed()){
			Actual ="Charity Vello Id text-feild is present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");	
		}else{
			Actual="Charity Vello Id text-feild is not be present";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
		}
	} catch (Exception e) {
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
	Thread.sleep(1000);


	//-----test case---TC020_Leave_Velloe Terms of Service Checkbox_unchecked
	config.driver.navigate().refresh();
	ModuleName = "Registration";
	TestId = "TC020_Leave_Velloe Terms of Service Checkbox_unchecked";
	Expected= Parameter.GetInput(ModuleName, TestId, 2);
	Name=Parameter.GetInput(ModuleName, TestId, 5);
	Email=Parameter.GetInput(ModuleName, TestId, 6);
	PhoneNo=Parameter.GetInput(ModuleName, TestId, 7);
	Password=Parameter.GetInput(ModuleName, TestId, 8);
	ConfirmPassword=Parameter.GetInput(ModuleName, TestId, 9);
	SecurityAnwser=Parameter.GetInput(ModuleName, TestId, 11);
	WebElement ErrorOccured=null;
	try {
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeBusiness")).click();
		config.driver.findElement(OR.GetLocator("Registration", "ProfileTypeCustomer")).click();
		config.driver.findElement(OR.GetLocator("Registration", "NameFeild")).sendKeys(Name);
		config.driver.findElement(OR.GetLocator("Registration", "PrimaryEmail")).sendKeys(Email);
		config.driver.findElement(OR.GetLocator("Registration", "MobileNo")).sendKeys(PhoneNo);
		config.driver.findElement(OR.GetLocator("Registration", "EnterPassword")).sendKeys(Password);
		config.driver.findElement(OR.GetLocator("Registration", "ConfirmPassword")).sendKeys(ConfirmPassword);
		
		
		WebElement dropdown= config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']"));
		dropdown.click();
		config.driver.findElement(By.xpath("//div[@id='input_3_5_chosen']//div[@class='chosen-drop']/ul[@class='chosen-results']//li[2]")).click();
		//config.driver.findElement(By.xpath("//div[@class='chosen-container chosen-container-single']//span[contains(text(),'Choose a security question ...')]")).click();
		//config.driver.findElement(By.xpath("//select[@id='input_3_5']//option[2]")).click();
		//Thread.sleep(4000);
		config.driver.findElement(OR.GetLocator("Registration", "SecurityAnwser")).sendKeys(SecurityAnwser);
		//config.driver.findElement(By.xpath("//input[@id='choice_3_7_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='choice_3_13_1']")).click();
		config.driver.findElement(By.xpath("//input[@id='gform_submit_button_3']")).click();
		ErrorOccured=config.driver.findElement(By.xpath("//div[@class='gfield_description validation_message']"));
		Actual=ErrorOccured.getText();
		System.out.println("////////////////////"+Actual);
		if(Expected.equalsIgnoreCase(Actual)){
			
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			
		}else {
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
	} catch (Exception e){
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}	
     
	config.driver.close();
	
	}
	
	
}
