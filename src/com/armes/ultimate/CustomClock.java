package com.armes.ultimate;

import android.graphics.Canvas;
import android.text.format.Time;

public class CustomClock extends Time
{
	public CustomClock(int perSec, int perMin, int perHour, int perDay, boolean parts, long difference)
	{
		holder = System.currentTimeMillis() + difference;
		super.set(holder);
		offset = difference + this.gmtoff * 1000;
		holder += this.gmtoff * 1000;
		lengthOfSecond = perSec;
		lengthOfMinute = perMin;
		lengthOfHour = perHour;
		lengthOfDay = perDay;
		amPmSplit = parts;
		
		// milliseconds into current epoch day
		long today = holder%lengthOfDay;
		second = (int) (today%lengthOfMinute)/lengthOfSecond;
		minute = (int) (today%lengthOfHour)/lengthOfMinute;
		hour   = (int) (today%lengthOfDay)/lengthOfHour;
	}
	
	public CustomClock() // default uses standard 24 hour clock
	{
		super.set(System.currentTimeMillis());
		lengthOfSecond = 1000;
		lengthOfMinute = 60000;
		lengthOfHour = 3600000;
		lengthOfDay = 24*3600000;
		amPmSplit = true;
	}
	
	private int lengthOfSecond; // in milliseconds
	private int lengthOfMinute;
	private int lengthOfHour;
	private long lengthOfDay;
	private boolean amPmSplit;  // AM-PM, split into fourths, etc.
	private long offset;        // difference from Java system time.
	private long holder;        // holds last set number
	
	@Override
	public String toString()
	{
		String handle = new String();
		long hoursInDay = lengthOfDay/lengthOfHour;
		
		// checks for AM/PM split and handles logic
		if(amPmSplit && hour > hoursInDay/2)
		{
			if(hour - hoursInDay/2 < 10) handle += "0";
			handle = handle + (hour - hoursInDay/2) + ":";
		}
		else
		{
			if(hour < 10) handle += "0";
			handle = handle + hour + ":";
		}
		if(minute < 10) handle += "0";
		handle = handle + minute + ":";
		if(second < 10) handle += "0";
		handle = handle + second;
		
		if(amPmSplit)
		{
			if(amPmSplit && hour > hoursInDay/2) handle = handle + " PM";
			else handle = handle + " AM";
		}
		
		return handle;
	}
	
   /*
    * Updates time to current state.
    */
	public void update()
	{
		holder = System.currentTimeMillis() + offset;
		long today = holder%lengthOfDay;
		second = (int) (today%lengthOfMinute)/lengthOfSecond;
		minute = (int) (today%lengthOfHour)/lengthOfMinute;
		hour   = (int) (today%lengthOfDay)/lengthOfHour;
	}
	
   /*
    * Defines the formatting of the string output.
	*/
	public enum Format
	{
		BIN(2), 
		OCT(8), 
		DEC(10), 
		HEX(16), 
		MAYA(20), 
		BAYL(60);
		
		private int value;
		
		private Format(int value)
		{
			this.value = value;
		}
	};
}
