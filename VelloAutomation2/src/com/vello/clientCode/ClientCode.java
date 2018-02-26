package com.vello.clientCode;

import com.vello.registration.Registration;
import com.velloe.automation.LaunchApplication;
import com.velloe.automation.Login;

public class ClientCode {

	public static void main(String[] args) throws Exception {
		LaunchApplication.applicationLaunch();
		//Login log = new Login();
		//log.Authentication();
		System.out.println("/////////////////////////////////////////authentication compleat-------------------------");
		Registration re=new Registration();
		re.RegistrationValidity();
		System.out.println("/////////////////////////////////////////registration compleat-------------------------");

	}

}
