package com.armes.ultimate;

import android.text.format.Time;

public class CustomClock extends Time
{
	/**
	 * Builds a clock with customizable seconds, minutes, hours, and days.
	 * 
	 * @param perSec milliseconds in a "second"
	 * @param perMin milliseconds in a "minute"
	 * @param perHour milliseconds in an "hour"
	 * @param perDay milliseconds in a "day"
	 * @param parts whether the day is split in the middle
	 * @param difference millisecond offset from system time (system time already corrected for timezone)
	 */
	public CustomClock(int perSec, int perMin, int perHour, int perDay, boolean parts, long difference)
	{
		holder = System.currentTimeMillis() + difference;
		super.set(holder);
		offset = difference + this.gmtoff * 1000; // include system calculated timezone adjustment.
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
	
	/**
	 * Default clock constructor makes a "normal" clock.
	 */
	public CustomClock()
	{
		super.set(System.currentTimeMillis());
		offset = this.gmtoff * 1000;
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
	
	/**
	 * Formats a CustomClock to a display type and returns a string.
	 *
	 * @param format a string with hours, minutes, and seconds and punctuation (e.g. HH.MM.SS)
	 * @return a string in the format specified.
	 */
	public String toString(String format)
	{
		String handle = new String();
		
		int h, hs, m, ms, s, ss;
		h=hs=m=ms=s=ss=0;
		
		for(int i=0;i<format.length();i++)
		{
			switch(format.charAt(i))
			{
			case 'h':
			case 'H':
				hs++;
				break;
			case 'm':
			case 'M':
				ms++;
				break;
			case 's':
			case 'S':
				ss++;
				break;
			default:
				break;
			}
		}
		
		for(int i=0;i<format.length();i++)
		{
			switch(format.charAt(i))
			{
			case 'h':
			case 'H':
				
				break;
			case 'm':
			case 'M':
				
				break;
			case 's':
			case 'S':
				
				break;
			default:
				
				break;
			}
		}
		return handle;
	}
	
	/**
	 * Formats a CustomClock to a display with a certain punctuation character.
	 *
	 * @param punct a char to replace the seminal colon
	 * @return a string in the format specified.
	 */
	public String toString(char punct)
	{
		String handle = new String();
		long hoursInDay = lengthOfDay/lengthOfHour;
		
		// checks for AM/PM split and handles logic
		if(amPmSplit && hour > hoursInDay/2)
		{
			if(hour - hoursInDay/2 < 10) handle += "0";
			handle = handle + (hour - hoursInDay/2) + punct;
		}
		else
		{
			if(hour == 0 && amPmSplit)
			{
				handle = handle + hoursInDay/2 + punct;
			}
			else
			{
				if(hour < 10) handle += "0";
				handle = handle + hour + punct;
			}
		}
		if(minute < 10) handle += "0";
		handle = handle + minute + punct;
		if(second < 10) handle += "0";
		handle = handle + second;
		
		if(amPmSplit)
		{
			if(amPmSplit && hour > hoursInDay/2) handle = handle + " PM";
			else handle = handle + " AM";
		}
		
		return handle;
	}
	
	@Override
	public String toString()
	{
		return this.toString(':');
	}
	
   /**
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
	
   /**
    * Defines number base system.
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
