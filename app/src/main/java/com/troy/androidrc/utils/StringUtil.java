package com.troy.androidrc.utils;

import com.troy.androidrc.TroyApplication;

/**
 * Created by Troy on 2018/6/9.
 */

public class StringUtil {

    public static String getString(int resId){
        return TroyApplication.getInstance().getString(resId);
    }

}
