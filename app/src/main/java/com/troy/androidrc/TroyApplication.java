package com.troy.androidrc;

import android.app.Application;

import com.troy.androidrc.db.DbManager;

/**
 * Created by Troy on 2018/6/9.
 */

public class TroyApplication extends Application {

    private static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        DbManager.buildDb();
    }

    public static Application getInstance(){
        return mInstance;
    }
}
