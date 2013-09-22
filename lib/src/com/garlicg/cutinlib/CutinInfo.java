package com.garlicg.cutinlib;


import android.content.Intent;

public class CutinInfo {
	public final static String ACTION_PICK_CUTIN ="com.garlicg.cutin.action.PICK";
	public final static String DATA_ACTION_NAME ="action_name";
	public final static String DATA_CUTIN_NAME ="cutin_name";
	public final static String DATA_CUTIN_ID ="cutin_id";
	
	/**
	 *  It is utility method for returning Intent to CUT-IN Manager.<br>
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
}
