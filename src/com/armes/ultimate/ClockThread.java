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
	CustomClock timer1, timer2;
	
	private long sleepTime_;
	private long delay_ = 70;
	
	int state = 1;
	public final static int RUNNING = 1;
	public final static int PAUSED  = 2;
	
	public ClockThread(SurfaceHolder sh, Context ct, Handler hand, 
			CustomClock dec, CustomClock norm)
	{
		surfHolder = sh;
		this.handle = hand;
		this.context = ct;
		
		linePaint = new Paint();
		linePaint.setARGB(255, 255, 255, 255);
		linePaint.setTextAlign(Paint.Align.CENTER);
		linePaint.setTextSize(50);
		linePaint.setAntiAlias(true);
		blackPaint = new Paint();
		blackPaint.setARGB(255, 0, 0, 0);
		
		timer1 = dec;
		timer2 = norm;
	}
	
	@Override
	public void run()
	{
		// update it
		while(state == RUNNING)
		{
			long beforeTime = System.nanoTime();
			timer1.update();
			timer2.update();
			
			// draw it
			Canvas c = null;
			try
			{
				c = surfHolder.lockCanvas(null);
				synchronized(surfHolder)
				{
					int width = c.getWidth();
					int height = c.getHeight();
					c.drawRect(0, 0, width, height, blackPaint);
					c.drawText(timer1.toString('.'), width/2, 100, linePaint);
					c.drawText(timer2.toString(), width/2, 200, linePaint);
				}
			}
			finally
			{
				if(c != null) surfHolder.unlockCanvasAndPost(c);
			}
		
			// sleep it
			this.sleepTime_ = delay_ - ((System.nanoTime() - beforeTime)/1000000L);
			
			try
			{
				if(sleepTime_ > 0) Thread.sleep(sleepTime_);
			}
			catch (InterruptedException ex)
			{
				Logger.getLogger(ClockThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
