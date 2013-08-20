/**
 * Copyright 2013 TAKAHIRO GOTO <goto@gunew.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
