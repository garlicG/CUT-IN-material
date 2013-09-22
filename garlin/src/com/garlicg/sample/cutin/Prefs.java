package com.garlicg.sample.cutin;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Prefs {
	private final static String KEY_SOUND_SETTING = "key_sound_setting";
	protected static boolean getSound(Context context){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		return pref.getBoolean(KEY_SOUND_SETTING, false);
	}
	
	protected static void setSound(Context context , boolean value){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = pref.edit();
		edit.putBoolean(KEY_SOUND_SETTING, value);
		edit.commit();
	}
}
