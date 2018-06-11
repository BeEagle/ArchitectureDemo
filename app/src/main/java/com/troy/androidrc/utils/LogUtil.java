package com.troy.androidrc.utils;

import android.util.Log;

import com.troy.androidrc.BuildConfig;

/**
 * Created by Troy on 2018/6/9.
 */

public class LogUtil {

    private static boolean mIsDebug = BuildConfig.DEBUG;

    public static void i(String tag, String msg){
        if(mIsDebug){
            Log.i(tag, msg);
        }
    }

}
