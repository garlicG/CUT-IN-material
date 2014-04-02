package com.garlicg.cutinlib;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * カットインマネージャーとの連携関係のUtiltyです。
 */
public class CutinManagerUtils {
	
	public static final String PACKAGE_NAME_CUTIN_MANAGER = "com.garlicg.cutin";
	
	/**
	 * カットインマネージャーからよばれた際のResultとして返却するIntentを構築します。
	 * 
	 */
	public static Intent buildResultIntent(CutinItem item){
		Intent intent = new Intent();
		intent.putExtra(CutinService.EXTRA_CUTIN_ACTION, item.serviceClass.getName());
		intent.putExtra(CutinService.EXTRA_CUTIN_TITLE, item.cutinName);
		intent.putExtra(CutinService.EXTRA_CUTIN_ID, item.cutinId);
		return intent;
	}
	
	/**
	 * カットイン設定Activityを呼び出すIntentを構築します。
	 * 呼び出されるActivityはカットインマネージャーのダイアログテーマになっています。
	 */
	public static Intent buildInAppSetIntent(CutinItem item){
		Intent intent = new Intent(CutinService.ACTION_SET_CUTIN);
		intent.setPackage(PACKAGE_NAME_CUTIN_MANAGER);
		intent.putExtra(CutinService.EXTRA_CUTIN_ACTION, item.serviceClass.getName());
		intent.putExtra(CutinService.EXTRA_CUTIN_TITLE, item.cutinName);
		intent.putExtra(CutinService.EXTRA_CUTIN_ID, item.cutinId);
		return intent;
	}
	
	/**
	 * カットインマネージャーからカットイン設定Activityを呼び出します。
	 */
	public static boolean startActivityInAppSet(Context context , CutinItem item){
		try{
			context.startActivity(buildInAppSetIntent(item));
		}catch(ActivityNotFoundException e){
			return false;
		}
		return true;
	}

	/**
	 * カットインマネージャーが存在するか否かを判定します。
	 */
	public static boolean existManager(Context context){
		PackageManager pm = context.getPackageManager();
		Intent intent = pm.getLaunchIntentForPackage(PACKAGE_NAME_CUTIN_MANAGER);
		return intent != null;
	}
	
	/**
	 * GooglePlayのカットインマネージャーに遷移するIntentを構築します。
	 */
	public static Intent buildMarketIntent(){
		Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.garlicg.cutin"));
		return intent;
	}
	
	/**
	 * GooglePlayのカットインマネージャーページに遷移します。
	 */
	public static void startActivityMarketOnCutinManagerPage(Context context){
		Intent intent = buildMarketIntent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	/**
	 * カットインマネージャーからアプリが起動したかを判定します。
	 */
	public static boolean isCalledFromCutinManager(Intent intent){
		if(intent == null) return false;
		String action = intent.getAction();
		if(action == null) return false;
		
		if(action.equals(CutinService.ACTION_PICK_CUTIN)){ 
			return true;
		}
		else {
			return false;
		}
	}
	
}
