package com.armes.ultimate;

import android.text.format.Time;

public class CustomClock extends Time
{
	public CustomClock(int perSec, int perMin, int perHour, int perDay, int parts, long difference)
	{
		holder = System.currentTimeMillis() + difference;
		super.set(holder);
		lengthOfSecond = perSec;
		lengthOfMinute = perMin;
		lengthOfHour = perHour;
		lengthOfDay = perDay;
		partsOfDay = parts;
		offset = difference;
		
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
		partsOfDay = 2;
	}
	
	private int lengthOfSecond; // in milliseconds
	private int lengthOfMinute;
	private int lengthOfHour;
	private long lengthOfDay;
	private int partsOfDay;     // AM-PM, split into fourths, etc.
	private long offset;        // difference from Java system time.
	private long holder;        // holds last set number
	
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
		holder = System.currentTimeMillis() + offset;
		long today = holder%lengthOfDay;
		second = (int) (today%lengthOfMinute)/lengthOfSecond;
		minute = (int) (today%lengthOfHour)/lengthOfMinute;
		hour   = (int) (today%lengthOfDay)/lengthOfHour;
	}
	
	public enum Format
	{
		BIN, OCT, DEC, HEX, MAYA, BAYL
	}
}
