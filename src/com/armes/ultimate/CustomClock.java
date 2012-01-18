package com.armes.ultimate;

import android.text.format.Time;

public class CustomClock extends Time
{
	public CustomClock(int sec, int min, int hour, int day, int parts, long difference)
	{
		super.set(System.currentTimeMillis() + difference);
		lengthOfSecond = sec;
		lengthOfMinute = min;
		lengthOfHour = hour;
		lengthOfDay = day;
		partsOfDay = parts;
	}
	
	public CustomClock()
	{
		super.set(System.currentTimeMillis());
		lengthOfSecond = 1000;
		lengthOfMinute = 60;
		lengthOfHour = 60;
		lengthOfDay = 24;
		partsOfDay = 2;
	}
	
	private int lengthOfSecond; // in milliseconds
	private int lengthOfMinute; // in seconds
	private int lengthOfHour;   // in minutes
	private int lengthOfDay;    // in hours
	private int partsOfDay;     // AM-PM, split into fourths, etc.
	private long offset;        // difference from Java system time.
}
