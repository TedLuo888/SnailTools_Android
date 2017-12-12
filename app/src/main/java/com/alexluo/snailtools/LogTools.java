package com.alexluo.snailtools;

import android.util.Log;

/**
 * Created by alexluo on 17-12-8.
 */

public class LogTools {

    public static boolean DEBUG = true;
    public static boolean INFO = true;
    public static boolean WARN = true;

    public static void e(String tag,String msg){
        Log.e(tag,msg);
    }


    public static void d(String tag,String msg){
        if(DEBUG) {
            Log.d(tag, msg);
        }
    }


    public static void i(String tag,String msg){
        if(INFO) {
            Log.i(tag, msg);
        }
    }


    public static void w(String tag,String msg){
        if(WARN) {
            Log.w(tag, msg);
        }
    }

}
