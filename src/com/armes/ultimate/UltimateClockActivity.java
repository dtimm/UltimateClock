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
        timer = new CustomClock(1000, 60000, 3600000, 28*3600000, 2, 0);
    	TextView tv = new TextView(this);
        tv.setText("28 hour time -- " + timer.toString());
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
    	timer.refresh();
    	TextView tv = new TextView(this);
    	tv.setText("28 hour time -- " + timer.toString());
        setContentView(tv);
    	// implement
    }
    
    private CustomClock timer;
}