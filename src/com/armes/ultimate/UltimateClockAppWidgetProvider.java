package com.armes.ultimate;

import com.armes.ultimate.CustomClock;
import com.armes.ultimate.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.widget.RemoteViews;

public class UltimateClockAppWidgetProvider extends AppWidgetProvider
{	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		dec = new CustomClock(864, 86400, 8640000, 86400000, false, 0);
		bigContext = context;
		hand = new Handler();
		hand.postDelayed(updater, 864);
	}
	
	private CustomClock dec;
	private Context bigContext;
	private Handler hand;
	
	Runnable updater = new Runnable()
	{
		@Override
		public void run()
		{
			dec.update();
			
			AppWidgetManager manager = AppWidgetManager.getInstance(bigContext);
			
			RemoteViews views = new RemoteViews(bigContext.getPackageName(),
					R.layout.ultimate_clock_appwidget);
			views.setTextViewText(R.id.currentDecimalTime, dec.toString('.'));
			
			manager.updateAppWidget(new ComponentName(bigContext,
					UltimateClockAppWidgetProvider.class), views);
			
			hand.removeCallbacks(this);
	        hand.postDelayed(this, 864);
		}
	};
}
