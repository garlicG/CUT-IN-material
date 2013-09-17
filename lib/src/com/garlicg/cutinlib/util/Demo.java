package com.garlicg.cutinlib.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.garlicg.cutinlib.CutinService;

public class Demo {
	private Intent mCutinIntent;
	private Context mContext;
	public Demo(Context context){
		mContext = context;
	}

	public ComponentName play(Class<? extends CutinService> serviceClass) {
		if (mCutinIntent != null) {
//			if(!mCutinIntent.getComponent().getClassName().equals(serviceClass.getName())){
			mContext.stopService(mCutinIntent);
//			}
		}
		mCutinIntent = new Intent(mContext, serviceClass);
		return mContext.startService(mCutinIntent);
	}
	
	public void forceStop(){
		if(mCutinIntent != null){
			mContext.stopService(mCutinIntent);
		}
		mCutinIntent = null;
	}
}