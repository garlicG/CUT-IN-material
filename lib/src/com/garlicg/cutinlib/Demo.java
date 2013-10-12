package com.garlicg.cutinlib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.garlicg.cutinlib.CutinInfo;
import com.garlicg.cutinlib.CutinService;

/**
 * Call cutinservice.
 */
public class Demo {
	private Intent mCutinIntent;
	private Context mContext;
	public Demo(Context context){
		mContext = context;
	}

	/**
	 * Play cutin service. If there are playing cutin , stop it and new cutin service start.
	 * @param serviceClass
	 * @return
	 */
	public ComponentName play(Class<? extends CutinService> serviceClass) {
		if (mCutinIntent != null) {
			mContext.stopService(mCutinIntent);
		}
		mCutinIntent = new Intent(mContext, serviceClass);
		return mContext.startService(mCutinIntent);
	}
	
	/**
	 * Play cutin service. If there are playing cutin , stop it and new cutin service start.
	 * @param serviceClass
	 * @param cutinId
	 * @return
	 */
	public ComponentName play(Class<? extends CutinService> serviceClass , long cutinId) {
		if (mCutinIntent != null) {
			mContext.stopService(mCutinIntent);
		}
		mCutinIntent = new Intent(mContext, serviceClass);
		mCutinIntent.putExtra(CutinInfo.DATA_CUTIN_ID, cutinId);
		return mContext.startService(mCutinIntent);
	}
	
	public void forceStop(){
		if(mCutinIntent != null){
			mContext.stopService(mCutinIntent);
		}
		mCutinIntent = null;
	}
}