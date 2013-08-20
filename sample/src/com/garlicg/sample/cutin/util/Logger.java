package com.garlicg.sample.cutin.util;

import android.os.Debug;
import android.util.Log;

import com.garlicg.sample.cutin.BuildConfig;

public class Logger{  
    private static final String TAG = "CUTIN";  
   
    public static final void d(String msg) {  
    	if (BuildConfig.DEBUG) 
    		Log.d(TAG, msg);  
    }  
    
    public static final void d(String tag, String msg) {  
        if (BuildConfig.DEBUG) {  
            Log.d(tag, msg);  
        }  
    }  
    public static final void e(String msg) {  
        if (BuildConfig.DEBUG) {  
            Log.e(TAG, msg);  
        }  
    }  
      
    public static final void e(String tag, String msg) {  
        if (BuildConfig.DEBUG) {  
            Log.e(tag, msg);  
        }  
    }  
      
    public static final void i(String msg) {  
        if (BuildConfig.DEBUG) {  
            Log.i(TAG, msg);  
        }  
    }  
      
    public static final void i(String tag, String msg) {  
        if (BuildConfig.DEBUG) {  
            Log.i(tag, msg);  
        }  
    }  
      
    public static final void v(String msg) {  
        if (BuildConfig.DEBUG) {  
            Log.v(TAG, msg);  
        }  
    }  
      
    public static final void v(String tag, String msg) {  
        if (BuildConfig.DEBUG) {  
            Log.v(tag, msg);  
        }  
    }  
      
    public static final void w(String msg) {  
        if (BuildConfig.DEBUG) {  
            Log.w(TAG, msg);  
        }  
    }  
      
    public static final void w(String tag, String msg) {  
        if (BuildConfig.DEBUG) {  
            Log.w(tag, msg);  
        }  
    }  
      
    public static final void heap(){  
        heap(TAG);  
    }  
      
    public static final void heap(String tag) {  
        if (BuildConfig.DEBUG){  
            String msg = "heap : NativeHeapFreeSize=" + Long.toString(Debug.getNativeHeapFreeSize() / 1024) + "kb" +   
                    ", NativeHeapAllocatedSize=" + Long.toString(Debug.getNativeHeapAllocatedSize() / 1024) + "kb" +   
                    ", NativeHeapSize=" + Long.toString(Debug.getNativeHeapSize() / 1024) + "kb";  
            Log.v(tag, msg);  
        }
    }
    
    private static long timeMark = 0;
    public static final void timeLog(String tag, String msg){
    	 if (BuildConfig.DEBUG) {  
    		 if(timeMark == 0){
    			 i(tag , "[0ms(start)]:" + msg );
    			 timeMark = System.currentTimeMillis();
    		 }
    		 else{
    			long current = System.currentTimeMillis();
    			 long diff = current - timeMark;
    			 timeMark = current;
    			 i(tag , "[" + diff + "ms]:" + msg );
    		 }
         }
    }
    
}