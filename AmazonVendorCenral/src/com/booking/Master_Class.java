package com.booking;

import com.booking.Booking;

public class Master_Class {

	public static void main(String[] args) {
		
		Booking bk= new Booking();
		
		try {
			bk.AuroraSmokeTest();
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
