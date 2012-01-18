package com.armes.ultimate;

import android.text.format.Time;

public class OddClock
{
	public OddClock()
	{
		currentTime = new Time();
		currentTime.set(System.currentTimeMillis());
		hours = 24 * currentTime.weekDay + currentTime.hour;
		hours = hours%28;
		minutes = currentTime.minute;
		seconds = currentTime.second;
	}
	
	public int getHours()
	{
		return hours;
	}
	
	public int getMinutes()
	{
		return minutes;
	}
	
	public int getSeconds()
	{
		return seconds;
	}
	
	public int getDay()
	{
		return currentTime.weekDay;
	}
	
	public String showTime()
	{
		String handle = new String();
		if(hours < 10) handle += "0";
		handle = handle + hours + ":";
		if(minutes < 10) handle += "0";
		handle = handle + minutes + ":";
		if(seconds < 10) handle += "0";
		handle = handle + seconds;
		return handle;
	}
	
	private Time currentTime;
	private int hours;
	private int minutes;
	private int seconds;
}