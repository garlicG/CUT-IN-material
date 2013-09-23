package com.garlicg.cutinlib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.garlicg.cutinlib.CutinInfo;
import com.garlicg.cutinlib.CutinService;

public class Demo {
	private Intent mCutinIntent;
	private Context mContext;
	public Demo(Context context){
		mContext = context;
	}

	public ComponentName play(Class<? extends CutinService> serviceClass , int cutinId) {
		if (mCutinIntent != null) {
//			if(!mCutinIntent.getComponent().getClassName().equals(serviceClass.getName())){
			mContext.stopService(mCutinIntent);
//			}
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