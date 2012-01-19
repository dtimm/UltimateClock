package com.armes.ultimate;

import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

public class ClockThread extends Thread
{
	private SurfaceHolder surfHolder;
	private Handler handle;
	private Context context;
	private Paint linePaint;
	private Paint blackPaint;
	CustomClock timer;
	
	private long sleepTime;
	private long delay = 70;
	
	int state = 1;
	public final static int RUNNING = 1;
	public final static int PAUSED  = 2;
	
	public ClockThread(SurfaceHolder sh, Context ct, Handler hand, CustomClock cc)
	{
		surfHolder = sh;
		this.handle = hand;
		this.context = ct;
		
		linePaint = new Paint();
		linePaint.setARGB(255, 255, 255, 255);
		linePaint.setTextAlign(Paint.Align.CENTER);
		linePaint.setTextSize(50);
		blackPaint = new Paint();
		blackPaint.setARGB(255, 0, 0, 0);
		
		timer = cc;
	}
	
	@Override
	public void run()
	{
		// update it
		while(state == RUNNING)
		{
			long beforeTime = System.nanoTime();
			timer.update();
		
			// draw it
			Canvas c = null;
			try
			{
				c = surfHolder.lockCanvas(null);
				synchronized(surfHolder)
				{
					c.drawRect(0, 0, c.getWidth(), c.getHeight(), blackPaint);
					
					c.drawText(timer.toString(), 100, 100, linePaint);
				}
			}
			finally
			{
				if(c != null) surfHolder.unlockCanvasAndPost(c);
			}
		
			// sleep it
			this.sleepTime = delay - ((System.nanoTime() - beforeTime)/1000000L);
			
			try
			{
				if(sleepTime > 0) Thread.sleep(sleepTime);
			}
			catch (InterruptedException ex)
			{
				Logger.getLogger(ClockThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
