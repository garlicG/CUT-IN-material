package com.garlicg.cutinlib;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;

public abstract class CutinService extends Service {
	
	public final static String ACTION_PICK_CUTIN ="com.garlicg.cutin.action.PICK";
	public final static String ACTION_SET_CUTIN ="com.garlicg.cutin.action.SET";
	public final static String EXTRA_CUTIN_ACTION ="action_name";
	public final static String EXTRA_CUTIN_TITLE ="cutin_name";
	public final static String EXTRA_CUTIN_ID ="cutin_id";
	public final static String EXTRA_TRIGGER_ID ="trigger_id";
	public final static int TRIGGER_ID_SCREEN_ON = 0;
	public final static int TRIGGER_ID_CHARGE_ON = 1;
	public final static int TRIGGER_ID_HEAD_SET_ON = 2;
	public final static int TRIGGER_ID_DATE_CHANGED = 3;
	public final static int TRIGGER_ID_LOW_BATTERY = 4;
	public final static int TRIGGER_ID_NOTIFICATION = 5;
	public final static String EXTRA_NOTIFICATION_TICKER ="notification_ticker";
	public final static String EXTRA_NOTIFICATION_PACKAGE_NAME="notification_package_name";
	
	private View mLayout;
	private WindowManager mWindowManager;
	private boolean mStarted = false;

	/**
	 * Create root view which is inflated to full screen window.
	 */
	protected abstract View create();

	/**
	 * It is called after create(). At this time, view size is possible to get.
	 * You must call finishCutin() or stopSelf() after end of your execution.
	 */
	protected abstract void start(Intent intent, int flags, int startId);

	/**
	 * Release resources, etc.
	 */
	protected abstract void destroy();

	@Override
	final public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	final public void onCreate() {
		super.onCreate();
		mLayout = create();

		if (mLayout == null) {
			throw new NullPointerException("CutinService#create need to return view.");
		}

		WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				PixelFormat.TRANSLUCENT);

		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		mWindowManager.addView(mLayout, params);
	}

	@Override
	final public int onStartCommand(final Intent intent, final int flags, final int startId) {
		if (!mStarted) {
			mStarted = true;
			
			// must to be possible to get view size on start method. 
			new Handler().post(new Runnable() {
				@Override
				public void run() {
					start(intent,flags,startId);
				}
			});
		} else {
			reStart();
		}
		return START_NOT_STICKY;
	}

	protected void reStart() {
	}

	protected void finishCutin() {
		stopSelf();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		destroy();
		if (mLayout != null) {
			mWindowManager.removeView(mLayout);
		}
	}

}
