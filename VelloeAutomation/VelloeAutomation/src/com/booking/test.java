package com.booking;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class test {
	
	static String Second,testname;

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
		
		System.out.println(new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()));
		System.out.println(day);

	}

	

}
