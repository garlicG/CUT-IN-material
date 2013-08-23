package com.garlicg.cutinlib.util;

import android.content.Context;
import android.widget.Toast;


/**
 * Toast表示クラス
 * @author goto.t
 */
public class Toaster {
	
	private Toaster(){
	}
	
	public static void post(Context context, String message){
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	public static void post(Context context, int stringRes){
		Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show();
	}
	public static void postLong(Context context, String message){
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
	public static void postLong(Context context, int stringRes){
		Toast.makeText(context, stringRes, Toast.LENGTH_LONG).show();
	}
	
}
