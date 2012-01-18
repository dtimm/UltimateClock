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
        OddClock timer = new OddClock();
    	TextView tv = new TextView(this);
        tv.setText("28 hour time -- " + timer.showTime());
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
    	OddClock timer = new OddClock();
    	TextView tv = new TextView(this);
    	tv.setText("28 hour time -- " + timer.showTime());
        setContentView(tv);
    	// implement
    }
}