package com.troy.androidrc.mvp;

import com.troy.androidrc.utils.LogUtil;

/**
 * Created by Troy on 2018/6/5.
 */

public class ActivityPresenter implements BaseActivityPresenter {

    private static String TAG = ActivityPresenter.class.getSimpleName();

    @Override
    public void onCreate() {
        LogUtil.i(TAG, "onCreate()");
    }

    @Override
    public void onStart() {
        LogUtil.i(TAG, "onStart()");
    }

    @Override
    public void onResume() {
        LogUtil.i(TAG, "onResume()");
    }

    @Override
    public void onPause() {
        LogUtil.i(TAG, "onPause()");
    }

    @Override
    public void onStop() {
        LogUtil.i(TAG, "onStop()");
    }

    @Override
    public void onDestroy() {
        LogUtil.i(TAG, "onDestroy()");
    }

}
