package com.vello.clientCode;

import com.vello.modules.BusinessHours;
import com.vello.modules.ContactInformation;
import com.vello.modules.GeneralInformation;
import com.vello.modules.Login;
import com.vello.modules.Registration;
import com.velloe.repository.LaunchApplication;
import com.velloe.repository.Parameter;

public class ClientCode {
	
	private static String Run;

	public static void main(String[] args) throws Exception {
		
		String Module[] = new String[5];		
		Module[0] = "Authentication";
		Module[1] = "Registration";
		Module[2] = "BusinessHours";
		Module[3] = "ContactInformation";
		Module[4] = "GeneralInformation";
		
		for(int i=0;i<Module.length;i++){
		
	    Run = Parameter.GetModule(Module[i]);		
		
	    if(Module[i].equalsIgnoreCase("Authentication")&& Run.equalsIgnoreCase("Yes"))
	    {
		LaunchApplication.applicationLaunch();		
		Login log = new Login();
		log.Authentication();
	    }
	    else if(Module[i].equalsIgnoreCase("Registration")&& Run.equalsIgnoreCase("Yes"))
	    {
	    LaunchApplication.applicationLaunch();	
		Registration re=new Registration();
		re.RegistrationValidity();
		}
	    else if(Module[i].equalsIgnoreCase("BusinessHours")&& Run.equalsIgnoreCase("Yes"))
	    {
	    LaunchApplication.applicationLaunch();	
		BusinessHours bsh = new BusinessHours();
		bsh.BusinessHoursExecution();
	    }
	    else if(Module[i].equalsIgnoreCase("ContactInformation")&& Run.equalsIgnoreCase("Yes"))
	    {
	    LaunchApplication.applicationLaunch();
		ContactInformation cnf = new ContactInformation();
		cnf.ContactInformationCheck();
	    }
	    else if(Module[i].equalsIgnoreCase("GeneralInformation")&& Run.equalsIgnoreCase("Yes"))
	    {
	    LaunchApplication.applicationLaunch();
	    GeneralInformation cnf = new GeneralInformation();
		cnf.checkGeneralInformation();
	    }
		}

	}

}
