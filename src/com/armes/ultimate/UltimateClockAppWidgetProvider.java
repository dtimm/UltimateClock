package com.armes.ultimate;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class UltimateClockAppWidgetProvider extends AppWidgetProvider
{
	 public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	 {
		 timer = new CustomClock();
		 
		 final int N = appWidgetIds.length;
		 for (int i=0; i<N; i++)
		 {
	            int appWidgetId = appWidgetIds[i];
	            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ultimate_clock_appwidget);
	            views.setString(R.id.current_time, null, timer.toString());

	            // Tell the AppWidgetManager to perform an update on the current app widget
	            appWidgetManager.updateAppWidget(appWidgetId, views);
		 }
	 }
	 
	 CustomClock timer;
}
