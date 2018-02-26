package com.booking;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.hssf.record.formula.functions.Time;

import com.booking.Authentication;

public class Master_Class {

	public static void main(String[] args) {
		
		//Authentication bk= new Authentication();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf= new SimpleDateFormat("ddssms");
		String str=sdf.format(cal.getTime());		
		System.out.println("12"+str);
		
		
		try {
			//bk.AuroraSmokeTest();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
/*Weekend_Karlsruhe wkr  = new Weekend_Karlsruhe();

try {
	wkr.AuroraSmokeTest();
} catch (Exception e) {
	
	e.printStackTrace();
}*/

	}

}
