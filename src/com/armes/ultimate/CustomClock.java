package com.armes.ultimate;

import android.text.format.Time;

public class CustomClock extends Time
{
	public CustomClock(int perSec, int perMin, int perHour, int perDay, int parts, long difference)
	{
		super.set(System.currentTimeMillis() + difference);
		lengthOfSecond = perSec;
		lengthOfMinute = perMin;
		lengthOfHour = perHour;
		lengthOfDay = perDay;
		partsOfDay = parts;
		
		// milliseconds into current epoch day
		long holder = super.toMillis(true)%lengthOfDay;
		second = (int) (holder%lengthOfSecond);
		minute = (int) (holder%lengthOfMinute)/(lengthOfSecond);
		hour   = (int) (holder%lengthOfHour)/(lengthOfMinute*lengthOfSecond);
	}
	
	public CustomClock()
	{
		super.set(System.currentTimeMillis());
		lengthOfSecond = 1000;
		lengthOfMinute = 60000;
		lengthOfHour = 3600000;
		lengthOfDay = 24*3600000;
		partsOfDay = 2;
	}
	
	private int lengthOfSecond; // in milliseconds
	private int lengthOfMinute;
	private int lengthOfHour;
	private long lengthOfDay;
	private int partsOfDay;     // AM-PM, split into fourths, etc.
	private long offset;        // difference from Java system time.
	
	public String toString()
	{
		String handle = new String();
		if(hour < 10) handle += "0";
		handle = handle + hour + ":";
		if(minute < 10) handle += "0";
		handle = handle + minute + ":";
		if(second < 10) handle += "0";
		handle = handle + second;
		return handle;
	}
	
	public void refresh()
	{
		long difference = System.currentTimeMillis() - super.toMillis(true);
		
	}
}
