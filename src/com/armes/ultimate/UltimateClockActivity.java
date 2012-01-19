package com.armes.ultimate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UltimateClockActivity extends Activity
{	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timer24  = new CustomClock(1000, 60000, 3600000, 24*3600000, 2, 0);
        timer28  = new CustomClock(1000, 60000, 3600000, 28*3600000, 2, 0);
        timerDec = new CustomClock(864, 86400, 8640000, 86400000, 1, 0);
    	TextView tv = new TextView(this);
        tv.setText("24 hour time -- " + timer24.toString() + "\n28 hour time -- " + timer28.toString()
        		+ "\ndecimal time -- " + timerDec.toString());
        setContentView(tv);
    }
    
    protected void onPause()
    {
    	super.onPause();
    	// implement
    }
    
    protected void onResume()
    {
    	super.onResume();
    	timer24.refresh();
    	timer28.refresh();
    	timerDec.refresh();
    	TextView tv = new TextView(this);
    	tv.setText("24 hour time -- " + timer24.toString() + "\n28 hour time -- " + timer28.toString()
        		+ "\ndecimal time -- " + timerDec.toString());
        setContentView(tv);
    	// implement
    }
    
    private CustomClock timer28;
    private CustomClock timer24;
    private CustomClock timerDec;
}