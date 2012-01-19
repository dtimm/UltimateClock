package com.armes.ultimate;

import android.app.Activity;
import android.os.Bundle;

public class UltimateClockActivity extends Activity
{	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        timer = new Clock(getBaseContext(), null);
        
        setContentView(timer);
    }
    
    protected void onPause()
    {
    	super.onPause();
    	// implement
    }
    
    protected void onResume()
    {
    	super.onResume();
    	setContentView(timer);
    }
    
    Clock timer;
}