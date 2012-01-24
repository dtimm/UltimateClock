package com.armes.ultimate;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class UltimateClockAppWidgetProvider extends AppWidgetProvider
{
	public void onEnabled(Context context)
	{
		timer.update();
		 
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ultimate_clock_appwidget);
	           
	    // Set text to the current CustomClock time.
	    views.setTextViewText(R.id.currentTime, timer.toString());
	}
	
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		timer = new CustomClock();
		 
		final int N = appWidgetIds.length;
		for (int i=0; i<N; i++)
		{
	           int appWidgetId = appWidgetIds[i];
	           RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ultimate_clock_appwidget);
	           
	           // Set text to the current CustomClock time.
	           views.setTextViewText(R.id.currentTime, timer.toString());
	           
	           // Update the app widget.
	           appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}
	 
	CustomClock timer;
}
