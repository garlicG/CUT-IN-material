package com.garlicg.cutinlib;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class CutinInfo {
	public final static String ACTION_PICK_CUTIN ="com.garlicg.cutin.action.PICK";
	public final static String ACTION_SET_CUTIN ="com.garlicg.cutin.action.SET";
	public final static String DATA_ACTION_NAME ="action_name";
	public final static String DATA_CUTIN_NAME ="cutin_name";
	public final static String DATA_CUTIN_ID ="cutin_id";
	
	/**
	 *  It is utility method for returning Intent to CutIn Manager.<br>
	 *   eq.<blockquote>
	 *   CutinItem item = new CutinItem(CutinService.class , "SAMPLE 1");<br>
	 *   Intent intent = CutinInfo.buildPickedIntent(item);<br>
	 *   setResult(RESULT_OK , intent);<br>
	 *   finish();<br>
	 *  </blockquote>
	 * @param cutinItem
	 * @return
	 */
	public static Intent buildPickedIntent(CutinItem cutinItem){
		Intent intent = new Intent();
		intent.putExtra(DATA_ACTION_NAME, cutinItem.serviceClass.getName());
		intent.putExtra(DATA_CUTIN_NAME, cutinItem.cutinName);
		intent.putExtra(DATA_CUTIN_ID, cutinItem.cutinId);
		return intent;
	}
	
	/**
	 *  It is utility method for setting CutIn on CutIn Manager.<br>
	 *   eq.<blockquote>
	 *   CutinItem item = new CutinItem(CutinService.class , "SAMPLE 1");<br>
	 *   Intent intent = CutinInfo.buildSetCutinIntent(item);<br>
	 *   startActivity(intent);
	 *  </blockquote>
	 * @param cutinItem
	 * @return
	 */
	public static Intent buildSetCutinIntent(CutinItem cutinItem){
		Intent intent = new Intent(ACTION_SET_CUTIN);
		intent.setPackage("com.garlicg.cutin");
		intent.putExtra(DATA_ACTION_NAME, cutinItem.serviceClass.getName());
		intent.putExtra(DATA_CUTIN_NAME, cutinItem.cutinName);
		intent.putExtra(DATA_CUTIN_ID, cutinItem.cutinId);
		return intent;
	}
	
	/**
	 * Confirm to exist CutIn Manager app.
	 */
	public static boolean existManager(Context context){
		PackageManager pm = context.getPackageManager();
		Intent intent = pm.getLaunchIntentForPackage("com.garlicg.cutin");
		return intent != null;
	}
	
	/**
	 * Get intent of CutIn Manager to Google play
	 */
	public static Intent buildMarketIntent(){
		Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.garlicg.cutin"));
		return intent;
	}
}
