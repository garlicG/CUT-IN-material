package com.garlicg.cutinlib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

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
	 */
	public ComponentName play(Intent intent) {
		forceStop();
		mCutinIntent = intent;
		return mContext.startService(intent);
	}

	/**
	 * 表示するカットインのクラスを指定してデモ再生します。
	 */
	public ComponentName play(Class<? extends CutinService> serviceClass) {
		Intent intent = new Intent(mContext , serviceClass);
		return play(intent);
	}
	
	/**
	 * カットインアイテムを指定してデモ再生をします。
	 * @see CutinService.EXTRA_CUTIN_ID
	 */
	public ComponentName play(CutinItem item) {
		return play(item.serviceClass, item.cutinId);
	}
	
	/**
	 * カットインマネージャーに登録できる任意のIDを指定してデモ再生します。
	 * @see CutinService.EXTRA_CUTIN_ID
	 */
	public ComponentName play(Class<? extends CutinService> serviceClass , long cutinId) {
		Intent intent = new Intent(mContext,serviceClass);
		intent.putExtra(CutinService.EXTRA_CUTIN_ID, cutinId);
		return play(intent);
	}
	
	/**
	 * カットインマネージャーからのイベント呼び出しに応じたデモ再生をします。
	 * @see CutinService.EXTRA_CUTIN_ID
	 */
	public ComponentName play(Class<? extends CutinService> serviceClass ,int triggerId){
		Intent intent = new Intent(mContext,serviceClass);
		intent.putExtra(CutinService.EXTRA_TRIGGER_ID, triggerId);
		return play(intent);
	}
	
	/**
	 * カットインマネージャーからの通知呼び出しに応じたデモ再生をします。
	 * @see CutinService.EXTRA_NOTIFICATION_PACKAGE_NAME
	 * @see CutinService.EXTRA_NOTIFICATION_TICKER
	 */
	public ComponentName play(Class<? extends CutinService> serviceClass , String notifyPackageName ,String ticker){
		Intent intent = new Intent(mContext,serviceClass);
		intent.putExtra(CutinService.EXTRA_TRIGGER_ID, CutinService.TRIGGER_ID_NOTIFICATION);
		intent.putExtra(CutinService.EXTRA_NOTIFICATION_PACKAGE_NAME,notifyPackageName);
		intent.putExtra(CutinService.EXTRA_NOTIFICATION_TICKER,ticker);
		return play(intent);
	}
	
	/**
	 * 再生中のカットインが存在する場合は停止します。
	 */
	public boolean forceStop(){
		boolean isStop = false;
		if(mCutinIntent != null){
			try{
				isStop = mContext.stopService(mCutinIntent);
			}catch(SecurityException e){
			}
		}
		mCutinIntent = null;
		return isStop;
	}
}